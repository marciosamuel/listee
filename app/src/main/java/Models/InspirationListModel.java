package Models;

public class InspirationListModel {
    private String inspirationCardId;
    private String nameProduct;
    private int quantity;

    public InspirationListModel(String inspirationCardId, String nameProduct, int quantity) {
        this.inspirationCardId = inspirationCardId;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }

    public String getInspirationCardId() {
        return inspirationCardId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }
}
