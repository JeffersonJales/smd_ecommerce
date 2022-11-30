<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="categoria.modelo.Categoria"%>
<%@page import="categoria.modelo.CategoriaDAO"%>
<%@page import="usuario.modelo.Usuario"%>
<%@page import="usuario.modelo.UsuarioDAO"%>
<%@page import="java.util.Locale"%>

<%@include file="header.jsp" %>

<link rel="stylesheet" href="Style/p_admin_opcoes.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<main>
    <h1>Lista de Clientes e compras efetuadas</h1>
    
    
    <%
        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDao.obterTodos(); /*Existentes*/
        

        if(usuarios.isEmpty()){ 
    %>
            <h4> Nenhuma usuario cadastrado </h4>
    <% } 
        else { 
    %>
            <h4> Clientes cadastrados </h4> <br>

    <%       
            for (Usuario us : usuarios) {  /*for (Compras cp : compras){*/
    %>

    <div> 
          <h5> <%= us.getId() %> - <b> <%= us.getNome() %> </b> </h5>
          <h4>Compras efetuadas</h4>
          <br>
          <label for="login"><b>Login</b></label><br>
          <%= us.getLogin() %>
          <br>
          <label for="email"><b>Email</b></label><br>
          <%= us.getEmail() %>
          <br>
          <label for="enderco"><b>Endereço</b></label><br>
          <%= us.getEndereco() %>
          <br>
          <label for="psw"><b>Password</b></label><br>
          <%= us.getSenha() %><hr><br>

        <form action="#" method="post" > 
            <input type="hidden" name="id" value="<%= us.getId()%>" required>
            <button type="submit" class="registerbtn">Deletar</button>
        </form>
    </div>       
    <%      }
        } 
    %>
    <hr><br>
    <a href="index.jsp" >Voltar</a> 
     
</main>
<%@include file="footer.jsp" %>