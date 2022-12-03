<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usu�rio
--%>

<%@page import="categoria.modelo.Categoria"%>
<%@page import="categoria.modelo.CategoriaDAO"%>
<%@page import="usuario.modelo.UsuarioDAO"%>
<%@page import="vendaProdutoItem.modelo.VendaProdutoItem"%>
<%@page import="vendaProdutoItem.modelo.VendaProdutoItemDAO"%>
<%@page import="produto.modelo.ProdutoDAO"%>
<%@page import="produto.modelo.Produto"%>

<%@include file="../header.jsp" %>

<link rel="stylesheet" href="Style/p_admin_opcoes.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<main>
    <h1>Compras Efetuadas</h1>
    
    <%
        if(cliente != null){        
    %>        
           
           <h5> <%= cliente.getId() %> - <b> <%= cliente.getNome() %> </b> </h5>
           
    <%       
                VendaProdutoItemDAO comprasDao = new VendaProdutoItemDAO();
                List<VendaProdutoItem> compras = comprasDao.obter(cliente.getId());
                
                for (VendaProdutoItem cp : compras){
    %>
                             
                    <div> 
                          <label for="idvenda"><b>Id Venda</b></label><br>
                          <%= cp.getVenda().getId() %> <br>
                          <label for="dataehora"><b>Data e hora</b></label><br>
                          <%= cp.getVenda().getDataHora() %> <br>

                          <% 

                               for(Produto pd : cp.getProdutos()){   

                          %>

                              <label for="descricao"><b>Descri��o</b></label><br>
                                        <%= pd.getDescricao() %> <br>
                              <label for="quantidade"><b>Quantidade</b></label><br>                         
                                        <%= pd.getQuantidade() %> <br>
                            <% 
                                }
                            %>  
                          <br>

                         <form action="ExcluirVendaServlet" method="post" > 
                            <input type="hidden" name="id" value="<%= cp.getVenda().getId() %>" required>
                            <button type="submit" class="registerbtn">Deletar</button>
                         </form>
                    </div>       
    <%          }
        }
    %>
    <hr><br>
    <a href="index.jsp" >Voltar</a> 
     
</main>
<%@include file="../footer.jsp" %>