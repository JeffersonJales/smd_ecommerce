<%-- 
    Document   : deletarCategoria
    Created on : 11 de nov. de 2022, 21:27:54
    Author     : Usuário
--%>
<%@page import="categoria.modelo.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>deletarCategoria</title>
    </head>
    <body>
        <div>
            <form action="categoriaDeletar" method="post"  >
                <input type="hidden" name="idUsuario" value="<%= categoria.getId()%> " required>
                                <button type="submit" class="registerbtn">Excluir categoria</button>
            </form>
        </div>
    </body>
</html>
