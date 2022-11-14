<%-- 
    Document   : consultarProduto
    Created on : 11 de nov. de 2022, 22:16:45
    Author     : Usuário
--%>

<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="produto.modelo.Produto" %>
<%@page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>categorias</title>
        <link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">
        <link rel="stylesheet" href="Style/p_admin_opcoes.css">
    </head>
<body>

    <header></header>
    <div class = "filete"></div>

    <main>
        
        <div class="barra_dados">
            <h1>PRODUTOS</h1>
        </div>
        
        <div class="container">
             <%
                List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                if (produtos != null) {

             %>
                
                    
                        
                            <p>Descrição</p>
                        
                    
                    <%
                        for (Produto pe : produtos) {

                    %>
                            
                                <p><%= pe.getDescricao() %></p>

                    <%
                        } }else{
<p>nulo</p>
}
                    %>
                    
        </div>

    </main>

    <footer></footer>

</body>
</html>
