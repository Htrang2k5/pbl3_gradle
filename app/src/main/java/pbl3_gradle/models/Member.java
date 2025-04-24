package pbl3_gradle.models;

import java.util.List;
import java.util.ArrayList;

public class Member extends User{
    protected String email;
    protected String fullName;
    protected String englishName;
    protected String role;
    protected String phoneNumber;
    protected String address;
    public List<String> projectIDList;

    public Member(int userID, String userName, String userPassword, String email, String fullName, String englishName, String role, String phoneNumber, String address) {
        super();
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.fullName = fullName;
        this.englishName = englishName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
