<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Agenda</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }
        .container h2 {
            text-align: center;
            color: #333333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555555;
        }
        .form-group select,
        .form-group input[type="date"],
        .form-group input[type="time"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .form-group button {
            padding: 10px 15px;
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .form-group .btn-secondary {
            background-color: #2196f3;
            margin-left: 5px;
        }
        .form-group .btn-secondary:hover {
            background-color: #1976d2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Cadastrar Agenda</h2>
        <form action="agenda" method="POST">
            <div class="form-group">
               <label for="usuario">Usuário</label>
               <select id="usuario" name="usuario" class="form-control" required>
                   <option value="">Selecione um usuário</option>
                   <c:forEach var="usuario" items="${usuarios}">
                       <option value="${usuario.id}">${usuario.nome}</option>
                   </c:forEach>
               </select>

               <div class="form-group">
                   <label for="vacina">Vacina</label>
                   <select id="vacina" name="vacina" class="form-control" required>
                       <option value="">Selecione uma vacina</option>
                       <c:forEach var="vacina" items="${vacinas}">
                           <option value="${vacina.id}">${vacina.titulo}</option>
                       </c:forEach>
                   </select>
               </div>

            </div>
            <div class="form-group">
                <label for="data">Data</label>
                <input type="date" id="data" name="data" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="hora">Hora</label>
                <input type="time" id="hora" name="hora" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="situacao">Situação</label>
                <select id="situacao" name="situacao" class="form-control" required>
                    <option value="AGENDADA">Agendada</option>
                    <option value="REALIZADA">Realizada</option>
                    <option value="CANCELADA">Cancelada</option>
                </select>
            </div>
            <div class="form-group">
                <label for="observacoes">Observações</label>
                <textarea id="observacoes" name="observacoes" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Cadastrar Agenda</button>
                <button type="reset" class="btn btn-secondary">Limpar</button>
            </div>
        </form>
    </div>
</body>
</html>
