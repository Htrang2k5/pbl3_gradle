package pbl3_gradle.controllers;

import pbl3_gradle.models.TestModel;


public class TestController {
    private final TestModel model;

    public TestController() {
        this.model = new TestModel();
    }

    public String getMessage() {
        return model.getGreeting();
    }
}
