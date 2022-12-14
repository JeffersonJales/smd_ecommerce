<%-- 
    Document   : carrinho
    Created on : 27 de nov. de 2022, 19:50:54
    Author     : jeffe
--%>

<%@include file="WEB-INF/JSP/header.jsp" %>

<%@page import="produto.modelo.Produto"%>

<%
    double total = 0;
    if(produtosCarrinhoCompra == null || produtosCarrinhoCompra.size() == 0){
%>
    <h2>Carrinho Vazio</h2>

<% } else { %>

<div class="row row-cols-1 row-cols-md-4 g-4">
<%
        for (CarrinhoCompraItem cci : produtosCarrinhoCompra) {
            total += cci.getProduto().getPreco() * cci.getQuantidade();
%>
        <div class="col">
            <div class="card h-100">
                <img src="..." class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%= cci.getProduto().getDescricao() %></h5>
                    <p class="card-text">
                        Pre?o <strong>R$ <%= nf.format(cci.getProduto().getPreco()) %></strong><br/>
                        <a href="RemoverCarrinhoCompraItemServlet?produtoId=<%= cci.getProduto().getId() %>" class="btn btn-success mt-2">Remover</a>
                    </p>
                </div>
                <div class="card-footer"><small class="text-muted">Quantidade: <strong><%= cci.getQuantidade() %></strong></small></div>
            </div>
        </div>

<%      } %> 
</div> 

<h5> Pre?o total: <%= nf.format(total)%>  </h5>
<form action="cadastrarVenda" method="POST">
    <button type="submit" class="registerbtn">Finalizar compra</button>
</form>
<% } %>

<%@include file="WEB-INF/JSP/footer.jsp" %>