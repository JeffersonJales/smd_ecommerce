/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendaProdutoItem;
import java.util.List;
import produto.modelo.Produto;
import venda.modelo.Venda;

public class VendaProdutoItem {
    private Venda venda;
    private List<Produto> produtos;

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void adicionarNovoProduto(Produto produto){
        produtos.add(produto);
    }
}
