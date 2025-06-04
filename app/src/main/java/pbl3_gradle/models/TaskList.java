package pbl3_gradle.models;

import java.util.List;

public class TaskList {
    protected int idTaskList;
    protected String name;
    protected List<Task> tasks;
    protected int position;

    public TaskList(){
        idTaskList = -1;
        name = "";
        tasks = null;
        position = 0;
    }

    public int getIdTaskList() {
        return idTaskList;
    }

    public void setIdTaskList(int idTaskList) {
        this.idTaskList = idTaskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
