package Models;

public class ProductListModel {
    private String id;
    private String listId;
    private String nome;
    private int quantidade;
    private Boolean checked;

    public ProductListModel(String id, String listId, String nome, int quantidade, Boolean checked) {
        this.id = id;
        this.listId = listId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.checked = checked;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getChecked() {
        return checked;
    }

    public String getQuantidade() {
        return String.format("%d", quantidade);
    }

    public String getListId() {
        return listId;
    }

    public String getId() { return id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
