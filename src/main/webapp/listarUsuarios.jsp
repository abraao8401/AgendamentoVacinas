<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuários</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Usuários</h1>

        <!-- Botão para adicionar novo usuário -->
        <div class="mb-3">
            <a href="cadastrarUsuario.html" class="btn btn-primary">Adicionar Usuário</a>
        </div>

        <!-- Tabela de usuários -->
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Nome</th>
                    <th>Data de Nascimento</th>
                    <th>Sexo</th>
                    <th>Endereço</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.nome}</td>
                        <td>${usuario.dataNascimento}</td>
                        <td>${usuario.sexo}</td>
                        <td>${usuario.logradouro}, ${usuario.numero}, ${usuario.setor}, ${usuario.cidade}, ${usuario.uf}</td>
                        <td>
                            <a href="usuario?action=verAgendas&id=${usuario.id}" class="btn btn-info btn-sm">Ver Agendas</a>
                            <a href="usuario?action=excluirUsuario&id=${usuario.id}" class="btn btn-danger btn-sm">Excluir</a>
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
