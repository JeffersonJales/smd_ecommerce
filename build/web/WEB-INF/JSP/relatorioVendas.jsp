<%-- 
    Document   : relatorioVendas
    Created on : 1 de dez. de 2022, 19:40:19
    Author     : jeffe
--%>


<%@page import="relatorios.modelo.RelatorioFaturamento" %>
<% 
    List<RelatorioFaturamento> rf = (List<RelatorioFaturamento) request.getAttribute("relatorio");
    Date data_fim = (Date) request.getAttribute("data_fim");
    Date data_inicio = (Date) request.getAttribute("data_inicio");
    
    if(data_fim == null) data_fim = new Date(0);
    if(data_inicio == null) data_inicio = new Date(0);
%>

<%@include file="../../header.jsp" %>

<%@include file="../../footer.jsp" %>