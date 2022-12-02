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
            Date data_inicio = new Date(System.currentTimeMillis()); 
            Date data_fim = new Date(System.currentTimeMillis()); 

             /// Substituindo as datas por datas enviadas via request
            Date data_inicio_request = (Date) request.getAttribute("data_inicio");
            Date data_fim_request = (Date) request.getAttribute("data_fim");
            if(data_inicio_request != null) data_inicio = data_inicio_request;
            if(data_fim_request != null) data_fim = data_inicio_request;
           
            try{
              relatorioFaturamento = relatoriosDao.Faturamento(data_inicio, data_fim);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            request.setAttribute("relatorio", relatorioFaturamento);
            request.setAttribute("data_inicio", data_inicio);
            request.setAttribute("data_fim", data_fim);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorioFaturamento.jsp");
            dispatcher.forward(request, response);
        }
    }
}
