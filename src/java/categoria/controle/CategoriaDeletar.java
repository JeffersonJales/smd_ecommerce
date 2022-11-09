/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoria.controle;

import categoria.modelo.CategoriaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author ivana
 */
@WebServlet(name = "CategoriaDeletar", urlPatterns = {"/categoriaDeletar"})
public class CategoriaDeletar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

        String id = request.getParameter("id");
        
        try{
            int idCategoria = Integer.parseInt(id);
            CategoriaDAO categoriaDao = new CategoriaDAO();
            categoriaDao.remover(idCategoria);
            request.setAttribute("mensagem", "Categoria removida com sucesso");
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Não foi possível excluir a categoria");
        }
        catch(NumberFormatException ex){
            request.setAttribute("mensagem", "Categoria inválida");
        }
    }


}
