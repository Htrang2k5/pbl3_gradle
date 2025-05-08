package pbl3_gradle.models;

public class UserClass {
    String usename;
    String avatar;
    String role;
    String password;

    public UserClass(String usename, String avatar) {
        this.usename = usename;
        this.avatar = avatar;
        this.password = "123456";
    }

    public UserClass() {
        this.usename = "Htrang";
        this.avatar = "file:src/main/resources/image/ImageAvatar.png";
        this.role = "Scrum Master";
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
