package pbl3_gradle.models;

import pbl3_gradle.controllers.*;
import pbl3_gradle.models.*;

public class TestModel {
    public String getGreeting() {
        Boolean isTest = true;

//        Account.Instance.registerUser("admin", "admin", 1);

        isTest = Account.Instance.login("testingagain", "12345678");
        if (isTest) {
//            return "Yes ";
            return String.valueOf(CurrentUser.Instance.getUserID());
        } else {
            return "No ";
        }
//        return "Run";
    }
}