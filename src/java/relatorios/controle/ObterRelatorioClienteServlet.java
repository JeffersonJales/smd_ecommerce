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
            Config.redirectUser(request, response);
        }
        else {
            
            /// Iniciar dados
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<RelatorioCliente> relatorioCliente = new ArrayList();
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

            /// Obter relatório
            try{
              relatorioCliente = relatoriosDao.Cliente(dataInicio, dataFim);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            /// Mandar relatório e datas para o front
            request.setAttribute("relatorio", relatorioCliente);
            request.setAttribute("data_inicio", dataInicio);
            request.setAttribute("data_fim", dataFim);
            
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorio/relatorioClientes.jsp");
            dispatcher.forward(request, response);
        }
    }
    
}
