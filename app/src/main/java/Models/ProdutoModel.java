package Models;

public class ProdutoModel {
    private String nome;
    private String unidade;
    private int quantidade;
    private int peso;

    public ProdutoModel(String nome, String unidade, int quantidade, int peso) {
        this.nome = nome;
        this.unidade = unidade.toLowerCase();
        this.quantidade = quantidade;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTotalString() {
        float total = quantidade * peso;
        if (unidade.equals("g") && total >= 1000) {
            return String.format("%.2f", total / 1000) + "kg";
        } else if (unidade.equals("kg") && total < 1.0) {
            return String.format("%.2f", total * 1000) + "g";
        } else if (unidade.equals("ml") && total >= 1000) {
            return String.format("%.2f", total / 1000) + "l";
        } else if (unidade.equals("l") && total < 1.0) {
            return String.format("%.2f", total * 1000) + "ml";
        } else {
            return String.format("%.1f", total) + unidade;
        }
    }
}
