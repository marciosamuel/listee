package Models;

public class ProdutoModel {
    private String nome;
    private String unidade;
    private String quantidade;

    public ProdutoModel(String nome, String unidade, String quantidade) {
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getQuantidade() {
        return quantidade;
    }
}
