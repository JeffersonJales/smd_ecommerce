<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="produto.modelo.Produto"%>
<%@page import="produto.modelo.ProdutoDAO"%>
<%@page import="java.util.Locale"%>

<%@include file="header.jsp" %>
<link rel="stylesheet" href="Style/p_admin_opcoes.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<main>

    <h1>Produto Dashboard</h1>

    <form action="produto" method="post"  >
        <h4> Adicionar um novo produto</h4>
        <label for="descricaoProduto"><b>Descrição</b></label>
        <input type="text" placeholder="Descriçao" name="descricaoProduto" id="descricaoProduto" required>
        <br>
        <label for="precoProduto"><b>Preço</b></label>
        <input type="number" placeholder="9.99" name="precoProduto" id="precoProduto" required>
        <br>
        <label for="quantidadeProduto"><b>Quantidade</b></label>
        <input type="number" placeholder="1" name="quantidadeProduto" id="quantidadeProduto" required>
        <br>
        <button type="submit" class="registerbtn">Adicionar</button>
    </form>

    <%
        ProdutoDAO produtoDao = new ProdutoDAO();
        List<Produto> produtos = produtoDao.obterTodos();

        if(produtos.isEmpty()){ 
    %>
            <h4> Nenhum produto cadastrado </h4>
    <% } 
        else { 
    %>
            <h4> produtos cadastrados </h4> <br>

    <%       
            for (Produto pr : produtos) {
    %>

    <div> 
        <form action="produtoAtualizar" method="post">
            <h5> <%= pr.getId()%> - <b> <%= pr.getDescricao() %> </b> | R$ <b> <%= pr.getPreco() %> </b> | Estoque: <b> <%= pr.getQuantidade() %>  </b>  </h5>
            <input type="hidden" name="idProduto" value="<%= pr.getId()%> " required>
            <label for="descricaoProduto"><b>Descrição</b></label>
            <input type="text" value="<%= pr.getDescricao()%>" name="descricaoProduto" id="descricaoProduto" required>
            <label for="precoProduto"><b>Preço</b></label>
            <input type="number" min="1.00" name="precoProduto" value="<%= pr.getPreco()%>" required>
            <label for="quantidadeProduto"><b>Quantidade</b></label>
            <input type="number" min="0" name="quantidadeProduto" value="<%= pr.getQuantidade()%>" required>
            <button type="submit" class="registerbtn">Atualizar </button>
        </form>

        <form action="produtoDeletar" method="post" > 
            <input type="hidden" name="idProduto" value="<%= pr.getId()%>" required>
            <button type="submit" class="registerbtn">Deletar</button>
        </form>
        <br>
    </div>
    <%      }
        } 
    %>

</main>
    
<%@include file="footer.jsp" %>