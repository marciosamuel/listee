package Models;

public class ProdutoModel {
    private String userId;
    private String nome;
    private int quantidade;


    public ProdutoModel(String userId, String nome, int quantidade) {
        this.userId = userId;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return String.format("%d", quantidade);
    }

    public String getUserId() {
        return userId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
