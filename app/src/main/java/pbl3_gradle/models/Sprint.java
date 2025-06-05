package pbl3_gradle.models;
import java.util.Date;
import java.util.List;

public class Sprint {
    private int idSprint;
    private int idProject;
    private String title;
    private Date startDate;
    private Date estimatedEndDate;
    private Date actualEndDate;
    private Boolean status;

    public List<Item> items;

    public Sprint() {
        this.idSprint = 0;
        this.idProject = 0;
        this.title = "";
        this.startDate = new Date();
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

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

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
}
