package Models;

public class ListModel {
    private String id;
    private String user_id;
    private String title;
    private String subTitle;
    private int quantity;

    public ListModel(){

    }

    public ListModel(String user_id, String title, String subTitle, int quantity){
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

    public String getUser_id() {
        return user_id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getQuantity() { return  quantity; }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
