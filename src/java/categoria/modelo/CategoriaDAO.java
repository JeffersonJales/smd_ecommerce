/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categoria.modelo;
 
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cindydamasceno
 */
public class CategoriaDAO {
    public Categoria obter(int id) throws SQLException {
        
    }
    
    public List<Categoria> obterTodos() throws SQLException{
        List<Categoria> resultado = new ArrayList();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                Statement statement = connection.createStatement(); 
                ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIA")){
                while(resultSet.next()){
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setDescricao(resultSet.getString("descricao"));
                    System.out.println(categoria.getDescricao());

                    resultado.add(categoria);
                }
                
                return resultado;
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public List<Categoria> obterTodos(int idProduto) throws SQLException{
    
    }
    public Categoria inserir(String descricao) throws SQLException{
        
    }
    public Categoria atualizar(int id, String descricao) throws SQLException{}
    public String remover(int id) throws SQLException{}
}
