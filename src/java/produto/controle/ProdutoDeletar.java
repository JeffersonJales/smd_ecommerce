/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package produto.controle;

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
@WebServlet(name = "ProdutoDeletar", urlPatterns = {"/produtoDeletar"})
public class ProdutoDeletar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idProduto = request.getParameter("idProduto");
        int id = 0;
        
        try{
            id = Integer.parseInt(idProduto);
        }
        catch(NumberFormatException ex){
            request.setAttribute("mensagem", "Produto inválido");
        }
        
        try{
            ProdutoDAO produtoDao = new ProdutoDAO();
            produtoDao.remover(id);
            request.setAttribute("mensagem", "Produto excluído.");
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Produto não excluido.");
        } 
        
    }
}
