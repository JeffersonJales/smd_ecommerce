<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usu�rio
--%>

<%@include file="../header.jsp"%>

<link rel="stylesheet" href="Style/p_admin_opcoes.css">

<main> 
    <div class="barra_dados">
        <svg width="36" height="36" viewBox="0 0 36 36" fill="none" xmlns="https://www.w3.org/2000/svg" class="IconUser">
            <path d="M9 8.52632C9 13.2272 13.038 17.0526 18 17.0526C22.962 17.0526 27 13.2272 27 8.52632C27 3.82547 22.962 0 18 0C13.038 0 9 3.82547 9 8.52632ZM34 36H36V34.1053C36 26.7935 29.718 20.8421 22 20.8421H14C6.28 20.8421 0 26.7935 0 34.1053V36H34Z" fill="#B6BBC2"></path>
        </svg>
        <h1>OP��ES DE ADMINISTRADOR</h1>
    </div>
        
    <div class="container">
            <a href="adm?url=opcoes/opcoesCategoria.jsp" class="button">CATEGORIAS</a><br>
            <a href="adm?url=opcoes/opcoesProdutos.jsp" class="button">PRODUTOS</a><br>
            <a href="adm?url=perfil/perfil_admin.jsp" class="button">DADOS DA CONTA</a><br>
            <a href="adm?url=visualizacaoCompras/visualizacao&remocao.jsp" class="button">VISUALIZA��O E EXCLUS�O DE COMPRAS</a><br>
            <a href="ObterRelatorioClienteServlet" class="button">RELAT�RIOS CLIENTES</a><br>
            <a href="ObterRelatorioEstoqueServlet" class="button">RELAT�RIOS ESTOQUE</a><br>
            <a href="ObterRelatorioFaturamentoServlet" class="button">RELAT�RIOS FATURAMENTO</a><br>
            <a href="index.jsp" class="button">VOLTAR</a>
    </div>

</main>

<%@include file="../footer.jsp" %>