<%-- 
    Document   : relatorioClientes
    Created on : 1 de dez. de 2022, 19:40:08
    Author     : jeffe
--%>
<%@include file="../../../header.jsp" %>

<%@page import="relatorios.modelo.RelatorioCliente" %>
<% 
    List<RelatorioCliente> rc = (List<RelatorioCliente>) request.getAttribute("relatorio");
    String data_fim = (String) request.getAttribute("data_fim");
    String data_inicio = (String) request.getAttribute("data_inicio");
%>


<form action="ObterRelatorioClienteServlet">
    <input type="date" id="data_inicio" name="data_inicio" value=<%= data_inicio%>>
    <input type="date" id="data_fim" name="data_fim" value=<%= data_fim%>>
    <input type="submit">
</form>

<% if(rc.isEmpty()){ %>
    <h4>Nenhum cliente no dado período!</h4>
<%  } 
    else { 
        for(RelatorioCliente rrc : rc){
%>
            <h4>Id: <%= rrc.getUsuario().getId()%> - Nome: <%= rrc.getUsuario().getNome() %> - Quantidade de compras: <%= rrc.getQuantidadeCompras() %></h4>
<%      }
    } 
%>

<%@include file="../../../footer.jsp" %>
