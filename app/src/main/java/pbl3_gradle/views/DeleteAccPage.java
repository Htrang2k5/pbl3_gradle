package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DeleteAccPage {
    public Pane getView() {
        // Tao menu bar
        Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                "DeleteAccPage");
        // Tao main label
        Label mainLb = new Label("DELETE ACCOUNT");
        mainLb.setStyle(
                "-fx-text-fill: #2f74eb;"
                        + " -fx-font-size: 26px;"
                        + " -fx-alignment: center; "
                        + "-fx-font-family:'Arial';"
                        + "-fx-font-weight: bold;");
        mainLb.setPrefSize(318.3, 44.6);
        mainLb.setLayoutX(668.3);
        mainLb.setLayoutY(37.3);

        // Tao pane
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, mainLb);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }
}
