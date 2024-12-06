package br.com.agenda.servlet;

import br.com.agenda.model.Agenda;
import br.com.agenda.model.Alergia;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.AgendaService;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.service.AlergiaService;  // Supondo que você tenha esse serviço para as alergias

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usuario")

public class UsuarioServlet extends HttpServlet {

    public UsuarioServlet (){}
    private UsuarioService usuarioService = new UsuarioService();
    private AlergiaService alergiaService = new AlergiaService();  // Serviço para alergias
    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            // Listar usuários
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarUsuarios.jsp");
           dispatcher.forward(request, response);

        } else if ("excluirUsuario".equals(action)) {
            // Excluir usuário
            Integer idUsuario = Integer.parseInt(request.getParameter("id"));
            usuarioService.excluirUsuario(idUsuario);
            response.sendRedirect("usuario?action=listar");

        }else if ("verAgendas".equals(action)) {
            // Obter o ID do usuário
            Integer idUsuario = Integer.parseInt(request.getParameter("id"));

            // Buscar as agendas do usuário
            List<Agenda> agendas = agendaService.listarAgendasPorUsuario(idUsuario);

            // Passar as agendas para a página JSP
            request.setAttribute("agendas", agendas);

            // Redireciona para a página que exibe as agendas
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAgendasPorUsuario.jsp");
            dispatcher.forward(request, response);
        } else {
            // Buscar todas as alergias para exibir no cadastro
            List<Alergia> alergias = alergiaService.listarAlergias();
            request.setAttribute("alergias", alergias);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String nome = request.getParameter("nome");
        LocalDate dataNascimento = LocalDate.parse(request.getParameter("dataNascimento"));
        String sexo = request.getParameter("sexo");
        String logradouro = request.getParameter("logradouro");
        Integer numero = Integer.parseInt(request.getParameter("numero"));
        String setor = request.getParameter("setor");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");

        // Tratamento das alergias
        List<Alergia> listaAlergias = new ArrayList<>();
        String quantidadeAlergiasStr = request.getParameter("quantasAlergias");
        if (quantidadeAlergiasStr != null && !quantidadeAlergiasStr.isEmpty()) {
            int quantidadeAlergias = Integer.parseInt(quantidadeAlergiasStr);
            for (int i = 1; i <= quantidadeAlergias; i++) {
                String alergiaNome = request.getParameter("alergia" + i);
                if (alergiaNome != null && !alergiaNome.trim().isEmpty()) {
                    Alergia alergia = buscarOuCriarAlergia(alergiaNome.trim());
                    listaAlergias.add(alergia);
                }
            }
        }

        // Criação do objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setDataNascimento(dataNascimento);
        usuario.setSexo(br.com.agenda.enums.Sexo.valueOf(sexo));
        usuario.setLogradouro(logradouro);
        usuario.setNumero(numero);
        usuario.setSetor(setor);
        usuario.setCidade(cidade);
        usuario.setUf(uf);
        usuario.setAlergias(listaAlergias);

        // Salvar o usuário (exemplo de uso do service)
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.salvarUsuario(usuario);

        //Redirecionamento ou resposta ao cliente
        response.sendRedirect("usuario?action=listar");


    }

    /**
     * Busca uma alergia pelo nome no banco de dados ou cria uma nova, se não existir.
     */
    private Alergia buscarOuCriarAlergia(String nome) {
        AlergiaService alergiaService = new AlergiaService();
        Alergia alergia = alergiaService.buscarAlergiaPorNome(nome);
        if (alergia == null) {
            alergia = new Alergia();
            alergia.setNome(nome);
            alergiaService.salvarAlergia(alergia);
        }
        return alergia;
    }


}