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
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            Config.redirectUser(request, response);
        }
        else {
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<RelatorioFaturamento> relatorioFaturamento = new ArrayList();
            String dataInicio;
            String dataFim;
            
            /// Substituindo as datas por datas enviadas via request
            String data_inicio_request = (String) request.getParameter("data_inicio");
            String data_fim_request = (String) request.getParameter("data_fim");
            
            LocalDateTime data = LocalDateTime.now();
            int day = data.getDayOfMonth(); 
            String dataString = data.getYear() + "-" + data.getMonthValue() + "-" + (day <= 9 ? "0" : "") + day;
            
            if(data_inicio_request != null) dataInicio = data_inicio_request;
            else dataInicio = dataString;
            
            if(data_fim_request != null) dataFim = data_fim_request;
            else dataFim = dataString;
            
            try{
                relatorioFaturamento = relatoriosDao.Faturamento(dataInicio, dataFim);
            }
            catch(SQLException ex){}
            
            request.setAttribute("relatorio", relatorioFaturamento);
            request.setAttribute("data_inicio", dataInicio);
            request.setAttribute("data_fim", dataFim);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorio/relatorioVendas.jsp");
            dispatcher.forward(request, response);
        }
    }
}
