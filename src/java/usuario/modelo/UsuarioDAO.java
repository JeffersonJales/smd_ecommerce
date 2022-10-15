/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.modelo;

import categoria.modelo.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cindydamasceno
 */
public class UsuarioDAO {
    
    public Usuario obter(int id) throws SQLException{
        Usuario usuario = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                
                PreparedStatement preparedStatement = connection.prepareStatement("Select * usuario where id = ?")) {
                preparedStatement.setInt(1, id);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                    
                while(resultSet.next()){
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setAdministrador(resultSet.getBoolean("administrador"));
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setEndereco(resultSet.getString("endereco"));
                    usuario.setLogin(resultSet.getString("login"));
                    usuario.setSenha(resultSet.getString("senha"));
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return usuario;
    }
    
    public Usuario obter(String login) throws SQLException{
        Usuario usuario = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                
                PreparedStatement preparedStatement = connection.prepareStatement("Select * usuario where login = ?")) {
                preparedStatement.setString(1, login);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                    
                while(resultSet.next()){
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setAdministrador(resultSet.getBoolean("administrador"));
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setEndereco(resultSet.getString("endereco"));
                    usuario.setLogin(resultSet.getString("login"));
                    usuario.setSenha(resultSet.getString("senha"));
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return usuario;
    }
    
    public boolean inserir(String nome, String endereco, String email, String login, String senha) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (nome, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, false)")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, senha);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean atualizar(int id, String nome, String endereco, String email, String login, String senha) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuario SET nome = ?, endereco = ?, email = ?, login = ?, senha = ? WHERE id = ?")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, senha);
                preparedStatement.setInt(6, id);
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuario where id = ?")) {
                preparedStatement.setInt(1, id);
                return preparedStatement.execute();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
