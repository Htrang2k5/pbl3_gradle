package pbl3_gradle.models;

public class Sprint {
    private String sprintID;
    private boolean status;
    private DateTime dateStart;
    private DateTime dateEnd;
    private List<Task> taskList = new ArrayList<>();

    public Sprint(DateTime start, DateTime end) {
        this.dateStart = start;
        this.dateEnd = end;
        this.status = false;
    }

    public boolean isDone() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSprintID() {
        return sprintID;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}

