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
import usuario.modelo.Usuario;

/**
 *
 * @author ivana
 */
public class UsuarioAtualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        Usuario usuario = null; 
        UsuarioDAO usuarioDao = new UsuarioDAO();
        
        String id = request.getParameter("id").trim();
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String destinoJSP = "WEB-INF/JSP/perfil/perfil_usuario.jsp";
        
        try{
            int idUsuario = Integer.parseInt(id);
            usuarioDao.atualizarCadastro(idUsuario, nome, endereco, email);
            usuario = usuarioDao.obter(idUsuario);
            mensagem = "Dados cadastrais do usuário atualizados com sucesso";
        }
         catch(SQLException ex){
            mensagem = "Não foi possível atualizar cadastro.";
        }
        catch(NumberFormatException num){
            mensagem = "Usuário inválido.";
        }
        
        if(usuario.isAdministrador()){
            destinoJSP = "WEB-INF/JSP/perfil/perfil_admin_opcoes.jsp";
        }
        
        HttpSession sessao = request.getSession(false);
        if(sessao != null){
            sessao.setAttribute("cliente", usuario);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
        request.setAttribute("mensagem", mensagem);
        
    }
}
