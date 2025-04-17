package pbl3_gradle.views;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class LoginPage {

    public HBox getView() {
        TextField username = new TextField();
        TextField password = new TextField();
        Label lb1 = new Label("Login");
        Label lb2 = new Label("Username");
        Label lb3 = new Label("Password");
        Button loginButton = new Button("Login");
        HBox hBox = new HBox();

        hBox.getChildren().addAll(lb1, lb2, username, lb3, password, loginButton);
        return hBox;
    }

}