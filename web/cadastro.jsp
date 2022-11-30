<%-- 
    Document   : cadastro
    Created on : 14/10/2022, 17:42:23
    Author     : samue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>


<!DOCTYPE html>
<link rel="stylesheet" href="Style/cadastro.css">
<script type="text/javascript" src="Javascript/script.js"></script>

<main>
    <form method="POST" action="usuario" onsubmit="return validateForm()">
        <div class="container">
          <h1>Cadastro</h1>
          <p>Please fill in this form to create an account.</p>
          <hr>

          <label for="name"><b>Name</b></label>
          <input type="text" placeholder="Enter name" name="nome" id="name" required>

          <label for="login"><b>Login</b></label>
          <input type="text" placeholder="Enter login" name="login" id="login" required>

          <label for="email"><b>Email</b></label>
          <input type="text" placeholder="Enter Email" name="email" id="email" required>

          <label for="enderco"><b>Endereço</b></label>
          <input type="text" placeholder="Endereco" name="endereco" id="endereco" required>

          <label for="psw"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="senha" id="psw" required>

          <label for="psw-repeat"><b>Repeat Password</b></label>
          <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
          <hr>
          <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

          <button type="submit" class="registerbtn">Register</button>
        </div>

        <div class="container signin">
            <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
        </div>
    </form>
</main>

<%@include file="footer.jsp" %>