<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Alergias</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Alergias</h1>

        <!-- Botão para adicionar nova alergia -->
        <div class="mb-3">
            <a href="cadastrarAlergia.html" class="btn btn-primary">Adicionar Alergia</a>
        </div>

        <!-- Tabela de alergias -->
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Nome da Alergia</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alergia" items="${alergias}">
                    <tr>
                        <td>${alergia.nome}</td>
                        <td>
                            <a href="alergia?action=excluirAlergia&id=${alergia.id}" class="btn btn-danger btn-sm">Excluir</a>
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
