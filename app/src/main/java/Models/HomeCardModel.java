package Models;

public class HomeCardModel {
    public String getUser_id() {
        return user_id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    private String id;
    private String user_id;
    private String title;
    private String subTitle;
    private Integer quantity;

    public HomeCardModel(String id, String user_id, String title, String subTitle, Integer quantity){
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.subTitle = subTitle;
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return String.format("%d", (quantity == null ? 0 : quantity));
    }
}
