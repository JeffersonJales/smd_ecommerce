/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package carrinhoCompra.controle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import carrinhoCompra.modelo.CarrinhoCompra;
import carrinhoCompra.modelo.CarrinhoCompraItem;
import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;


/**
 *
 * @author jeffe
 */
public class AdicionarCarrinhoCompraItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(Config.COOKIE_CARRINHOCOMPRA)) {
                cookie = cookies[i];
                break;
            }
        }
        String novaString = CarrinhoCompra.adicionarProduto(cookie.getValue(), produtoId, 1);
        cookie.setValue(novaString);
        response.addCookie(cookie);
        request.setAttribute("mensagem", "Produto adicionado ou atualizado no carrinho de compra");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);  
    }
}
