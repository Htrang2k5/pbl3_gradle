package pbl3_gradle.models;

import java.util.*;
import pbl3_gradle.models.*;
import pbl3_gradle.controllers.*;

public class CurrentProject {
    public static final CurrentProject Instance = new CurrentProject();
    private Project currentProject;

    private CurrentProject() {
        currentProject = new Project();
    }

    public int getIdProject() {
        return currentProject.idProject;
    }
    public void setIdProject(int idProject) {
        currentProject.idProject = idProject;
    }

    public String getProjectName() {
        return currentProject.projectName;
    }
    public void setProjectName(String projectName) {
        currentProject.projectName = projectName;
    }

    public String getDescription() {
        return currentProject.description;
    }
    public void setDescription(String description) {
        currentProject.description = description;
    }

    public Date getDateCreated() {
        return currentProject.dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        currentProject.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return currentProject.dateModified;
    }
    public void setDateModified(Date dateModified) {
        currentProject.dateModified = dateModified;
    }

    public boolean getStatus() {
        return currentProject.status;
    }
    public void setStatus(boolean status) {
        currentProject.status = status;
    }

    public ProductBacklog getProductBacklog() {
        return currentProject.productBacklog;
    }
    public void setProductBacklog(ProductBacklog productBacklog) {
        currentProject.productBacklog = productBacklog;
        DataManager.Instance.updateProject(currentProject);
    }
}