package pbl3_gradle.views;

import javafx.scene.layout.Pane;

public class DeleteAccPage {
    public Pane getView() {
        // Tao menu bar
        Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                "DeleteAccPage");

        // Tao pane
        Pane pane = new Pane();
        pane.getChildren().add(menuBar);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }
}
