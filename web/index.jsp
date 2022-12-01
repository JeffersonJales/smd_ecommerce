

<%@page import="usuario.modelo.Usuario"%>
<%@page import="produto.modelo.Produto"%>
<%@page import="produto.modelo.ProdutoDAO"%>

<%@include file="header.jsp" %>

<%
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtosEmEstoque = produtoDao.obterTodosEmEstoque();
    if (produtosEmEstoque != null) {
%>
    
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
                            Preço <strong>R$ <%= nf.format(pe.getPreco()) %></strong><br/>
                            <a href="AdicionarCarrinhoCompraItemServlet?produtoId=<%= pe.getId() %>" class="btn btn-danger mt-2">Adicionar</a>
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