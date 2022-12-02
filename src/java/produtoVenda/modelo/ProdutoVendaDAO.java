/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package produtoVenda.modelo;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cindydamasceno
 */
public class ProdutoVendaDAO {
    public ProdutoVenda obter(int idProduto, int idVenda) throws SQLException{
        ProdutoVenda produtoVenda = null;
        
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from produto_venda where id_produto = ? AND id_venda = ?")) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idVenda);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    produtoVenda = new ProdutoVenda();
                    produtoVenda.setIdProduto(resultSet.getInt("id_produto"));
                    produtoVenda.setIdVenda(resultSet.getInt("id_venda"));
                    produtoVenda.setQuantidade(resultSet.getInt("quantidade"));
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return produtoVenda;
    }
    
    public boolean inserir(int idProduto, int idVenda, int quantidade) throws SQLException{
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto_venda (id_produto, id_venda, quantidade) VALUES (?, ?, ?)")) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idVenda);
                preparedStatement.setInt(3, quantidade);

                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean atualizar(int idProduto, int idVenda, int quantidade) throws SQLException{
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto_venda SET quantidade = ? WHERE id_produto = ? AND id_venda = ?")) {
                preparedStatement.setInt(1, quantidade);
                preparedStatement.setInt(2, idProduto);
                preparedStatement.setInt(3, idVenda);

                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}

