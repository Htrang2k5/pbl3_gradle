package pbl3_gradle.controllers;
import pbl3_gradle.models.*;

public class Account {
    public static final Account Instance = new Account();
    private Member currentUser;

    private Account(){
        System.out.println("Initializing Account"); //for debugging
    }

    public boolean login(String username, String password){
        // Simulate a login process
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }
        else {
            return false;
        }
    }
}
