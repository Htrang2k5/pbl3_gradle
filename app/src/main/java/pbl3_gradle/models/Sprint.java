package pbl3_gradle.models;
import java.util.*;

public class Sprint {
    private int idSprint;
    private int idProject;
    private String title;
    private Date dateStart;
    private Date estimatedEndDate;
    private Date actualEndDate;
    private Boolean status;

    public List<Item> items;

    public Sprint() {
        this.idSprint = 0;
        this.idProject = 0;
        this.title = "";
        this.dateStart = new Date();
        this.estimatedEndDate = new Date();
        this.actualEndDate = new Date();
        this.status = false;
        this.items = null;
    }

    public int getIdSprint() {return idSprint;}
    public void setIdSprint(int idSprint) {this.idSprint = idSprint;}

    public int getIdProject() {return idProject;}
    public void setIdProject(int idProject) {this.idProject = idProject;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Date getDateStart() {return dateStart;}
    public void setDateStart(Date dateStart) {this.dateStart = dateStart;}

    public Date getEstimatedEndDate() {return estimatedEndDate;}
    public void setEstimatedEndDate(Date estimatedEndDate) {this.estimatedEndDate = estimatedEndDate;}

    public Date getActualEndDate() {return actualEndDate;}
    public void setActualEndDate(Date actualEndDate) {this.actualEndDate = actualEndDate;}

    public Boolean getStatus() {return status;}
    public void setStatus(Boolean status) {this.status = status;}

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getEndDate() {
        return this.actualEndDate = new Date();
    }
}
