/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendaProdutoItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produto.modelo.Produto;

/**
 *
 * @author jeffe
 */
public class VendaProdutoItemDAO {
    public List<VendaProdutoItem> obter(int idUsuario) throws SQLException{
        List<VendaProdutoItem> vendaProdutoItem = new ArrayList();
        String sql = "select v.id, v.data_hora, p.descricao, pv.quantidade from venda v\n" +
                        "inner join produto_venda pv on v.id = pv.id_venda\n" +
                        "inner join produto p on p.id = pv.id_produto\n" +
                        "where v.id_usuario = ?;";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    int idVenda = resultSet.getInt("id");
                    VendaProdutoItem vpi = null;

                    if(vendaProdutoItem.isEmpty()){
                        vpi = new VendaProdutoItem();
                        vendaProdutoItem.add(vpi);
                    }
                    else{
                        for(VendaProdutoItem vpis : vendaProdutoItem){
                            if(idVenda == vpis.getVenda().getId()){
                                vpi = vpis; 
                                break;
                            }
                        }
                        
                        if(vpi == null){
                            vpi = new VendaProdutoItem();
                            vendaProdutoItem.add(vpi);
                        }
                    }
                    
                    vpi.getVenda().setId(idVenda);
                    vpi.getVenda().setIdUsuario(idUsuario);
                    vpi.getVenda().setDataHora(resultSet.getString("data_hora"));

                    Produto produto = new Produto();
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    
                    vpi.adicionarNovoProduto(produto);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
            
        return vendaProdutoItem;
    }
}
