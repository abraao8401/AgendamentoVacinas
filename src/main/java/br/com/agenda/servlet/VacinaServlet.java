package br.com.agenda.servlet;

import br.com.agenda.enums.Periodicidade;
import br.com.agenda.model.Vacina;
import br.com.agenda.service.VacinaService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/vacina")
public class VacinaServlet extends HttpServlet {

    private VacinaService vacinaService = new VacinaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            // Listar todas as vacinas
            List<Vacina> vacinas = vacinaService.listarVacinas();
            request.setAttribute("vacinas", vacinas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarVacinas.jsp");
            dispatcher.forward(request, response);

        } else if ("excluirVacina".equals(action)) {
            // Excluir vacina
            Integer idVacina = Integer.parseInt(request.getParameter("id"));
            vacinaService.excluirVacina(idVacina);
            response.sendRedirect("vacina?action=listar");

        } else if ("visualizar".equals(action)) {
            // Visualizar detalhes da vacina
            Integer idVacina = Integer.parseInt(request.getParameter("id"));
            Vacina vacina = vacinaService.buscarVacinaPorId(idVacina);
            request.setAttribute("vacina", vacina);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/visualizarVacina.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se a ação for diferente, redireciona para a tela de cadastro
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarVacina.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Coletando os dados do formulário
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        Integer doses = Integer.parseInt(request.getParameter("doses"));
        String periodicidadeStr = request.getParameter("periodicidade");
        Integer intervalo = null;

        if (doses > 1) {
            // Se o número de doses for maior que 1, coletar os dados de periodicidade e intervalo
            String periodicidadeEnum = request.getParameter("periodicidade");
            intervalo = Integer.parseInt(request.getParameter("intervalo"));
        }

        // Criando o objeto vacina
        Vacina vacina = new Vacina();
        vacina.setTitulo(titulo);
        vacina.setDescricao(descricao);
        vacina.setDoses(doses);
        vacina.setPeriodicidade(Periodicidade.fromCodigo(Integer.parseInt(periodicidadeStr))); // Convertendo o valor para o enum
        vacina.setIntervalo(intervalo);

        // Salvando a vacina
        vacinaService.salvarVacina(vacina);

        // Redirecionando para a lista de vacinas
        response.sendRedirect("vacina?action=listar");
    }
}
