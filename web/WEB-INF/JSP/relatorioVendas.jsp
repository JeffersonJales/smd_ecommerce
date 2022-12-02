<%-- 
    Document   : relatorioVendas
    Created on : 1 de dez. de 2022, 19:40:19
    Author     : jeffe
--%>

<%@include file="../../header.jsp" %>

<%@page import="relatorios.modelo.RelatorioFaturamento" %>
<% 
    List<RelatorioFaturamento> rf = (List<RelatorioFaturamento>) request.getAttribute("relatorio");
    String data_fim = (String) request.getAttribute("data_fim");
    String data_inicio = (String) request.getAttribute("data_inicio");
    double faturamentoTotal = 0;
%>


<form action="ObterRelatorioFaturamentoServlet">
    <input type="date" id="data_inicio" name="data_inicio" value=<%= data_inicio%>>
    <input type="date" id="data_fim" name="data_fim" value=<%= data_fim%>>
    <input type="submit">
</form>

<% if(rf.isEmpty()){ %>
    <h4>Nenhum faturamento no dado período!</h4>
<%  } 
    else { 
        for(RelatorioFaturamento rft : rf){
            faturamentoTotal += rft.getValor();
        }
%>
    <h2> Faturamento total no período: R$ <%= nf.format(faturamentoTotal)%> </h2>
<%
    for(RelatorioFaturamento rft : rf){
%>
    <h4>Dia: <%= rft.getData() %> - Faturamento do dia: R$ <%= nf.format(rft.getValor()) %></h4>
<%      }
    } 
%>

<%@include file="../../footer.jsp" %>