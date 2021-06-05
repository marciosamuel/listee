package Models;

public class HomeCardModel {
    private String title;
    private String selectedItems;
    private String remainingItems;

    public HomeCardModel(String title, String selectedItems, String remainingItems){
        this.title = title;
        this.selectedItems = selectedItems;
        this.remainingItems = remainingItems;
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
