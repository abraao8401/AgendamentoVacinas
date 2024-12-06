package br.com.agenda.servlet;

import br.com.agenda.model.Agenda;
import br.com.agenda.model.Usuario;
import br.com.agenda.model.Vacina;
import br.com.agenda.service.AgendaService;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.service.VacinaService;
import br.com.agenda.enums.SituacaoAgenda;
import br.com.agenda.enums.Periodicidade;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/agenda")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();
    private UsuarioService usuarioService = new UsuarioService();
    private VacinaService vacinaService = new VacinaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            // Listar todas as agendas
            List<Agenda> agendas = agendaService.listarTodasAgendas();
            request.setAttribute("agendas", agendas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAgendas.jsp");
            dispatcher.forward(request, response);
        } else if ("listarPorSituacao".equals(action)) {
            // Listar agendas Canceladas ou Realizadas
            String situacaoStr = request.getParameter("situacao");
            SituacaoAgenda situacao = SituacaoAgenda.valueOf(situacaoStr);
            List<Agenda> agendas = agendaService.listarAgendasPorSituacao(situacao);
            request.setAttribute("agendas", agendas);
            request.setAttribute("filter", situacaoStr);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAgendasPorSituacao.jsp");
            dispatcher.forward(request, response);
        } else if ("listarDoDia".equals(action)) {
            // Listar agendas do dia atual, ordenadas por situação
            List<Agenda> agendas = agendaService.listarAgendasDoDiaAtual();
            request.setAttribute("agendas", agendas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAgendasDoDia.jsp");
            dispatcher.forward(request, response);
        } else if ("darBaixa".equals(action)) {
            // Dar baixa na agenda
            Integer id = Integer.parseInt(request.getParameter("id"));
            agendaService.darBaixaNaAgenda(id, SituacaoAgenda.REALIZADA); // Exemplo de dar baixa
            response.sendRedirect("agenda?action=listar");
        } else if ("excluir".equals(action)) {
            // Excluir agenda
            Integer id = Integer.parseInt(request.getParameter("id"));
            agendaService.excluirAgenda(id);
            response.sendRedirect("agenda?action=listar");
        } else {
            // Carregar lista de usuários e vacinas para o formulário
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            List<Vacina> vacinas = vacinaService.listarVacinas();

            request.setAttribute("usuarios", usuarios);
            request.setAttribute("vacinas", vacinas);

            // Redireciona para a página de cadastro
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarAgenda.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter os dados do formulário
        Integer usuarioId = Integer.parseInt(request.getParameter("usuario"));
        Integer vacinaId = Integer.parseInt(request.getParameter("vacina"));
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String situacaoStr = request.getParameter("situacao");
        String observacoes = request.getParameter("observacoes");

        // Obter os objetos Usuario e Vacina pelo id
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        Vacina vacina = vacinaService.buscarVacinaPorId(vacinaId);
        SituacaoAgenda situacao = SituacaoAgenda.valueOf(situacaoStr);

        // Convertendo a data para LocalDate
        LocalDate dataInicial = LocalDate.parse(data);

        // Obter o número de doses, a periodicidade e o intervalo
        int numeroDoses = vacina.getDoses();
        int intervalo = vacina.getIntervalo();
        Periodicidade periodicidade = vacina.getPeriodicidade();

        // Criar e salvar a agenda para todas as doses
        for (int i = 0; i < numeroDoses; i++) {
            // Calcular a data da próxima dose com base na periodicidade
            LocalDate novaData;
            switch (periodicidade) {
                case DIA:
                    novaData = dataInicial.plusDays(i * intervalo);
                    break;
                case SEMANAS:
                    novaData = dataInicial.plusWeeks(i * intervalo);
                    break;
                case MESES:
                    novaData = dataInicial.plusMonths(i * intervalo);
                    break;
                case ANOS:
                    novaData = dataInicial.plusYears(i * intervalo);
                    break;
                default:
                    throw new IllegalArgumentException("Periodicidade inválida: " + periodicidade);
            }

            // Criar nova agenda para a dose
            Agenda agenda = new Agenda();
            agenda.setUsuario(usuario);
            agenda.setVacina(vacina);
            agenda.setData(novaData);
            agenda.setHora(hora);
            agenda.setSituacao(situacao);
            agenda.setObservacoes(observacoes);

            // Salvar a agenda
            agendaService.criarAgenda(agenda);
        }

        // Redirecionar para a página de listagem após salvar
        response.sendRedirect("agenda?action=listar");
    }
}
