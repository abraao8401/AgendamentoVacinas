<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendas do Usuário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Agendas do Usuário</h1>

        <!-- Tabela de agendas -->
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Vacina</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Situação</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="agenda" items="${agendas}">
                    <tr>
                        <td>${agenda.id}</td>
                        <td>${agenda.vacina.titulo}</td>
                        <td>${agenda.data}</td>
                        <td>${agenda.hora}</td>
                        <td>${agenda.situacao}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Botão para voltar à lista de usuários -->
        <a href="usuario?action=listar" class="btn btn-primary">Voltar à Lista de Usuários</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
