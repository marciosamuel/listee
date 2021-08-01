package Models;

public class InspirationCardModel {
    private String id;
    private String title;
    private String subTitle;
    private String author;

    public InspirationCardModel(String id, String title, String subTitle, String author) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
    }

    public String getId() {
        return id;
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


    public void setAuthor(String author) {
        this.author = author;
    }
}
