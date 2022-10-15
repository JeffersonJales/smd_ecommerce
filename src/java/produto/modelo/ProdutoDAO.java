/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package produto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produtoCategoria.modelo.ProdutoCategoria;

/**
 *
 * @author cindydamasceno
 */
public class ProdutoDAO {
    public Produto obter(int id) throws SQLException{
        Produto produto = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from produto where id = ?")) {
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return produto;
    }
    
    public List<Produto> obterPorCategoria(int idCategoria) throws SQLException{
        List<Produto> produtos = new ArrayList();
        
        String sql = 
            "Select * from produto "
            + "INNER JOIN produto_categoria ON "
                + "produto.id = produto_categoria.id_produto "
                + "WHERE produto_categoria.id_categoria = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idCategoria);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));
                    
                    produtos.add(produto);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return produtos;
    }
    
    public boolean inserir(String descricao, double preco, int quantidade, String foto) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (descricao, preco, quantidade, foto) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setDouble(2, preco);
                preparedStatement.setInt(3, quantidade);
                preparedStatement.setString(4, foto);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean atualizar(int id, String descricao, double preco, int quantidade, String foto) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, quantidade = ?, foto = ? WHERE id = ?")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setDouble(2, preco);
                preparedStatement.setInt(3, quantidade);
                preparedStatement.setString(4, foto);
                preparedStatement.setInt(5, id);

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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto where id = ?")) {
                preparedStatement.setInt(1, id);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
