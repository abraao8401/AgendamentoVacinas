<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Vacinas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Vacinas</h1>

        <!-- Botão para adicionar nova vacina -->
        <div class="mb-3">
            <a href="cadastrarVacina.html" class="btn btn-primary">Adicionar Vacina</a>
        </div>

        <!-- Tabela de vacinas -->
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Doses</th>
                    <th>Periodicidade</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vacina" items="${vacinas}">
                    <tr>
                        <td>${vacina.titulo}</td>
                        <td>${vacina.descricao}</td>
                        <td>${vacina.doses}</td>
                        <td>
                         <c:choose>
                             <c:when test="${vacina.periodicidade == 'DIA'}">Dia</c:when>
                             <c:when test="${vacina.periodicidade == 'SEMANAS'}">Semanas</c:when>
                             <c:when test="${vacina.periodicidade == 'MESES'}">Meses</c:when>
                             <c:when test="${vacina.periodicidade == 'ANOS'}">Anos</c:when>
                         </c:choose>
                        </td>
                        <td>
                            <a href="vacina?action=excluirVacina&id=${vacina.id}" class="btn btn-danger btn-sm">Excluir</a>
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
