/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cindydamasceno
 */
public class UsuarioDAO {
    
    public void inserir(String nome, String endereco, String email, String login, String senha) throws SQLException{
        int resultado = 0;
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
                resultado = preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (resultado == 0) {
            throw new SQLException("Registro não foi inserido com sucesso");
        }
    }
    public Usuario obter(int id){}
    public Usuario obter(String login){}
    public Usuario atualizar(int id, String nome, String endereco, String email, String login, String senha){}
    public String remover(int id){}
}
