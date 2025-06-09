package pbl3_gradle.models;
import pbl3_gradle.models.*;
import pbl3_gradle.controllers.*;

import java.util.Date;

public class Item {
    protected int idItem;
    protected int idBacklog;
    protected int backlogType; // true for ProductBacklog, false for SprintBacklog
    protected String title;
    protected String description;
    protected Date dateCreated;
    protected Date dateModified;
    protected Boolean status;//

    public Item() {
        this.idItem = 0;
        this.idBacklog = 0;
        this.title = "";
        this.description = "";
        this.dateCreated = new Date();
        this.dateModified = new Date();
        this.status = false;
    }

    public int getIdItem() {return idItem;}
    public void setIdItem(int idItem) { this.idItem = idItem;}

    public int getIdBacklog() { return idBacklog; }
    public void setIdBacklog(int idBacklog) { this.idBacklog = idBacklog; }

    public int getBacklogType() { return backlogType; }
    public void setBacklogType(int backlogType) { this.backlogType = backlogType; }

    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getDateModified() { return dateModified; }
    public void setDateModified(Date dateModified) { this.dateModified = dateModified; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status;}
}
