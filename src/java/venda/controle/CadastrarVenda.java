/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package venda.controle;

import carrinhoCompra.controle.ObterCarrinhoCompra;
import carrinhoCompra.modelo.CarrinhoCompra;
import carrinhoCompra.modelo.CarrinhoCompraItem;
import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import produto.modelo.ProdutoDAO;
import produto.modelo.Produto;
import usuario.modelo.Usuario;
import venda.modelo.Venda;
import venda.modelo.VendaDAO;
import produtoVenda.modelo.ProdutoVendaDAO;

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
        Usuario cliente = null;
        
        /// Pegar cliente da sessão
        if(sessao != null)
            cliente = (Usuario) sessao.getAttribute("cliente");
        
        // Se não tiver cliente, pedir pra logar
        if(cliente == null){
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
                mensagem = "Problema para efetuar a compra. Tente novamente mais tarde";
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
                        System.out.println("Iniciando venda");
                        
                        VendaDAO vendaDao = new VendaDAO();
                        ProdutoVendaDAO produtoVendaDao = new ProdutoVendaDAO();

                        /// Inserir nova venda
                        Venda venda = vendaDao.inserir(cliente.getId());
                        
                        /// Adicionar produto_venda + remover quantidade do estoque
                        for(CarrinhoCompraItem cci : produtosCarrinhoCompra){
                            produtoVendaDao.inserir(
                                    cci.getProduto().getId(), 
                                    venda.getId(), 
                                    cci.getQuantidade());
                            
                            produtoDao.atualizar(
                                    cci.getProduto().getId(), 
                                    cci.getProduto().getDescricao(), 
                                    cci.getProduto().getPreco(),
                                    cci.getProduto().getQuantidade() - cci.getQuantidade(), 
                                    cci.getProduto().getFoto());
                        }
                        
                        /// Limpando carrinho de compras
                        Cookie cookie = new Cookie(Config.COOKIE_CARRINHOCOMPRA, "");
                        Cookie[] cookies = request.getCookies();
                        for (int i = 0; i < cookies.length; i++) {
                            if (cookies[i].getName().equals(Config.COOKIE_CARRINHOCOMPRA)) {
                                cookie = cookies[i];
                                break;
                            }
                        }
                        
                        cookie.setValue("");
                        cookie.setMaxAge(-1);
                        response.addCookie(cookie);
                        request.setAttribute("produtosCarrinhoCompra", null);
                        
                        /// Redirecionando pra pagina inicial
                        mensagem = "Compra efetuada com sucesso";
                        destinoJSP = "index.jsp";
                    }
                }
                catch(SQLException ex){
                    mensagem = ex.getMessage();
                }
            }
            
        }
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
        
    }
}
