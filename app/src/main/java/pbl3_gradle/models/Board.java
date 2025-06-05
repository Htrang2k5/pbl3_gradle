package pbl3_gradle.models;

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
    }
}
