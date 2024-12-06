package br.com.agenda.servlet;

import br.com.agenda.model.Alergia;
import br.com.agenda.service.AlergiaService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/alergia")
public class AlergiaServlet extends HttpServlet {

    private AlergiaService alergiaService = new AlergiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            // Listar todas as alergias
            List<Alergia> alergias = alergiaService.listarAlergias();
            request.setAttribute("alergias", alergias);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAlergias.jsp");
            dispatcher.forward(request, response);

        } else if ("excluirAlergia".equals(action)) {
            // Excluir alergia
            Long idAlergia = Long.parseLong(request.getParameter("id"));
            alergiaService.excluirAlergia(idAlergia);
            response.sendRedirect("alergia?action=listar");

        } else {
            // Se a ação for diferente, redireciona para a tela de cadastro
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarAlergia.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Coletando os dados do formulário
        String nome = request.getParameter("nome");

        // Criando o objeto alergia
        Alergia alergia = new Alergia();
        alergia.setNome(nome);

        // Salvando a alergia
        alergiaService.salvarAlergia(alergia);

        // Redirecionando para a lista de alergias
        response.sendRedirect("alergia?action=listar");
    }
}
