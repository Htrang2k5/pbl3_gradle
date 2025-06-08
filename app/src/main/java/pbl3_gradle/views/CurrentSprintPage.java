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
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.Item;
import pbl3_gradle.models.ProductBacklog;
import pbl3_gradle.models.Sprint;
import pbl3_gradle.models.SprintList;
import pbl3_gradle.util.*;

import java.util.List;

public class CurrentSprintPage {
        public Pane getView() {
                // Tạo menuBar
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Current Sprint", "CurrentSprintPage");

                // Tạo nhãn tiêu đề
                Label mainLabel = new Label("CURRENT SPRINT");
                mainLabel.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(305.8, 44.6);
                mainLabel.setLayoutX(670.4);
                mainLabel.setLayoutY(25.8);

                // Nút "More"
                Image image = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 56.9, 51, 1260.8, 20);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Detail Sprint List");
                menuItem1.setStyle(" -fx-font-size: 14px;" +
                        " -fx-alignment: center; " +
                        "-fx-font-family:'Helvetica';");

                contextMenu.getItems().addAll(menuItem1);
                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0);
                        } else {
                                contextMenu.hide();
                        }
                });
                menuItem1.setOnAction(e -> {
                        AppContext.set("currentPage", "SprintListPage");
                        NavigationManager.navigateToSprintListPage();
                });

                // Lấy sprint hiện tại
                List<Sprint> sprint = DataManager.Instance.getUnfinishedSprints();
                if (sprint.size() == 0) {
                        // Nếu chưa có sprint, hiển thị nút tạo sprint mới
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

                // Tạo sprintBox
                Pane sprintBox = sprintBox(sprint.get(0), 979.2f);
                sprintBox.setLayoutX(341.7);
                sprintBox.setLayoutY(92.6);

                // Tạo hình chữ nhật bao ngoài
                RoundedRect rect1 = new RoundedRect(341.7, 172.1, 985, 488.4, "transparent", "#92badd", 2, 36);

                // Tạo backlog scrollpane
                ProductBacklog productBacklog = DataManager.Instance.getCurrentProductBacklog();
                List<Item> backlogItems = DataManager.Instance.getAllItemByBacklog(productBacklog.getIdProductBacklog(), 0);
                ScrollPane scrollPane = createBacklogScrollPane(backlogItems);

                // Tạo nút End Sprint
                FancyButtonClass endSprintButton = new FancyButtonClass("End Sprint", 642.3, 58.6, 510.2, 677.3);
                endSprintButton.setOnAction(e -> {
                               if(DataManager.Instance.changeSprintStatus(sprint.get(0))) {

                                       List<Item> sprintItem = DataManager.Instance.getAllItemByBacklog(productBacklog.getIdProductBacklog(), 0);
                                       ProductBacklog productBacklogCurrent = DataManager.Instance.getCurrentProductBacklog();
                                       for(Item item: sprintItem){
                                               DataManager.Instance.removeItemFromProductBacklog(productBacklogCurrent,item);
                                       }
                                       NavigationManager.navigateToCurrentSprintPage();
                               }});

                // Gộp tất cả vào mainPane
                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, moreButton, sprintBox, rect1, scrollPane, endSprintButton);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                mainPane.setPrefSize(1366, 768);
                return mainPane;
        }

        // Hàm tạo Sprint Box
        public static Pane sprintBox(Sprint sprint, float width) {
                Pane sprintBox = new Pane();
                sprintBox.setPrefSize(width, 60.4);
                sprintBox.setStyle("-fx-background-color: #c4dff8;" +
                        "-fx-border_radius: 36; " +
                        "-fx-background-radius: 36; ");

                Label nameLabel = new Label(String.format("Sprint %d: %s ", sprint.getIdSprint(), sprint.getTitle()));
                nameLabel.setPrefSize(width - 321.7, 24.7);
                nameLabel.setLayoutX(29.8);
                nameLabel.setLayoutY(17.9);
                nameLabel.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-font-family: 'Helvetica';");

                Label dateLabel = new Label(String.format("%s - %s", sprint.getStartDate(), sprint.getActualEndDate()));
                dateLabel.setPrefSize(231.7, 24.7);
                dateLabel.setLayoutX(29.8 + width - 321.7 + 90);
                dateLabel.setLayoutY(17.9);
                dateLabel.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-font-family: 'Helvetica';");

                sprintBox.getChildren().addAll(nameLabel, dateLabel);
                return sprintBox;
        }

        // 💡 Hàm tạo scrollpane chứa backlog
        private ScrollPane createBacklogScrollPane(List<Item> backlogItems) {
                VBox vBox = new VBox();
                vBox.setSpacing(20);
                vBox.setPadding(new Insets(20, 0, 20, 20));

                int index = 1;
                for (Item item : backlogItems) {
                        Pane backlog = ProductBacklogPage.backlog(index++, item);
                        backlog.setPrefSize(976.8, 69.3);
                        vBox.getChildren().add(backlog);
                }

                ScrollPane scrollPane = new ScrollPane(vBox);
                scrollPane.setPrefSize(979.2, 488.4);
                scrollPane.setLayoutX(341.7);
                scrollPane.setLayoutY(172.1);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle(
                        "-fx-background-color: transparent; " +
                                "-fx-background: transparent;" +
                                "-fx-border-color: transparent");

                return scrollPane;
        }
}
