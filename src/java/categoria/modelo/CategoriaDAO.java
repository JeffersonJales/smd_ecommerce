/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categoria.modelo;
 
import java.sql.SQLException;
import java.util.List;
import DatabaseConnection.DatabaseConnection;
import java.sql.ResultSet;
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
        
        System.out.println(categoria);
        return categoria;
    }
    
    
    public List<Categoria> obterTodos() throws SQLException{
        List<Categoria> resultado = new ArrayList();
        DatabaseConnection db = new DatabaseConnection();
        ResultSet queryResult = db.QuerySimple("Select * from categoria");
        
        while(queryResult.next()){
            Categoria categoria = new Categoria();
            categoria.setId(queryResult.getInt("id"));
            categoria.setDescricao(queryResult.getString("descricao"));
            resultado.add(categoria);
        }
        
        return resultado;
    }
}
