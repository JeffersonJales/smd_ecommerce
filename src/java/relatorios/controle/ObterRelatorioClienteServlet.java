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
            
            /// Iniciar dados
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<RelatorioCliente> relatorioCliente = new ArrayList();
            Date data_inicio = new Date(System.currentTimeMillis()); 
            Date data_fim = new Date(System.currentTimeMillis()); 
              
            /// Substituindo as datas por datas enviadas via request
            Date data_inicio_request = (Date) request.getAttribute("data_inicio");
            Date data_fim_request = (Date) request.getAttribute("data_fim");
            if(data_inicio_request != null) data_inicio = data_inicio_request;
            if(data_fim_request != null) data_fim = data_inicio_request;
            
            /// Obter relatório
            try{
              relatorioCliente = relatoriosDao.Cliente(data_inicio, data_fim);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            /// Mandar relatório e datas para o front
            request.setAttribute("relatorio", relatorioCliente);
            request.setAttribute("data_inicio", data_inicio);
            request.setAttribute("data_fim", data_fim);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorioCliente.jsp");
            dispatcher.forward(request, response);
        }
    }
}
