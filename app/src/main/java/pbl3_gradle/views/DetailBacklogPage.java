package pbl3_gradle.views;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.*;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;
import javafx.scene.control.Label;

public class DetailBacklogPage {
    public Pane getView() {
        // Tao MenuBar
        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Detail Backlog", "DetailBacklogPage");
        // Tao button Back
        Image image1 = new Image(
                "file:src/main/resources/image/Back_Icon.png");
        ImageButtonClass btnBack = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
        btnBack.setOnAction(e -> {
            AppContext.set("currentPage", "ProductBacklogPage");
            NavigationManager.navigateToProductBacklogPage();
        });
        // Tao backlog lam tieu de
        Pane backlog = ProductBacklogPage.backlog(1, "Design UI", "Not Set");
        backlog.setPrefSize(894.4, 68.8);
        backlog.setLayoutX(386.2);
        backlog.setLayoutY(63.9);
        // Tao hop chua description
        RoundedRect rect = new RoundedRect(339.2, 156.1, 988.5, 570.4, "#c4dff8", "#ffffff", 0, 36);
        // Tao label description
        Label description = new Label(
                "Description: This is a detailed description of the backlog item. It includes all the necessary information that the team needs to understand the requirements and expectations for this item.");
        description.setStyle(
                "-fx-text-fill: #2f74eb; -fx-font-size: 18px; -fx-alignment: TOP_LEFT; -fx-font-family: 'Helvetica';");
        description.setPrefWidth(903);
        description.setMaxHeight(519.9);
        description.setLayoutX(386.2);
        description.setLayoutY(182);
        description.setWrapText(true);
        // Tao pane chinh
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, btnBack, backlog, rect, description);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }
}
