/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venda.modelo;
import venda.modelo.Venda;
import categoria.modelo.Categoria;
import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cindydamasceno
 */
public class VendaDAO {
    
    public Venda obter(int id) throws SQLException{
        Venda venda = null;
        
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from venda where id = ?")) {
                preparedStatement.setInt(1, id);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    venda = new Venda();
                    venda.setId(resultSet.getInt("id"));
                    venda.setIdUsuario(resultSet.getInt("id_usuario"));
                    venda.setDataHora(resultSet.getString("data_hora"));
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return venda;
    }
    
    public List<Venda> obterTodas(int idUsuario) throws SQLException {
        List<Venda> vendas = new ArrayList();
        
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from venda where id_usuario = ?")) {
                preparedStatement.setInt(1, idUsuario);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    Venda venda = new Venda();
                    venda.setId(resultSet.getInt("id"));
                    venda.setIdUsuario(resultSet.getInt("id_usuario"));
                    venda.setDataHora(resultSet.getString("data_hora"));
                    
                    vendas.add(venda);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return vendas;
    }
    
    public Venda inserir(int idUsuario) throws SQLException{
        try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda (id_usuario) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, idUsuario);
                preparedStatement.execute();
                
                ResultSet result = preparedStatement.getGeneratedKeys();
                result.next();
                
                Venda venda = new Venda();
                venda.setId(result.getInt(1));
                venda.setIdUsuario(idUsuario);
                
                return venda;
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean remover(int idVenda) throws SQLException{
    try {
            Class.forName(Config.JDBC_DRIVER);
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM venda where id = ?")){
                preparedStatement.setInt(1, idVenda);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
