package pbl3_gradle.models;

public class Notifications {
    private int notificationID;
    private User user;
    private String message;
    private boolean isRead;
    private DateTime createdAt;

    public void markAsRead() {
        isRead = true;
    }

    public void sendNotification(User user, String message) {
        this.user = user;
        this.message = message;
        this.createdAt = new DateTime(); // Assume this sets current time
        this.isRead = false;
    }
}
