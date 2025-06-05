package pbl3_gradle.controllers;

import com.google.protobuf.Message;
import pbl3_gradle.models.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectController {
    public static final ProjectController Instance = new ProjectController();

    private ProjectController() {
        loadProjectsFromDatabase();
    }

    private List<Project> projectsUndone = new ArrayList<>();
    private List<Project> projectsComplete = new ArrayList<>();
//    private List<Item> items = new ArrayList<>();

    //Load toàn bộ project từ db, chia thành 2 list: Undone và Completed
    private void loadProjectsFromDatabase() {
        List<Project> allProjects = DataManager.Instance.getAllProject();
        projectsUndone.clear();
        projectsComplete.clear();
        for (Project project : allProjects) {
            if (project.isStatus()) {
                projectsComplete.add(project);
            } else {
                projectsUndone.add(project);
            }
        }
    }

    //Trả về list project undone
    public List<Project> getUndoneProject() {
        List<Project> currentProjects = new ArrayList<>();
        for (Project p : projectsUndone) {
            if (!p.isStatus()) {
                currentProjects.add(p);
            }
        }
        return currentProjects;
    }

    //Trả về list project completed
    public List<Project> getCompletedProject() {
        List<Project> completedProjects = new ArrayList<>();
        for (Project p : projectsComplete) {
            if (p.isStatus()) {
                completedProjects.add(p);
            }
        }
        return completedProjects;
    }

    //Trả về true nếu thêm thành công, false ngược lại
    public boolean addProject(Project project) {
        if (DataManager.Instance.verifyProjectName(project.getProjectName())) {
            return false; // Project name already exists
        }
        project.setDateCreated(new Date());
        DataManager.Instance.addNewProject(project);
        projectsUndone.add(project);
        return true; // Project added successfully
    }

    //Trả về list project sau khi đã xoá
    public List<Project> removeProject(int idProject) {
        DataManager.Instance.deleteProject(idProject);
        projectsUndone.removeIf(p -> p.getIdProject() == idProject);
        projectsComplete.removeIf(p -> p.getIdProject() == idProject);
        return projectsUndone; // Return danh sách undone sau khi xóa
    }

    //Thay đổi trạng thái project từ undone -> completed, true nếu thành công, false ngược lại
    public boolean markDoneProject(int idProject) {
        Project projectToMarkDone = DataManager.Instance.getProjectByID(idProject);
        if (projectToMarkDone != null && !projectToMarkDone.isStatus()) {
            projectToMarkDone.setStatus(true);
            projectToMarkDone.setDateModified(new Date());
            DataManager.Instance.updateProjectStatus(projectToMarkDone);
            projectsUndone.remove(projectToMarkDone);
            projectsComplete.add(projectToMarkDone);
            return true; // Project marked as done
        }
        return false; // Project not found or already done
    }

    //Cập nhật thông tin project, true nếu thành công, false ngược lại
    public boolean updateProjectInfo(Project updatedProject) {
        Project existingProject = DataManager.Instance.getProjectByID(updatedProject.getIdProject());
        if (existingProject == null) {
            return false; // Project not found
        }
        if (!existingProject.getProjectName().equals(updatedProject.getProjectName()) && DataManager.Instance.verifyProjectName(updatedProject.getProjectName())) {
            return false; // Project name already exists
        }
        updatedProject.setDateModified(new Date());
        DataManager.Instance.updateProject(updatedProject);
        int undoneIndex = -1;
        for (int i = 0; i < projectsUndone.size(); i++) {
            if (projectsUndone.get(i).getIdProject() == updatedProject.getIdProject()) {
                undoneIndex = i;
                break;
            }
        }
        if (undoneIndex != -1) {
            projectsUndone.set(undoneIndex, updatedProject);
            return true; // Project information updated successfully
        }
        int completeIndex = -1;
        for (int i = 0; i < projectsComplete.size(); i++) {
            if (projectsComplete.get(i).getIdProject() == updatedProject.getIdProject()) {
                completeIndex = i;
                break;
            }
        }
        if (completeIndex != -1) {
            projectsComplete.set(completeIndex, updatedProject);
            return true; // Project information updated successfully
        }
        return true; // Updated in database, local list might not have it
    }

    //Trả về project tìm kiếm theo tên
    public Project searchProject(String projectName) {
        try {
            for (Project p : projectsUndone) {
                if (p.getProjectName().equals(projectName)) {
                    return p;
                }
            }
            for (Project p : projectsComplete) {
                if (p.getProjectName().equals(projectName)) {
                    return p;
                }
            }
        } catch (Exception e) {
            System.out.println("Project not found!");
        }
        return null;
    }

    //Thay đổi trạng thái từ Completed -> Undone
    public Boolean markUndoneProject(int idProject) {
        Project projectToMarkUndone = DataManager.Instance.getProjectByID(idProject);
        if (projectToMarkUndone != null && projectToMarkUndone.isStatus()) {
            projectToMarkUndone.setStatus(false);
            projectToMarkUndone.setDateModified(new Date());
            DataManager.Instance.updateProjectStatus(projectToMarkUndone);
            projectsComplete.remove(projectToMarkUndone);
            projectsUndone.add(projectToMarkUndone);
            return true;
        }
        return false;
    }
}
