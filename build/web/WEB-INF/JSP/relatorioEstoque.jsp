<%-- 
    Document   : relatorioEstoque
    Created on : 1 de dez. de 2022, 19:40:31
    Author     : jeffe
--%>

<%@page import="produto.modelo.Produto" %>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("relatorio");
    
%>

<%@include file="../../header.jsp" %>

<% if(produtos != null && !produtos.isEmpty()) { %>
    <h1> Produtos fora de estoque </h1>
    
    <% for(Produto p : produtos){ %>
        <h4> Id: <%= p.getId() %> - Nome: <%= p.getDescricao() %> - R$<%= nf.format(p.getPreco()) %> </h4>
        <br>
    <% } %>
<% } else { %>
    <h1> Todos os produtos estão estão com estoque! </h1>
<% } %>

<%@include file="../../footer.jsp" %>
