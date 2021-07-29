package Models;

public class ProdutoModel {
    private String nome;
    private int quantidade;


    public ProdutoModel(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return String.format("%d", quantidade);
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
