/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoria.controle;

import categoria.modelo.CategoriaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author ivana
 */
public class CategoriaAtualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        String id = request.getParameter("id").trim();
        String descricao = request.getParameter("descricao");
        
        try{
            int idCategoria = Integer.parseInt(id);
            CategoriaDAO categoriaDao = new CategoriaDAO();
            categoriaDao.atualizar(idCategoria, descricao);
            request.setAttribute("mensagem", "Categoria atualizada com sucesso");
        }
        catch(SQLException ex){
            request.setAttribute("mensagem", "Categoria não cadastrada");
        }
        catch(NumberFormatException ex){
            request.setAttribute("mensagem", "Categoria inválida");
        }
    }
}
