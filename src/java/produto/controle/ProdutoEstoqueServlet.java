/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package produto.controle;

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
public class ProdutoEstoqueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            ProdutoDAO produtoDao = new ProdutoDAO();
            List<Produto> produtos = produtoDao.obterTodosEmEstoque();
            request.setAttribute("produtos", produtos);
        }
        catch(SQLException ex){}
    }

}
