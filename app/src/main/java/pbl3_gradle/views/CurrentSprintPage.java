package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.*;
import pbl3_gradle.util.*;

public class CurrentSprintPage {
        public Pane getView() {
                // Tao MenuBar
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Current Sprint", "CurrentSprintPage");

                // Create the main label
                Label mainLabel = new Label("CURRENT SPRINT");
                mainLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(305.8, 44.6);
                mainLabel.setLayoutX(670.4);
                mainLabel.setLayoutY(25.8);
                // Create the more button
                Image image = new Image(
                                "file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 56.9, 51, 1260.8, 20);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("View Sprint List");
                menuItem1.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1);
                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0); // Hiện phía dưới button
                        } else {
                                contextMenu.hide();
                        }
                });
                menuItem1.setOnAction(e -> {
                        // Navigate to Sprint List Page
                        AppContext.set("currentPage", "SprintListPage");
                        NavigationManager.navigateToSprintListPage();
                });
                // Truong hop khong co sprint nao
                if (AppContext.get("CurrentSprint") == null) {
                        // Tao button tao sprint moi
                        FancyButtonClass createNew = new FancyButtonClass(
                                        "No sprint now! Do you want to create a new sprint?",
                                        955.8,
                                        58.6,
                                        361.9,
                                        120.9);
                        createNew.setOnAction(e -> {
                                AppContext.set("lastPage", "CurrentSprintPage");
                                AppContext.set("currentPage", "AddNewSprintPage");
                                NavigationManager.navigateToAddNewSprintPage();
                        });
                        Pane mainPane = new Pane();

                        mainPane.getChildren().addAll(menuBar, mainLabel, moreButton, createNew);
                        mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                        mainPane.setPrefSize(1366, 768);
                        return mainPane;
                }
                // Create the sprint box
                Pane sprintBox = sprintBox(1, "Tao Planning", "01/01/2023", "01/15/2023", 979.2f);
                sprintBox.setLayoutX(341.7);
                sprintBox.setLayoutY(92.6);
                // Tao hinh chu nhat chua noi dung sprint
                RoundedRect rect1 = new RoundedRect(341.7, 172.1, 985, 488.4, "transparent", "#92badd", 2, 36);
                // Tao backlog
                Pane backlog1 = ProductBacklogPage.backlog(1, "Backlog 1", "Doing");
                backlog1.setPrefSize(976.8, 69.3);

                VBox vBox = new VBox(); // Tao VBox de chua cac backlog
                vBox.setSpacing(20); // Khoang cach giua cac backlog
                vBox.getChildren().addAll(backlog1);
                vBox.setPadding(new Insets(20, 0, 20, 20));

                ScrollPane scrollPane = new ScrollPane(vBox);
                scrollPane.setPrefSize(979.2, 488.4); // Kich thuoc scroll pane
                scrollPane.setLayoutX(341.7);
                scrollPane.setLayoutY(172.1);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Luon hien thi thanh cuon
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Khong hien thi thanh cuon ngang
                scrollPane.setStyle(
                                "-fx-background-color: transparent; "
                                                + "-fx-background: transparent;"
                                                + "-fx-border-color: transparent");
                // Tao button end sprint
                FancyButtonClass endSprintButton = new FancyButtonClass(
                                "End Sprint", 642.3, 58.6, 510.2, 677.3);
                endSprintButton.setOnAction(e -> {
                        Boolean isConfirmed = CustomMessageBox.show("Warning!",
                                        "Are you sure you want to end this sprint?");
                        if (isConfirmed) {
                                AppContext.set("CurrentSprint", null); // Xoa gia tri ao
                                CustomMessageBox.show("Success!", "Sprint has been ended successfully!");
                                NavigationManager.navigateToCurrentSprintPage(); // load lai trang hien tai
                        }
                });
                // Create the main pane
                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, moreButton, sprintBox, rect1, scrollPane,
                                endSprintButton);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                mainPane.setPrefSize(1366, 768);

                return mainPane;
        }

        public static Pane sprintBox(int index, String sprintName, String startDate, String endDate, float width) {
                Pane sprintBox = new Pane();
                sprintBox.setPrefSize(width, 60.4);
                sprintBox.setStyle("-fx-background-color: #c4dff8;"
                                + "-fx-border_radius: 36; "
                                + "-fx-background-radius: 36; ");

                Label nameLabel = new Label(String.format("Sprint %d: %s ", index, sprintName));
                nameLabel.setPrefSize(width - 321.7, 24.7);
                nameLabel.setLayoutX(29.8);
                nameLabel.setLayoutY(17.9);
                nameLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-alignment: left; -fx-font-family: 'Helvetica';");

                Label dateLabel = new Label(String.format("%s - %s", startDate, endDate));
                dateLabel.setPrefSize(231.7, 24.7);
                dateLabel.setLayoutX(29.8 + width - 321.7 + 90);
                dateLabel.setLayoutY(17.9);
                dateLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-alignment: left; -fx-font-family: 'Helvetica';");

                sprintBox.getChildren().addAll(nameLabel, dateLabel);
                return sprintBox;
        }
}
