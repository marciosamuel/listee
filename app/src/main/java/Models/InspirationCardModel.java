package Models;

public class InspirationCardModel {
    private String title;
    private String subTitle;
    private String author;

    public InspirationCardModel(String title, String subTitle, String author) {
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getAuthor() {
        return author;
    }
}
