package pbl3_gradle.models;

public class Item {
    private String itemID;
    private String title;
    private String description;
    private DateTime dateCreated;
    private DateTime dateModified;

    public void updateItemInfo(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateModified = new DateTime(); // Assume now
    }
}
