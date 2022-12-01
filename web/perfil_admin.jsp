<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">
<link rel="stylesheet" href="Style/p_admin.css">

<main>
    <div class="barra_dados">
        <svg width="36" height="36" viewBox="0 0 36 36" fill="none" xmlns="https://www.w3.org/2000/svg" class="IconUser">
            <path d="M9 8.52632C9 13.2272 13.038 17.0526 18 17.0526C22.962 17.0526 27 13.2272 27 8.52632C27 3.82547 22.962 0 18 0C13.038 0 9 3.82547 9 8.52632ZM34 36H36V34.1053C36 26.7935 29.718 20.8421 22 20.8421H14C6.28 20.8421 0 26.7935 0 34.1053V36H34Z" fill="#B6BBC2"></path>
        </svg>
        <h1>MEUS DADOS</h1>
    </div>

    <div class="container">

        <% 
            if(cliente != null){        
        %>        
            <!-- Para o usuario normal -->
            <label for="name"><b>Name</b></label>
            <br>
            <%= cliente.getNome() %>
            <hr><br>
            <label for="login"><b>Login</b></label>
            <br>
            <%= cliente.getLogin() %>
            <hr><br>
            <label for="email"><b>Email</b></label>
            <br>
            <%= cliente.getEmail() %>
            <hr><br>
            <label for="enderco"><b>Endereço</b></label>
            <br>
            <%= cliente.getEndereco() %>
            <hr><br>
            <label for="psw"><b>Password</b></label>
            <br>
            <%= cliente.getSenha() %>
            <hr><br>

            <div class="user_op">

                <a href="alterar_dados.jsp" >Alterar Dados</a><br>
                <a href="visualizacaoCompras.jsp" >Visualizar Compras</a><br>
                <form method="post" action="usuarioDeletar" >
                    <input type="hidden" name="idUsuario" value="<%= cliente.getId()%> " required>
                    <button type="submit" class="registerbtn">Excluir Dados de usuario</button>
                </form><br>  

                <a href="index.jsp" >Voltar</a> 
            </div>

        <% } %>
    </div>
</main>

<%@include file="footer.jsp" %>