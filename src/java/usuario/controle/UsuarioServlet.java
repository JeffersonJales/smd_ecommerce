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
import usuario.modelo.Usuario;

/**
 *
 * @author ivana
 */
public class UsuarioServlet extends HttpServlet {
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario;
        
        String id = request.getParameter("idUsuario");
        
        try{
            int idUsuario = Integer.parseInt(id);
            usuario = usuarioDao.obter(idUsuario); 
            
            request.setAttribute("nomeUsuario", usuario.getNome());
            request.setAttribute("enderecoUsuario", usuario.getEndereco());
            request.setAttribute("emailUsuario", usuario.getNome());
            mensagem = "Dados cadastrais do usuário recuperados.";
        }
         catch(SQLException ex){
            mensagem = "Não foi possível atualizar cadastro.";
        }
        catch(NumberFormatException num){
            mensagem = "Usuário inválido.";
        }
        
        request.setAttribute("mensagem", mensagem);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        String mensagem;
        String destinoJSP = "login.jsp";
        
        try {
            usuarioDAO.inserir(nome, endereco, email, login, senha);
            mensagem = "Cliente inserido com sucesso";
        } catch (SQLException ex) {
            mensagem = "Não foi possível inserir o cliente";
            destinoJSP = "cadastro.jsp";
        }
        
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("nome", nome);
        request.setAttribute("endereco", endereco);
        request.setAttribute("email", email);
        request.setAttribute("login", login);

        RequestDispatcher dispatcher = request.getRequestDispatcher(destinoJSP);
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        
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
        
        request.setAttribute("mensagem", mensagem);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensagem;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String idUsuario = request.getParameter("idUsuario");
        
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
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
}
