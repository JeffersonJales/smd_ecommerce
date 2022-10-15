/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categoria.modelo;
 
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cindydamasceno
 */
public class CategoriaDAO {
    
    public Categoria obter(int id) throws SQLException {
        Categoria categoria = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                
                PreparedStatement preparedStatement = connection.prepareStatement("Select * categoria where id = ?")) {
                preparedStatement.setInt(1, id);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                    
                while(resultSet.next()){
                    categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setDescricao(resultSet.getString("descricao"));
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return categoria;
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
                    resultado.add(categoria);
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return resultado;
    }
    
    public List<Categoria> obterTodos(int idProduto) throws SQLException{
        List<Categoria> resultado = new ArrayList();
        String sql = "Select * from categoria "
                + "Inner join produto_categoria on categoria.id = produto_categoria.id_categoria"
                + "WHERE produto_categoria.id_produto = ?";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idProduto);
                ResultSet resultSet = preparedStatement.executeQuery();
               
                while(resultSet.next()){
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setDescricao(resultSet.getString("descricao"));
                    resultado.add(categoria);
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return resultado;
    }
    
    public boolean inserir(String descricao) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (descricao) VALUES (?)")) {
                preparedStatement.setString(1, descricao);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean atualizar(int id, String descricao) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET descricao = ? WHERE id = ?")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setInt(2, id);

                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean remover(int id) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria where id = ?")) {
                preparedStatement.setInt(1, id);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
