/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package venda.controle;

import carrinhoCompra.controle.ObterCarrinhoCompra;
import carrinhoCompra.modelo.CarrinhoCompraItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import produto.modelo.ProdutoDAO;
import produto.modelo.Produto;

/**
 *
 * @author jeffe
 */
public class CadastrarVenda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem = "";
        String destinoJSP = "index.jsp";
        HttpSession sessao = request.getSession(false);
        
        // Sem sessão
        if(sessao == null ){
            mensagem = "Para finalizar a compra, faça login na sua conta!";
            destinoJSP = "login.jsp";
        }
        else{
            /// Checar Carrinho 
            ObterCarrinhoCompra.Obter(request, response);
            List<CarrinhoCompraItem> produtosCarrinhoCompra = (List<CarrinhoCompraItem>) request.getAttribute("produtosCarrinhoCompra");

            if(produtosCarrinhoCompra == null){
                mensagem = "Carrinho está vazio!";
                destinoJSP = "carrinho.jsp";
            }
            
            /// Checar Estoque de produtos
            else{
                String mensagemFalha = "Alguns produtos não tem a quantidade pedida em estoque:";
                ProdutoDAO produtoDao = new ProdutoDAO();
                Produto produto;
                
                try{
                    
                    boolean itemForaEstoque = false;
                    for(CarrinhoCompraItem cci : produtosCarrinhoCompra){
                        produto = produtoDao.obter(cci.getProduto().getId()); /// database request
                        if(produto.getQuantidade() < cci.getQuantidade()){
                            mensagemFalha += "\n" + produto.getDescricao() + 
                                    " tem apenas " + 
                                    String.valueOf(produto.getQuantidade()) + 
                                    " em estoque.";
                            
                            itemForaEstoque = true;
                        }
                    }
                    
                    if(itemForaEstoque){
                        mensagem = mensagemFalha;
                        destinoJSP = "carrinho.jsp";
                    }
                    else{
                        mensagem = "Compra vai ser efetuada";
                        destinoJSP = "carrinho.jsp";
                    }
                }
                catch(SQLException ex){}
            }
            
        }
        
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
        
    }
}
