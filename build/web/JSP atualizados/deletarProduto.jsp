<%-- 
    Document   : deletarProduto
    Created on : 11 de nov. de 2022, 22:15:51
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form action="produtoDeletar" method="post"  >
                <input type="hidden" name="idUsuario" value="<%= produto.getId()%> " required>
                                <button type="submit" class="registerbtn">Excluir produto</button>
            </form>
        </div>
    </body>
</html>
