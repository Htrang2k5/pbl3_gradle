package pbl3_gradle.models;

import pbl3_gradle.controllers.DataManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Board {
    protected int idBoard;
    protected Date dateCreated;
    protected Date dateModified;
    protected List<TaskList> taskLists;

    public Board(){
        idBoard = -1;
        dateCreated = new Date();
        dateModified = new Date();
        taskLists = new ArrayList<TaskList>();
    }

    public int getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(int idBoard) {
        this.idBoard = idBoard;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public List<TaskList> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    public   void loadDataFromDatabase(){
        if (idBoard == -1){
            System.out.println("Board id is not set. Cannot load data from database.");
            return;
        }

        this.taskLists = DataManager.Instance.getTaskListByBoardId(idBoard);
        for (TaskList taskList : taskLists) {
            taskList.setTasks(DataManager.Instance.getTaskByTaskListId(taskList.getIdTaskList()));
        }
    }

    //tạo task list mới trong Board
    public void createNewTaskList(String taskListName){
        TaskList newList = new TaskList();
        newList.setName(taskListName);
        newList.setPosition(taskLists.size());

        //update to database too
        newList = DataManager.Instance.createTaskList(newList, idBoard);
        taskLists.add(newList);
    }

    //xóa task list trong Board
    public void deleteTaskList(int idTaskList) {
        TaskList taskListToDelete = null;
        for (TaskList taskList : taskLists) {
            if (taskList.getIdTaskList() == idTaskList) {
                taskListToDelete = taskList;
                break;
            }
        }
        if (taskListToDelete != null) {
            DataManager.Instance.deleteTaskList(taskListToDelete);
            taskLists.remove(taskListToDelete);
        } else {
            System.out.println("Task List with ID " + idTaskList + " not found.");
        }
    }

    private void updatePositions(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setPosition(i);
        }
    }

    public void moveTask(int idTask, int idTaskList, int position){
        Task taskToMove = null;
        TaskList destinationTaskList = null;
        TaskList sourceTaskList = null;

        for (TaskList taskList : taskLists) {
            if (taskList.getIdTaskList() == idTaskList) {
                //this is the destination task list
                destinationTaskList = taskList;
            }

            if (taskToMove != null && destinationTaskList != null) {
                break; // Found the task and destination, no need to continue searching
            }

            for (Task task : taskList.getTasks()) {
                if (task.getIdTask() == idTask) {
                    taskToMove = task;
                    sourceTaskList = taskList; // This is the source task list
                    break;
                }
            }
        }

        if (taskToMove == null || destinationTaskList == null) {
            System.out.println("Task or destination list not found.");
        }

        sourceTaskList.getTasks().remove(taskToMove);
        updatePositions(sourceTaskList.getTasks());
        List<Task> destTasks = destinationTaskList.getTasks();
        if (position < 0) position = 0;
        if (position > destTasks.size()) position = destTasks.size();
        destTasks.add(position, taskToMove);
        updatePositions(destTasks);
        updatePositionToDB();
    }

    public void moveTaskList(int idTaskList, int position){
        TaskList taskListToMove = null;

        for (TaskList taskList : taskLists) {
            if (taskList.getIdTaskList() == idTaskList) {
                taskListToMove = taskList;
                break;
            }
        }

        if (taskListToMove == null) {
            System.out.println("Task List not found.");
            return;
        }

        taskLists.remove(taskListToMove);
        if (position < 0) position = 0;
        if (position > taskLists.size()) position = taskLists.size();
        taskLists.add(position, taskListToMove);
        updatePositionToDB();
    }

    private void updatePositionToDB(){
        for (TaskList taskList : taskLists) {
            DataManager.Instance.updateTaskListPosition(taskList);
            for (Task task : taskList.getTasks()) {
                DataManager.Instance.updateTaskPosition(task);
            }
        }
    }
}
