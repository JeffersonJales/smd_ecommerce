/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendaProdutoItem.modelo;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produto.modelo.Produto;
import venda.modelo.Venda;
/**
 *
 * @author jeffe
 */
public class VendaProdutoItemDAO {
    public List<VendaProdutoItem> obter(int idUsuario) throws SQLException{
        List<VendaProdutoItem> vendaProdutoItem = new ArrayList();
        String sql = "select v.id, v.data_hora, p.descricao, pv.quantidade from venda v " +
                        "inner join produto_venda pv on v.id = pv.id_venda " +
                        "inner join produto p on p.id = pv.id_produto " +
                        "where v.id_usuario = ?;";
        
        try {
            Class.forName(Config.JDBC_DRIVER);
            
            try (
                Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USER, Config.JDBC_PASSWORD); 
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
