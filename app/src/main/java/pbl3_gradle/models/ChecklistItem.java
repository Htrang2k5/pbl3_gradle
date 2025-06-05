package pbl3_gradle.models;

public class ChecklistItem {
    protected int idChecklistItem;
    protected String content;
    protected boolean checked;

    public ChecklistItem() {
        idChecklistItem = -1; // Default value indicating no ID assigned
        content = ""; // Default empty content
        checked = false; // Default unchecked state
    }

    public int getIdChecklistItem() {
        return idChecklistItem;
    }

    public void setIdChecklistItem(int idChecklistItem) {
        this.idChecklistItem = idChecklistItem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
