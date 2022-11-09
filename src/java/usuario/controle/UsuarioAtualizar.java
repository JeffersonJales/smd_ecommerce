/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package usuario.controle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author ivana
 */
@WebServlet(name = "UsuarioAtualizar", urlPatterns = {"/usuarioAtualizar"})
public class UsuarioAtualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String destinoJSP = "perfil_usuario.jsp";
        
        String id = request.getParameter("idUsuario");
        String nome = request.getParameter("nomeUsuario");
        String endereco = request.getParameter("enderecoUsuario");
        String email = request.getParameter("emailUsuario");
        
        try{
            int idUsuario = Integer.parseInt(id);
            usuarioDao.atualizarCadastro(idUsuario, nome, endereco, email);
            
            mensagem = "Dados cadastrais do usuário atualizados com sucesso";
            
        }
         catch(SQLException ex){
            mensagem = "Não foi possível atualizar cadastro.";
        }
        catch(NumberFormatException num){
            mensagem = "Usuário inválido.";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
        request.setAttribute("mensagem", mensagem);
        
    }
}
