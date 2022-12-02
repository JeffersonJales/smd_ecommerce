<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@include file="header.jsp" %>

<%@page import="categoria.modelo.Categoria"%>
<%@page import="categoria.modelo.CategoriaDAO"%>

<link rel="stylesheet" href="Style/p_admin_opcoes.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<main>
    <h1>Categoria Dashboard</h1>

    <form action="categoria" method="post"  >
        <h4> Adicionar uma nova categoria</h4>
        <label for="descricao"><b>Descrição</b></label>
        <input type="text" placeholder="Descriçao" name="descricao" id="descricao" required>
        <button type="submit" class="registerbtn">Adicionar</button>
    </form>

    <%
        CategoriaDAO categoriaDao = new CategoriaDAO();
        List<Categoria> categorias = categoriaDao.obterTodos();

        if(categorias.isEmpty()){ 
    %>
            <h4> Nenhuma categoria cadastrada </h4>
    <% } 
        else { 
    %>
            <h4> Categorias cadastradas </h4> <br>

    <%       
            for (Categoria ca : categorias) {
    %>

    <div> 
        <form action="categoriaAtualizar" method="post">
            <input type="hidden" name="id" value="<%= ca.getId()%> " required>
            <h5> <%= ca.getId() %> - <b> <%= ca.getDescricao() %> </b> </h5>
            <input type="text" value="<%= ca.getDescricao()%>" name="descricao" id="descricao" required>
            <button type="submit" class="registerbtn">Atualizar </button>
        </form>

        <form action="categoriaDeletar" method="post" > 
            <input type="hidden" name="id" value="<%= ca.getId()%>" required>
            <button type="submit" class="registerbtn">Deletar</button>
        </form>
    </div>
    <%      }
        } 
    %>
</main>
<%@include file="footer.jsp" %>