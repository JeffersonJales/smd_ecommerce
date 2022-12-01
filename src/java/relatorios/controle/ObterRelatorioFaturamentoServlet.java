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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import relatorios.modelo.RelatorioFaturamento;
import relatorios.modelo.RelatoriosDAO;

/**
 *
 * @author jeffe
 */
public class ObterRelatorioFaturamentoServlet extends HttpServlet {

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
    
        
        if(!Config.isADM(request)){
            Config.redirectNotAdm(request, response);
        }
        else {
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<RelatorioFaturamento> relatorioFaturamento = new ArrayList();
            Date date = new Date(System.currentTimeMillis()); 

            try{
              relatorioFaturamento = relatoriosDao.Faturamento(date, date);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            request.setAttribute("data", date);
            request.setAttribute("relatorio", relatorioFaturamento);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorioFaturamento.jsp");
            dispatcher.forward(request, response);
        }
    }
}
