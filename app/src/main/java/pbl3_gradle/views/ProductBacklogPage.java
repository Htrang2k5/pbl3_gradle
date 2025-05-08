package pbl3_gradle.views;

import javafx.scene.layout.Pane;
import pbl3_gradle.common.MenuBarClass;
import pbl3_gradle.common.RoundedRect;

public class ProductBacklogPage {
    public Pane getView() {

        // Tao MenuBar
        Pane menuBar = MenuBarStyle_Layer3("Product Backlog", "ProductBacklogPage");
        // Tao pane
        Pane pane = new Pane(menuBar);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }

    public static Pane MenuBarStyle_Layer3(String text, String page) {
        // Tạo hình chữ nhật cho menu
        RoundedRect rect = new RoundedRect();
        // Tao MenuBar
        MenuBarClass menuBar = new MenuBarClass(3, page);
        menuBar.setLayoutX(11.5);
        menuBar.setLayoutY(259.1);
        Pane pane = new Pane();
        pane.getChildren().addAll(rect, menuBar);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(296.1, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }

}
