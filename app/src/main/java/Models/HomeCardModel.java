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
    private String selectedItems;
    private String remainingItems;

    public HomeCardModel(String user_id, String title, String subTitle, String selectedItems, String remainingItems){
        this.user_id = user_id;
        this.title = title;
        this.subTitle = subTitle;
        this.selectedItems = selectedItems;
        this.remainingItems = remainingItems;
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

    public String getSelectedItems() {
        return selectedItems;
    }

    public String getRemainingItems() {
        return remainingItems;
    }
}
