package pbl3_gradle.models;

public class Administrator extends User {
    public static final Administrator admin = new Administrator();
    private Administrator() {
        // Private constructor to prevent instantiation
    }
}
