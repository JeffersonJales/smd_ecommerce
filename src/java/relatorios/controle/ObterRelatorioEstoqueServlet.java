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
import java.util.List;
import produto.modelo.Produto;
import relatorios.modelo.RelatoriosDAO;

/**
 *
 * @author jeffe
 */
public class ObterRelatorioEstoqueServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        if(!Config.isADM(request)){
            Config.redirectNotAdm(request, response);
        }
        else {
            RelatoriosDAO relatoriosDao = new RelatoriosDAO();
            List<Produto> produtos = new ArrayList();
            
            try{
                produtos = relatoriosDao.Estoque();
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", ex.getMessage());
            }
            
            request.setAttribute("relatorio", produtos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/relatorioEstoque.jsp");
            dispatcher.forward(request, response);
        }
    }
    
}
