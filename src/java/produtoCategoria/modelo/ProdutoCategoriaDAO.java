/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package produtoCategoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produtoVenda.modelo.ProdutoVenda;

/**
 *
 * @author cindydamasceno
 */
public class ProdutoCategoriaDAO {
    
    public List<ProdutoCategoria> obterPorProduto(int idProduto) throws SQLException{
        List<ProdutoCategoria> produtoCategorias = new ArrayList();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from produto_categoria where id_produto = ?")) {
                preparedStatement.setInt(1, idProduto);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    ProdutoCategoria produtoCategoria = new ProdutoCategoria();
                    produtoCategoria.setIdCategoria(resultSet.getInt("id_categoria"));
                    produtoCategoria.setIdProduto(resultSet.getInt("id_produto"));
                    
                    produtoCategorias.add(produtoCategoria);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return produtoCategorias;
    }
    
    public List<ProdutoCategoria> obterPorCategoria(int idCategoria) throws SQLException{
        List<ProdutoCategoria> produtoCategorias = new ArrayList();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from produto_categoria where id_categoria = ?")) {
                preparedStatement.setInt(1, idCategoria);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    ProdutoCategoria produtoCategoria = new ProdutoCategoria();
                    produtoCategoria.setIdCategoria(resultSet.getInt("id_categoria"));
                    produtoCategoria.setIdProduto(resultSet.getInt("id_produto"));
                    
                    produtoCategorias.add(produtoCategoria);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return produtoCategorias;
    }
    
    public boolean inserir(int idProduto, int idCategoria) throws SQLException {
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto_categoria (id_produto, id_categoria) VALUES (?, ?)")) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idCategoria);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean atualizar(int idProduto, int idCategoriaAntigo, int idCategoriaNovo) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto_categoria SET id_categoria = ? WHERE id_produto = ? AND id_categoria = ?")) {
                preparedStatement.setInt(1, idCategoriaNovo);
                preparedStatement.setInt(2, idProduto);
                preparedStatement.setInt(3, idCategoriaAntigo);

                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean remover(int idProduto, int idCategoria) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto_categoria where id_produto = ? AND id_categoria = ?")) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idCategoria);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
