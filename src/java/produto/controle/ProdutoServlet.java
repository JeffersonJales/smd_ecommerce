package produto.controle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author ivana
 */
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idProduto = request.getParameter("idProduto");
        ProdutoDAO produtoDao = new ProdutoDAO();
        
        
        /// Obter todos os produtos 
        if(idProduto.isBlank()){
            try{
                List<Produto> produtos = produtoDao.obterTodos();
                request.setAttribute("produtos", produtos);
            }
            catch(SQLException ex){}
            
        }
        
        /// Obter produto específico
        else{
            try{
                int id = Integer.parseInt(idProduto);
                Produto produto = produtoDao.obter(id);
                request.setAttribute("produto", produto);
            }
            catch(SQLException ex){}
        }
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String descricao = request.getParameter("descricaoProduto");
        String preco = request.getParameter("precoProduto");
        String quantidade = request.getParameter("quantidadeProduto");
        String foto = request.getParameter("fotoProduto");
        
        double precoConvertido = 0;
        int quantidadeConvertido = 0;
        
        try{
            precoConvertido = Double.parseDouble(preco);
            quantidadeConvertido = Integer.parseInt(quantidade);
        }
        catch(NumberFormatException ex){
            request.setAttribute("mensagem", "Valores inválidos");
        }
        
        try{
            ProdutoDAO produtoDao = new ProdutoDAO();
            produtoDao.inserir(descricao, precoConvertido, quantidadeConvertido, foto);
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Produto não cadastrado.");
        } 
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("opcoesProdutos.jsp");
        dispatcher.forward(request, response);
    }

}
