package pbl3_gradle.models;

import java.util.Date;

public class Comment {
    protected int idComment;
    protected int idUser;
    protected String content;
    protected Date dateCreated;

    public Comment(){
        idComment = -1;
        idUser = -1;
        content = "";
        dateCreated = new Date();
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
