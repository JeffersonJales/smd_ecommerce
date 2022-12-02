<%-- 
    Document   : login]
    Created on : 14/10/2022, 17:36:05
    Author     : Usuário
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
    <h1>Lista de Clientes e compras efetuadas</h1>
    
    <%
        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDao.obterTodos();

        if(usuarios.isEmpty()){ 
    %>
            <h4> Nenhuma usuario cadastrado </h4>
    <% } 
        else { 
    %>
            <h4> Clientes cadastrados </h4> <br>

    <%       
            for (Usuario us : usuarios) {  /*for (Compras cp : compras){*/
    %>
          
           <h5> <%= us.getId() %> - <b> <%= us.getNome() %> </b> </h5>
           <h4>Compras efetuadas</h4><br>
    <%       
                VendaProdutoItemDAO comprasDao = new VendaProdutoItemDAO();
                List<VendaProdutoItem> compras = comprasDao.obter(us.getId());
                
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
              
              <label for="descricao"><b>Descrição</b></label><br>
                        <%= pd.getDescricao() %> <br>
              <label for="quantidade"><b>Quantidade</b></label><br>                         
                        <%= pd.getQuantidade() %> <br>
            <% 
                }
            %>  
          <br>
          
        <form action="ExcluirVendaServlet" method="post" > 
            <input type="hidden" name="id" value="<%= us.getId() %>" required>
            <button type="submit" class="registerbtn">Deletar</button>
        </form>
    </div>       
    <%      }
        }
     }  
    %>
    <hr><br>
    <a href="index.jsp" >Voltar</a> 
     
</main>
<%@include file="../footer.jsp" %>