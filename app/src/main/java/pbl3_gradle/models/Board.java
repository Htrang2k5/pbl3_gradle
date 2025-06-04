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

    public void loadDataFromDatabase(){
        if (idBoard == -1){
            System.out.println("Board id is not set. Cannot load data from database.");
            return;
        }

        this.taskLists = DataManager.Instance.getTaskListByBoardId(idBoard);
    }

    //tạo task list mới trong Board
    public void createNewTaskList(String taskListName){
        TaskList newList = new TaskList();
        newList.setName(taskListName);
        newList.position = taskLists.size();

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
}
