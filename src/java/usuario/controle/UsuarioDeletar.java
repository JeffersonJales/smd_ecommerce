/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package usuario.controle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author ivana
 */
public class UsuarioDeletar extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String idUsuario = request.getParameter("idUsuario").trim();
        
        try{
            int id = Integer.parseInt(idUsuario);
            usuarioDao.remover(id);
            mensagem = "Usuário removido com sucesso.";
        }
        catch(SQLException ex){
            mensagem = "Usuário não existe ou não foi excluido.";
        }
        catch(NumberFormatException num){
            mensagem = "Usuário inválido.";
        }
        
        HttpSession sessao = request.getSession();
        if(sessao != null)
            sessao.invalidate();
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
