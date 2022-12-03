/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package carrinhoCompra.controle;

import carrinhoCompra.modelo.CarrinhoCompra;
import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author jeffe
 */
public class RemoverCarrinhoCompraItemServlet extends HttpServlet {
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
        String novaString = CarrinhoCompra.removerProduto(cookie.getValue(), produtoId);
        cookie.setValue(novaString);
        response.addCookie(cookie);
        request.setAttribute("mensagem", "Produto removido do carrinho de compra");
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrinho.jsp");
        dispatcher.forward(request, response);
    }
}
