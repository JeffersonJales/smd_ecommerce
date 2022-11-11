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
import java.io.IOException;
import java.sql.SQLException;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author ivana
 */
public class UsuarioAtualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String destinoJSP = "perfil_usuario.jsp";
        
        String id = request.getParameter("id").trim();
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        
        try{
            int idUsuario = Integer.parseInt(id);
            System.out.println("IdUsuario:" + id);
            System.out.println("nomeUsuario:" + nome);
            System.out.println("enderecoUsuario:" + endereco);
            System.out.println("emailUsuario:" + email);

            
            usuarioDao.atualizarCadastro(idUsuario, nome, endereco, email);
            
            mensagem = "Dados cadastrais do usuário atualizados com sucesso";
        }
         catch(SQLException ex){
            mensagem = "Não foi possível atualizar cadastro.";
             System.out.println("SQL ERROR!!!");
        }
        catch(NumberFormatException num){
            mensagem = "Usuário inválido.";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
        request.setAttribute("mensagem", mensagem);
        
    }
}
