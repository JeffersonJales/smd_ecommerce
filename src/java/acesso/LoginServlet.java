/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package acesso;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import usuario.modelo.Usuario;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author cindydamasceno
 */
public class LoginServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
    
        boolean sucesso = false;
        try {
            Usuario usuario = usuarioDAO.obter(login);
            if (usuario.getSenha().equals(senha)) {
                HttpSession sessao = request.getSession(true);
                sessao.setAttribute("cliente", usuario);
                request.setAttribute("usuario", usuario);

                sucesso = true;
            }
        } catch (SQLException ex) {
            
        }
        if (!sucesso) {
            request.setAttribute("erro_login", "Login ou senha incorreta");
            request.setAttribute("login", login);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        
    }

}
