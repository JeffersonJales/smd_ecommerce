package categoria.controle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import categoria.modelo.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import categoria.modelo.CategoriaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cindydamasceno
 */
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoriaDAO categoriaDao = new CategoriaDAO();
        List<Categoria> categorias = new ArrayList();
        
        try{
            categorias = categoriaDao.obterTodos();
            
        }
        catch (SQLException ex) {
            
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoriaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            for(int i = 0; i < categorias.size(); i++){
                Categoria categoria = categorias.get(i);
                String descricao = categoria.getDescricao();
                out.println("<h1>" + descricao + "</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
