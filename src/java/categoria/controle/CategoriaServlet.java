package categoria.controle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import categoria.modelo.Categoria;
import categoria.modelo.CategoriaDAO;
import jakarta.servlet.RequestDispatcher;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author cindydamasceno
 */
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

        CategoriaDAO categoriaDao = new CategoriaDAO();
        String idCategoria = request.getParameter("idCategoria");
        
        /// Obter todas categorias
      
        if(idCategoria.isBlank()){
            try{
                List<Categoria> categorias = categoriaDao.obterTodos();
                request.setAttribute("categorias", categorias);
            }
            catch(SQLException ex){}
        }
        
        /// Obter categoria específica
        else {
            try{
                int id = Integer.parseInt(idCategoria);
                Categoria categoria = categoriaDao.obter(id);
                request.setAttribute("categoria", categoria);
            }
            catch(SQLException ex){
                request.setAttribute("mensagem", "Nenhuma categoria cadastrada");
            }
            catch(NumberFormatException ex){
                request.setAttribute("mensagem", "Categoria inválida");
            }
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        String descricao = request.getParameter("descricao");
        
        try{
            CategoriaDAO categoriaDao = new CategoriaDAO();
            categoriaDao.inserir(descricao);
            request.setAttribute("mensagem", "Categoria cadastrada com sucesso");
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Categoria não cadastrada");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("perfil_admin_opcoes.jsp");
        dispatcher.forward(request, response);
    }
    

}
