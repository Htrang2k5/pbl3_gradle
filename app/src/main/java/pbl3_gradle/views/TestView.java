package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pbl3_gradle.controllers.TestController;

public class TestView {
    private final TestController controller;

    public TestView() {
        this.controller = new TestController();
    }

    public StackPane getView() {
        Label label = new Label(controller.getMessage());
        return new StackPane(label);
    }
}
