/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package venda.controle;

import config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import venda.modelo.VendaDAO;

public class ExcluirVendaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(!Config.isADM(request)){
            Config.redirectNotAdm("index.jsp", request, response);
        }
        else{
            String mensagem = "Venda excluida com sucesso!";
            String idVenda = request.getParameter("id").trim();

            try{
                int id = Integer.parseInt(idVenda);
                VendaDAO vendaDao = new VendaDAO();
                vendaDao.remover(id);
            }
            catch(SQLException | NumberFormatException ex){
                mensagem = ex.getMessage();
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("visualizacao&remocao.jsp");
            dispatcher.forward(request, response);
        }
    }        
}
