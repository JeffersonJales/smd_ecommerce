<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<link rel="stylesheet" href="Style/login.css">
<link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">

<main>
    <div class="retrato">
        <form action="login" method="post">
            <div class="Log">
            <h1>LOGIN</h1>
            </div>

            <div class="container">
                 <% if(request.getAttribute("erro_login") != null) { %>
                <div>
                    <p> <%= request.getAttribute("erro_login") %> </p>
                </div>
                <% } %>

                <label for="uname"><b>Login</b></label>

                <% if(request.getAttribute("login") != null){ %>
                    <input type="text" placeholder="Enter Username" name="login" value="<%=request.getAttribute("login") %>" required>
                <% } else { %>
                    <input type="text" placeholder="Enter Username" name="login" required>
                <% } %>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="senha" required>

                <button class="buttonlogin" type="submit">Login</button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
            </div>

            <div class="container cadastrase">
                <p>Não possui uma conta? <a href="cadastro.jsp">Cadastra-se</a>.</p>
            </div>

        </form>
    </div>
</main>

<%@include file="footer.jsp" %>