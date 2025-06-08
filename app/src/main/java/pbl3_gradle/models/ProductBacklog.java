package pbl3_gradle.models;
import pbl3_gradle.models.*;
import pbl3_gradle.controllers.*;

import java.util.List;

public class ProductBacklog {
    protected int idProductBacklog;
    protected int idProject;

    public List<Item> items;

    public int getIdProductBacklog() {
        return idProductBacklog;
    }
    public void setIdProductBacklog(int idProductBacklog) {
        this.idProductBacklog = idProductBacklog;
    }
    public int getIdProject() {
        return idProject;
    }
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }
    public ProductBacklog() {
        this.idProductBacklog = 0;
        this.idProject = 0;
        this.items = DataManager.Instance.getAllItemByBacklog(this.idProductBacklog, true);
    }
    public ProductBacklog(int idProductBacklog, int idProject, List<Item> items) {
        this.idProductBacklog = idProductBacklog;
        this.idProject = idProject;
        this.items = items;
    }
    public ProductBacklog addItem(Item item) {
        this.items.add(item);
        return this;
    }
    public ProductBacklog removeItem(Item item) {
        this.items.remove(item);
        return this;
    }
    public ProductBacklog updateItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdItem() == item.getIdItem()) {
                items.set(i, item);
                break;
            }
        }
        return this;
    }
}
