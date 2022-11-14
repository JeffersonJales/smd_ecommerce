/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package produto.controle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author ivana
 */
public class ProdutoAtualizar extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("idProduto");
        if (id != null){
            id = id.trim();
        }
        String descricao = request.getParameter("descricaoProduto");
        String preco = request.getParameter("precoProduto");
        String quantidade = request.getParameter("quantidadeProduto");
        String foto = request.getParameter("fotoProduto");
        
        double precoConvertido = 0;
        int quantidadeConvertido = 0;
        int idConvertido = 0;
        try{
            idConvertido = Integer.parseInt(id);
            precoConvertido = Double.parseDouble(preco);
            quantidadeConvertido = Integer.parseInt(quantidade);
        }
        catch(NumberFormatException ex){
            request.setAttribute("mensagem", "Valores inválidos");
        }
        
        try{
            ProdutoDAO produtoDao = new ProdutoDAO();
            produtoDao.atualizar(idConvertido, descricao, precoConvertido, quantidadeConvertido, foto);
            request.setAttribute("mensagem", "Produto atualizado.");
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Produto não atualizado.");
        } 
        RequestDispatcher dispatcher = request.getRequestDispatcher("opcoesProdutos.jsp");
        dispatcher.forward(request, response);
    }
}
