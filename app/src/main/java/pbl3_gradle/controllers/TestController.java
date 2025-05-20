package pbl3_gradle.controllers;

import pbl3_gradle.models.UserClass;

import java.util.Vector;


public class TestController {
    private Vector<UserClass> userList = new Vector<>();

    public TestController() {
        for (int i = 0; i < 20; i++) {
            UserClass user = new UserClass();
            userList.add(user);
        }

    }

    public Vector<UserClass> getUserList() {
        return userList;
    }

}
