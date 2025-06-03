package pbl3_gradle.models;

public class ChecklistItem {
    protected int idChecklistItem;
    protected String content;
    protected boolean checked;

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
