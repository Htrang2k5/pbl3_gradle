package pbl3_gradle.models;

public abstract class Backlog {
    protected List<Item> backlogItemList;

    public Backlog() {
        backlogItemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        backlogItemList.add(item);
    }

    public void removeItem(Item item) {
        backlogItemList.remove(item);
    }
}
public class ProductBacklog extends Backlog {
}

public class SprintBacklog extends Backlog {
}
