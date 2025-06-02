package pbl3_gradle.models;

import java.util.List;

public class CurrentUser{
    public static final CurrentUser Instance = new CurrentUser();
    private User currentUser;
    private CurrentUser() {
        currentUser = new User();
    }

    //Kiem tra role
    public Boolean isAdmin(){
        if (currentUser.role == 1) return true;
        else return false;
    }

    public int getUserID() {
        return currentUser.userID;
    }

    public void setUserID(int userID) {
        currentUser.userID = userID;
    }

    public String getUserName() {
        return currentUser.userName;
    }

    public void setUserName(String userName) {
        currentUser.userName = userName;
    }

    public String getUserPassword() {
        return currentUser.userPassword;
    }

    public void setUserPassword(String userPassword) {
        currentUser.userPassword = userPassword;
    }

    public String getEmail() {
        return currentUser.email;
    }

    public void setEmail(String email) {
        currentUser.email = email;
    }

    public String getFullName() {
        return currentUser.fullName;
    }

    public void setFullName(String fullName) {
        currentUser.fullName = fullName;
    }

    public String getEnglishName() {
        return currentUser.englishName;
    }

    public void setEnglishName(String englishName) {
        currentUser.englishName = englishName;
    }

    public int getRole() {
        return currentUser.role;
    }

    public void setRole(int role) {
        currentUser.role = role;
    }

    public String getPhone() {
        return currentUser.phone;
    }

    public void setPhone(String phone) {
        currentUser.phone = phone;
    }

    public String getAddress() {
        return currentUser.address;
    }

    public void setAddress(String address) {
        currentUser.address = address;
    }

    public String getAvatar() {
        return currentUser.avatar;
    }

    public void setAvatar(String avatar) {
        currentUser.avatar = avatar;
    }

    public List<String> getProjectIDList() {
        return currentUser.projectIDList;
    }

    public void setProjectIDList(List<String> projectIDList) {
        currentUser.projectIDList = projectIDList;
    }

    public User getUserObject(){
        return currentUser;
    }
}
