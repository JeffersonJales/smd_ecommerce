

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="produto.modelo.Produto"%>
<%@page import="produto.modelo.ProdutoDAO"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>

<%@include file="header.jsp" %>

<%
    NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtosEmEstoque = produtoDao.obterTodosEmEstoque();
    if (produtosEmEstoque != null) {
%>
    
<nav>
    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn"> DEPARTAMENTOS 
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <a href="#">Computadores</a>
                <a href="#">Processadores</a>
                <a href="#">Placas mãe</a>
                <a href="#">Placas de video</a>
            </div>
        </div>
        <a href="#">ELETRÔNICOS </a>
        <a href="#">LANÇAMENTOS </a>
        <a href="#">PCGAMER </a>
    </div>    
</nav>

<div class="alert alert-primary mt-3" role="alert">
    Produtos em Estoque
</div>
<div class="row row-cols-1 row-cols-md-4 g-4" style="background: white">
        <%
            for (Produto pe : produtosEmEstoque) {
        %>  
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title"><%= pe.getDescricao() %></h5>
                        <p class="card-text">
                            Preço <strong>R$ <%= numberFormat.format(pe.getPreco()) %></strong><br/>
                            <a href="AdicionarCarrinhoProduto?produtoId=<%= pe.getId() %>" class="btn btn-danger mt-2">Adicionar</a>
                        </p>
                    </div>
                    <div class="card-footer"><small class="text-muted">Quantidade: <strong><%= pe.getQuantidade() %></strong></small></div>
                </div>
            </div>
        <% } 
    }
    else { %>
        <h4>Nenhum produto encontrado</h4>
    <% 
        }
    %> 
    </div>
    
<%@include file="footer.jsp" %>