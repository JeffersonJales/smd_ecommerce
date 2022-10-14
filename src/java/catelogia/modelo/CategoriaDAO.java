/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catelogia.modelo;
 
import java.sql.SQLException;
import java.util.List;
import DatabaseConnection.DatabaseConnection;
import java.util.ArrayList;

/**
 *
 * @author cindydamasceno
 */
public class CategoriaDAO {
    public Categoria obter(int id) throws SQLException {
        Categoria categoria = new Categoria();
        
        DatabaseConnection db = new DatabaseConnection();
        List<Object> itens = new ArrayList();
        itens.add(id);
        
        db.QueryPrepare("Select * from Categoria where id = ?", itens);
        
        System.out.println(categoria);
        return categoria;
    }
    
}
