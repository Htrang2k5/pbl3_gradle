package pbl3_gradle.models;

import pbl3_gradle.models.*;
import java.util.*;

public class Project {
    protected int idProject;
    protected String projectName;
    protected String description;
    protected Date dateCreated;
    protected Date dateModified;
    protected ProductBacklog productBacklog;
    protected List<Sprint> sprints;
    //SprintBacklog
    protected boolean status;

    //public ProductBacklog()

    public Project() {
        this.idProject = 0;
        this.projectName = "";
        this.description = "";
        this.dateCreated = new Date();
        this.dateModified = new Date();
        this.status = false;
    }

    // Các getter và setter
    public int getIdProject() { return idProject; }
    public void setIdProject(int idProject) { this.idProject = idProject; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getDateModified() { return dateModified; }
    public void setDateModified(Date dateModified) { this.dateModified = dateModified; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }
    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }
}
