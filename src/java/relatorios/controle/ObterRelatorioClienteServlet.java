/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package relatorios.controle;

import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import relatorios.modelo.RelatoriosDAO;
import relatorios.modelo.RelatorioCliente;

/**
 *
 * @author jeffe
 */
public class ObterRelatorioClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        if(!Config.isADM(request)){
            Config.redirectNotAdm(request, response);
        }
        else {
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<RelatorioCliente> relatorioCliente = new ArrayList();
            Date date = new Date(System.currentTimeMillis()); 

            try{
              relatorioCliente = relatoriosDao.Cliente(date, date);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            request.setAttribute("data", date);
            request.setAttribute("relatorio", relatorioCliente);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorioCliente.jsp");
            dispatcher.forward(request, response);
        }
    }
}
