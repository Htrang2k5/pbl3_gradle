package pbl3_gradle.models;

import pbl3_gradle.controllers.DataManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Task {
    protected int idTask;
    protected String title;
    protected String description;
    protected Boolean status;
    protected Date dateCreated;
    protected Date dateModified;
    protected Date dateDue;
    protected int position;
    protected List<User> members;
    protected List<Comment> comments;
    protected List<Checklist> checklists;
    protected Label label;

    public Task(){
        idTask = -1;
        title = "";
        description = "";
        status = false;
        dateCreated = new Date();
        dateModified = new Date();
        dateDue = null;
        position = 0;
        members = new ArrayList<User>();
        comments = new ArrayList<Comment>();
        checklists = new ArrayList<Checklist>();
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void addMember(User user) {
        if (this.members != null && !this.members.contains(user)) {
            this.members.add(user);
        }

        //add to database too
        DataManager.Instance.addMemberToTask(user, idTask);
    }

    public void removeMember(User user) {
        //remove by User id
        if (this.members != null) {
            this.members.removeIf(member -> member.getUserID() == user.getUserID());
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        //All the data is already set in the comment object
        //So now we just need to add it to the database, get it ID
        //then add it to the comments list
        comment = DataManager.Instance.createComment(comment, idTask);

        this.comments.add(comment);
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public void createNewChecklist(String checklistName) {
        Checklist newChecklist = new Checklist();
        newChecklist.setName(checklistName);
        // Update to database too
        newChecklist = DataManager.Instance.createChecklist(newChecklist, idTask);
        this.checklists.add(newChecklist); // Assuming comments can also hold checklists
    }

    public void createNewDueDate(int year, int month, int day, int hour24, int minute) {
        // Create a new Date object with the specified year, month, day, hour, and minute
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour24, minute,0); // Month is 0-based in Calendar
        Date dueDate = cal.getTime();
        this.setDateDue(dueDate);

        // Update to database too
        DataManager.Instance.updateTaskDueDate(this.idTask, dueDate);
    }
}
