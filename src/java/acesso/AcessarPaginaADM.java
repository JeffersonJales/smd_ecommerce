/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package acesso;

import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author jeffe
 */
public class AcessarPaginaADM extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(!Config.isADM(request)){
            Config.redirectUser(request, response);
        }
        else{
            String destinoJSP = (String) request.getParameter("url");
            
            if(destinoJSP == null){ 
                destinoJSP = "index.jsp";
            }
            else{
                destinoJSP = "WEB-INF/JSP/" + destinoJSP;
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
            dispatcher.forward(request, response);
        }
    }
}
