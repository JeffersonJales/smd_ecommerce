/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package inicio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import produto.modelo.ProdutoDAO;
import produto.modelo.Produto;

/**
 *
 * @author ivana
 */
public class Inicio extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
         ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtosEmEstoque = produtoDAO.obterTodosEmEstoque();
            request.setAttribute("produtosEmEstoque", produtosEmEstoque);
        } catch (SQLException ex) {
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}
