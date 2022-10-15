<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LOGIN</title>
    <link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">
    <link rel="stylesheet" href="Style/login.css">
</head>
<body>

    <header></header>
    <div class = "filete"></div>

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
                        
                    <button type="submit">Login</button>
                    <label>
                        <input type="checkbox" checked="checked" name="remember"> Remember me
                    </label>
                </div>
            
                <div class="container" style="background-color:#f1f1f1">
                    <a href="../index.html"><button type="button" class="cancelbtn" >Cancel</button></a>
                    <span class="psw">Forgot <a href="#">password?</a></span>
                </div>

                <div class="container cadastrase">
                    <p>Não possui uma conta? <a href="cadastro.jsp">Cadastra-se</a>.</p>
                </div>

            </form>
        </div>

    </main>

    <footer></footer>

</body>
</html>
