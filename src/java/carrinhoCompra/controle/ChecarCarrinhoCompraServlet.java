/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package carrinhoCompra.controle;

import carrinhoCompra.modelo.CarrinhoCompra;
import carrinhoCompra.modelo.CarrinhoCompraItem;
import config.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jeffe
 */
public class ChecarCarrinhoCompraServlet extends HttpServlet {

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
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        Cookie carrinhoCompra = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(Config.COOKIE_CARRINHOCOMPRA)) {
                carrinhoCompra = cookies[i];
                break;
            }
        }
        if (carrinhoCompra == null) {
            carrinhoCompra = new Cookie(Config.COOKIE_CARRINHOCOMPRA, "");
        } else {
            List<CarrinhoCompraItem> produtosCarrinhoCompra = (List<CarrinhoCompraItem>) CarrinhoCompra.obterCarrinhoCompra(carrinhoCompra.getValue());
            if (produtosCarrinhoCompra != null && !produtosCarrinhoCompra.isEmpty()) {
                request.setAttribute("produtosCarrinhoCompra", produtosCarrinhoCompra);
            }
        }
        
        carrinhoCompra.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(carrinhoCompra);
    }
}
