/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios.modelo;

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
public class RelatoriosDAO {
    public List<Produto> Estoque() throws SQLException {
        List<Produto> produtos = new ArrayList();
        
        String sql = "Select * from produto where quantidade = 0 order by descricao;";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
    
    public List<RelatorioFaturamento> Faturamento(String inicio, String fim) throws SQLException{
        List<RelatorioFaturamento> rfs = new ArrayList();
        
        String sql = 
            "WITH tab1 as (select CAST(v.data_hora as DATE) as dia, p.descricao, " +
            "p.preco, pv.quantidade, (pv.quantidade * p.preco) as total_preco from venda v " +
            "inner join produto_venda pv on pv.id_venda = v.id " +
            "inner join produto p on p.id = pv.id_produto " + 
            "where v.data_hora >= ? AND  v.data_hora <= ?) " +
            "select dia, sum(total_preco) as valor from tab1 group by dia order by dia;";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, inicio);
                preparedStatement.setString(2, fim);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    RelatorioFaturamento rf = new RelatorioFaturamento();
                    rf.setData(resultSet.getString("dia"));
                    rf.setValor(resultSet.getDouble("valor"));
                    rfs.add(rf);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return rfs;
    }

    public List<RelatorioCliente> Cliente(String inicio, String fim) throws SQLException{
        List<RelatorioCliente> rcs = new ArrayList();
        
        String sql = 
            "WITH tab1 as (select CAST(v.data_hora as DATE) as dia, p.descricao, p.preco, pv.quantidade, " +
            "(pv.quantidade * p.preco) as total_preco, u.id, u.nome from venda v " +
            "inner join usuario u on u.id = v.id_usuario " +
            "inner join produto_venda pv on pv.id_venda = v.id " +
            "inner join produto p on p.id = pv.id_produto " +
            "where v.data_hora >= ? AND  v.data_hora <= ?)" +
            "select id, nome, sum(quantidade) as total_compra from tab1 group by id order by total_compra desc;";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smd_ecommerce_", "jeff", "jeff123"); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, inicio);
                preparedStatement.setString(2, fim);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    RelatorioCliente rc = new RelatorioCliente();
                    rc.getUsuario().setId(resultSet.getInt("id"));
                    rc.getUsuario().setNome(resultSet.getString("nome"));
                    rc.setQuantidadeCompras(resultSet.getInt("total_compra"));
                    rcs.add(rc);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        
        return rcs;
    }
}
