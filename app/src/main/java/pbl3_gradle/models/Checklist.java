package pbl3_gradle.models;

import pbl3_gradle.controllers.DataManager;

import java.util.ArrayList;
import java.util.List;

public class Checklist {
    protected int idChecklist;
    protected String name;
    protected List<ChecklistItem> items;

    public Checklist() {
        idChecklist = -1;
        name = "";
        items = new ArrayList<ChecklistItem>(); // Initialize with null or an empty list if preferred
    }

    public int getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(int idChecklist) {
        this.idChecklist = idChecklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChecklistItem> getItems() {
        return items;
    }

    public void setItems(List<ChecklistItem> items) {
        this.items = items;
    }

    public void addItem(String itemContent) {
        ChecklistItem newItem = new ChecklistItem();
        newItem.setContent(itemContent);

        // Update to database too
        newItem = DataManager.Instance.createChecklistItem(newItem, idChecklist);

        items.add(newItem);
    }
}
