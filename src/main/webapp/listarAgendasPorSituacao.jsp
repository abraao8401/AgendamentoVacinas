<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Agendas por Situação</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <!-- Título com base no filtro -->
        <h1 class="text-center mb-4">Lista de Agendas - ${filter}</h1>

        <!-- Botões de ações -->
        <div class="mb-3">
            <a href="agenda?action=listar" class="btn btn-primary">Todas as Agendas</a>
            <a href="agenda?action=listar&filter=canceladas" class="btn btn-warning">Agendas Canceladas</a>
            <a href="agenda?action=listar&filter=realizadas" class="btn btn-success">Agendas Realizadas</a>
            <a href="agenda?action=listar&filter=hoje" class="btn btn-info">Agendas do Dia</a>
        </div>

        <!-- Tabela de agendas -->
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Usuário</th>
                    <th>Vacina</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Situação</th>
                    <th>Observações</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="agenda" items="${agendas}">
                    <tr>
                        <td>${agenda.id}</td>
                        <td>${agenda.usuario.nome}</td>
                        <td>${agenda.vacina.titulo}</td>
                        <td>${agenda.data}</td>
                        <td>${agenda.hora}</td>
                        <td>${agenda.situacao}</td>
                        <td>${agenda.observacoes}</td>
                        <td>
                            <c:if test="${agenda.situacao == 'AGENDADA'}">
                                <a href="agenda?action=darBaixa&id=${agenda.id}" class="btn btn-success btn-sm">Dar Baixa</a>
                            </c:if>
                            <a href="agenda?action=excluir&id=${agenda.id}" class="btn btn-danger btn-sm">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
