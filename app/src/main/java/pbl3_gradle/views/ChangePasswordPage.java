package pbl3_gradle.views;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.PasswordTextFieldClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.common.FancyButtonClass;

public class ChangePasswordPage {
    public Pane getView() {
        // Tao menu bar
        Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                "ChangePasswordPage");

        // Tao main Label
        Label mainLb = new Label("CHANGE PASSWORD");
        mainLb.setStyle(
                "-fx-text-fill: #2f74eb;"
                        + " -fx-font-size: 26 px;"
                        + " -fx-alignment: center; "
                        + "-fx-font-family:'Arial';"
                        + "-fx-font-weight: bold;");
        mainLb.setPrefSize(362.7, 44.6);
        mainLb.setLayoutX(661.4);
        mainLb.setLayoutY(37.3);

        // Tao khung hinh chu nhat
        RoundedRect rect = new RoundedRect(349.4, 152.3, 966.5, 500.4, "transparent", "#92badd", 2, 36);
        // Tao form edit
        // Tao group 4 label
        Label lb1 = new Label("Current Password: ");
        Label lb2 = new Label("New Password: ");
        Label lb3 = new Label("Enter-Password: ");
        AdminAddAccPage.LabelStyle(lb1, "#2f74eb", "left", 328.4, 35.9);
        AdminAddAccPage.LabelStyle(lb2, "#2f74eb", "left", 328.4, 35.9);
        AdminAddAccPage.LabelStyle(lb3, "#2f74eb", "left", 328.4, 35.9);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(lb1, lb2, lb3);
        vbox.setSpacing(62);
        vbox.setLayoutX(450);
        vbox.setLayoutY(236.1);
        vbox.setPrefSize(328.4, 234.9);
        // Tao group 3 textfield
        PasswordTextFieldClass tf1 = new PasswordTextFieldClass();
        PasswordTextFieldClass tf2 = new PasswordTextFieldClass();
        PasswordTextFieldClass tf3 = new PasswordTextFieldClass();
        AdminAddAccPage.TextFieldStyle(tf1, "#2f74eb", 495.8, 54.5);
        AdminAddAccPage.PwTextFieldStyle(tf2, "#2f74eb", 495.8, 54.5);
        AdminAddAccPage.PwTextFieldStyle(tf3, "#2f74eb", 495.8, 54.5);
        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(tf1, tf2, tf3);
        vbox1.setSpacing(44);
        vbox1.setLayoutX(740.0);
        vbox1.setLayoutY(226.8);
        vbox1.setPrefSize(495.8, 251.5);
        // Tao button save
        FancyButtonClass saveBtn = new FancyButtonClass("SAVE", 213.1, 59.8, 726, 553.1);
        // Tao pane
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, mainLb, rect, vbox, vbox1, saveBtn);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }
}
