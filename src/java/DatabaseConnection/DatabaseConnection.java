/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author cindydamasceno
 */
public class DatabaseConnection {
    public ResultSet QuerySimple(String query) throws SQLException{
        try{
            Class.forName("org.mysql.Driver");
             try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jj3294"); 
                    Statement statement = connection.createStatement(); 
                    ResultSet resultSet = statement.executeQuery(query)){
                    return resultSet;
             }
           
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public ResultSet QueryPrepare(String query, List<Object> preparedItens) throws SQLException{
        try{
            Class.forName("org.mysql.Driver");
             try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jj3294"); 
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                 
                for(int i = 0; i < preparedItens.size(); i++){
                    Object item = preparedItens.get(i);
                    int parameterIndex = i + 1;
                    
                    if(item instanceof Integer){
                        preparedStatement.setInt(parameterIndex, (int) item);
                    }
                    
                    else if (item instanceof String){
                        preparedStatement.setString(parameterIndex, (String) item);
                    }
                }
                
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    return resultSet;
                }
             }
           
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
