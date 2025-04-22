package pbl3_gradle.models;

public class UserClass {
    String usename;
    String avatar;

    public UserClass(String usename, String avatar) {
        this.usename = usename;
        this.avatar = avatar;
    }

    public UserClass() {
        this.usename = "Htrang";
        this.avatar = "file:src/main/resources/image/ImageAvatar.png";
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
