package pbl3_gradle.models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class User {
    protected int userID;
    protected String userName;
    protected String userPassword;
    protected String email;
    protected String fullName;
    protected String englishName;
    protected int role;
    protected Date birthday;
    protected String phone;
    protected String address;
    protected String avatar;
    protected List<Integer> projectIDList;

//    public User(int userID, String userName, String userPassword, String email, String fullName, String englishName, int role, String phoneNumber, String address, String avatar) {
//        super();
//        this.userID = userID;
//        this.userName = userName;
//        this.userPassword = userPassword;
//        this.email = email;
//        this.fullName = fullName;
//        this.englishName = englishName;
//        this.role = role;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//        this.avatar = avatar;
//    }

    public User() {
        super();
        this.userID = -1;
        this.userName = "";
        this.userPassword = "";
        this.email = "";
        this.fullName = "";
        this.englishName = "";
        this.role = 0;
        this.birthday = new Date();
        this.phone = "";
        this.address = "";
        this.avatar = "";
        this.projectIDList = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Integer> getProjectIDList() {
        return projectIDList;
    }

    public void setProjectIDList(List<Integer> projectIDList) {
        this.projectIDList = projectIDList;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}