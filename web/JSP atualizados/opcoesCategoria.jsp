<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
--%>

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>perfil_admin</title>
        <link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">
        <link rel="stylesheet" href="Style/p_admin_opcoes.css">
    </head>
<body>

    <header></header>
    <div class = "filete"></div>

    <main>
        
        <div class="barra_dados">
            <svg width="36" height="36" viewBox="0 0 36 36" fill="none" xmlns="https://www.w3.org/2000/svg" class="IconUser">
                <path d="M9 8.52632C9 13.2272 13.038 17.0526 18 17.0526C22.962 17.0526 27 13.2272 27 8.52632C27 3.82547 22.962 0 18 0C13.038 0 9 3.82547 9 8.52632ZM34 36H36V34.1053C36 26.7935 29.718 20.8421 22 20.8421H14C6.28 20.8421 0 26.7935 0 34.1053V36H34Z" fill="#B6BBC2"></path>
            </svg>
            <h1>OPÇÕES DE CATEGORIA</h1>
        </div>
        
        <div class="container">
            <a href="consultarCategoria.jsp" class="button">CONSULTAR</a><br>
              <a href="adicionarCategoria.jsp" class="button">INSERIR</a><br>
              <a href="atualizarCategoria.jsp" class="button">ALTERAR</a>
              <a href="deletarCategoria.jsp" class="button">REMOVER</a>
              <a href="perfil_admin_opcoes.jsp" class="button">VOLTAR</a>
        </div>

    </main>

    <footer></footer>

</body>
</html>