<%-- 
    Document   : header
    Created on : 27 de nov. de 2022, 13:02:19
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="usuario.modelo.Usuario"%>

<% 
    Usuario cliente = null;
    if(session != null)
        cliente = (Usuario) session.getAttribute("cliente");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Pichau </title>
        <link rel="shortcut icon" href="img/Dooffy-Characters-K1.ico" type="image/x-icon">
        <link rel="stylesheet" href="Style/Style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    </head>
    
    <body>
        <header>
            <div class="barra1">
                <a href="/smd_ecommerce"> <div class="logo"> </div> </a>
                
                <div class="user">
                    <div class="user_logo">
                        <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="https://www.w3.org/2000/svg" class="IconDefaultProfileLogo"><mask id="mask0" mask-type="alpha" maskUnits="userSpaceOnUse" x="0" y="0" width="28" height="28"><path fill-rule="evenodd" clip-rule="evenodd" d="M18.6375 13.5625C20.6063 12.2062 21.875 9.975 21.875 7.4375C21.875 3.325 18.55 0 14.4375 0C10.325 0 7 3.325 7 7.4375C7 9.975 8.26875 12.2062 10.2375 13.5625C6.34375 14.7875 3.5 18.4625 3.5 22.75V28H25.375V22.75C25.375 18.4625 22.5312 14.7875 18.6375 13.5625Z" fill="#347BBE"></path></mask><g mask="url(#mask0)"><circle cx="14.5" cy="11.5" r="15.5" fill="#C4C4C4"></circle></g></svg>
                    </div>

                        <div class="login_cadastro">

                        <% 
                            if(cliente != null){
                        %>

                            <span> 
                                Olá <%= cliente.getNome() %>

                                <% if(cliente.isAdministrador()) { %>
                                ADM
                                <% } %>

                                <br> 
                                <form action="logout", method="POST">

                                    <% if(cliente.isAdministrador()) { %>  <!-- inverter depois de finalizar o conteudo do if com o else -->

                                        <a href="perfil_admin_opcoes.jsp" >PERFIL</a>
                                    <% } else { %>
                                        <a href="perfil_usuario.jsp" >PERFIL</a>

                                    <% } %>
                                    <button type="submit">Logout</button>
                                </form>
                            </span> 
                        <% } else { %>

                            <span>
                                Faça <a id="linkLoginHeader" href="login.jsp" class="sc-gJwTLC iJZmHT">LOGIN</a> 
                                ou 
                                <br>crie seu <a id="linkCadastroHeader" href="cadastro.jsp" class="sc-gJwTLC iJZmHT">CADASTRO</a>
                            </span>

                        <% } %>
                        </div>
                </div>
                <div class="barra_ferramentas">
                    <div class="carrinho">
                        <a id="linkCarrinhoHeader" title="Carrinho" href="/carrinho" class="carrinho"><svg width="20" height="20" viewBox="0 0 24 24" xmlns="https://www.w3.org/2000/svg" class="IconHeaderCart" size="20"><g opacity="0.8"><path d="M7.19975 19.2C5.8798 19.2 4.81184 20.28 4.81184 21.6C4.81184 22.92 5.8798 24 7.19975 24C8.51971 24 9.59967 22.92 9.59967 21.6C9.59967 20.28 8.51971 19.2 7.19975 19.2ZM0 0V2.4H2.39992L6.71977 11.508L5.09982 14.448C4.90783 14.784 4.79984 15.18 4.79984 15.6C4.79984 16.92 5.8798 18 7.19975 18H21.5993V15.6H7.70374C7.53574 15.6 7.40375 15.468 7.40375 15.3L7.43974 15.156L8.51971 13.2H17.4594C18.3594 13.2 19.1513 12.708 19.5593 11.964L23.8552 4.176C23.9542 3.99286 24.004 3.78718 23.9997 3.57904C23.9955 3.37089 23.9373 3.16741 23.8309 2.98847C23.7245 2.80952 23.5736 2.66124 23.3927 2.55809C23.2119 2.45495 23.0074 2.40048 22.7992 2.4H5.05183L3.92387 0H0ZM19.1993 19.2C17.8794 19.2 16.8114 20.28 16.8114 21.6C16.8114 22.92 17.8794 24 19.1993 24C20.5193 24 21.5993 22.92 21.5993 21.6C21.5993 20.28 20.5193 19.2 19.1993 19.2Z" fill="white"></path></g></svg></a>    
                    </div>
                </div>

            </div>
        </header>
