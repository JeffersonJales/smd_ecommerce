<%-- 
    Document   : header
    Created on : 27 de nov. de 2022, 13:02:19
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>

<%@page import="java.util.Date" %>
<%@page import="usuario.modelo.Usuario"%>
<%@page import="carrinhoCompra.controle.ObterCarrinhoCompra"%>
<%@page import="carrinhoCompra.modelo.CarrinhoCompraItem"%>
<%@page import="java.util.List"%>

<% 
    NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));

    Usuario cliente = null;
    if(session != null)
        cliente = (Usuario) session.getAttribute("cliente");
    
    ObterCarrinhoCompra.Obter(request,response);
    List<CarrinhoCompraItem> produtosCarrinhoCompra = (List<CarrinhoCompraItem>) request.getAttribute("produtosCarrinhoCompra");
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
                                        <a href="adm?url=perfil/perfil_admin_opcoes.jsp" >PERFIL</a>
                                        <a href="user?url=perfil/perfil_usuario.jsp" >PERFIL</a>
                                    <% } else { %>
                                        <a href="user?url=perfil/perfil_admin_opcoes.jsp" >PERFIL</a>
                                        

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
                        <a id="linkCarrinhoHeader" title="Carrinho" href="carrinho.jsp" class="carrinho"><svg width="20" height="20" viewBox="0 0 24 24" xmlns="https://www.w3.org/2000/svg" class="IconHeaderCart" size="20"><g opacity="0.8"><path d="M7.19975 19.2C5.8798 19.2 4.81184 20.28 4.81184 21.6C4.81184 22.92 5.8798 24 7.19975 24C8.51971 24 9.59967 22.92 9.59967 21.6C9.59967 20.28 8.51971 19.2 7.19975 19.2ZM0 0V2.4H2.39992L6.71977 11.508L5.09982 14.448C4.90783 14.784 4.79984 15.18 4.79984 15.6C4.79984 16.92 5.8798 18 7.19975 18H21.5993V15.6H7.70374C7.53574 15.6 7.40375 15.468 7.40375 15.3L7.43974 15.156L8.51971 13.2H17.4594C18.3594 13.2 19.1513 12.708 19.5593 11.964L23.8552 4.176C23.9542 3.99286 24.004 3.78718 23.9997 3.57904C23.9955 3.37089 23.9373 3.16741 23.8309 2.98847C23.7245 2.80952 23.5736 2.66124 23.3927 2.55809C23.2119 2.45495 23.0074 2.40048 22.7992 2.4H5.05183L3.92387 0H0ZM19.1993 19.2C17.8794 19.2 16.8114 20.28 16.8114 21.6C16.8114 22.92 17.8794 24 19.1993 24C20.5193 24 21.5993 22.92 21.5993 21.6C21.5993 20.28 20.5193 19.2 19.1993 19.2Z" fill="white"></path></g></svg></a>    
                    </div>
                    
                    <% if(produtosCarrinhoCompra != null){ %>
                    <p> <%= produtosCarrinhoCompra.size() %> </p>  
                    <% } %>
                </div>
            </div>  
        </header>
                        
        <div>
            <% if (request.getAttribute("mensagem") != null) { %>
                <div class="mb-3"></div>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong><%= request.getAttribute("mensagem") %></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <% }%>
                <div class="mb-2"></div>
        </div>
         
