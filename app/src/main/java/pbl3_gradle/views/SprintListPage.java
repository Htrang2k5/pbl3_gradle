package pbl3_gradle.views;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class SprintListPage {
        public Pane getView() {
                // Tao menu bar
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Sprint List", "SprintListPage");
                // Crate the main label
                Label mainLabel = new Label("SPRINT LIST");
                mainLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(212.4, 44.6);
                mainLabel.setLayoutX(717.1);
                mainLabel.setLayoutY(19.3);
                // Create the back button
                Image image1 = new Image(
                                "file:src/main/resources/image/Back_Icon.png");
                ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                backButton.setOnAction(e -> {
                        // Navigate back to the previous page
                        AppContext.set("currentPage", "CurrentSprintPage");
                        NavigationManager.navigateToCurrentSprintPage();
                });
                // Create the done sprint box
                Pane doneSprintBox = doneSprint();
                doneSprintBox.setLayoutX(350);
                doneSprintBox.setLayoutY(76.8);
                // Create a current sprint box
                Pane currentSprintBox = currentSprintBox();
                currentSprintBox.setLayoutX(350);
                currentSprintBox.setLayoutY(536.1);
                // Create the main pane
                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, backButton, doneSprintBox, currentSprintBox);
                mainPane.setPrefSize(1366, 768);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                return mainPane;

        }

        public static Pane doneSprint() {
                // Create a label for done sprint box
                Label doneSprintLabel = new Label("Done Sprint");
                doneSprintLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 22px; -fx-alignment: center; -fx-font-family: 'Helvetica'; ");
                doneSprintLabel.setPrefSize(168.1, 35.1);
                doneSprintLabel.setLayoutX(385.6);
                doneSprintLabel.setLayoutY(20);
                // Create a Vbox for list of done sprints
                VBox doneSprintList = new VBox();
                Pane sprint1 = CurrentSprintPage.sprintBox(1,
                                "xay dung mo hinh dua an cua toi trong mot thoi gian dai do la ca qua trinh tich luy vo cung lon lao, toi da rat co gang de kiem tra tinh loi",
                                "22/1/2023", "22/2/2023", 885.8f);
                Pane sprint2 = CurrentSprintPage.sprintBox(2, "xay dung giao dien", "22/2/2023", "22/3/2023", 885.8f);
                Pane sprint3 = CurrentSprintPage.sprintBox(3, "kiem thu", "22/3/2023", "22/4/2023", 885.8f);
                List<Pane> sprints = List.of(sprint1, sprint2, sprint3);
                for (Pane x : sprints) {
                        doneSprintList.getChildren().add(x);
                }
                doneSprintList.setSpacing(20);
                doneSprintList.setPadding(new Insets(20, 0, 20, 20));
                ScrollPane scrollPane = new ScrollPane(doneSprintList);
                scrollPane.setPrefSize(939.2, 352.3); // Kich thuoc scroll pane
                scrollPane.setLayoutX(5);
                scrollPane.setLayoutY(75.1);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Luon hien thi thanh cuon
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Khong hien thi thanh cuon ngang
                scrollPane.setStyle(
                                "-fx-background-color: transparent; "
                                                + "-fx-background: transparent;"
                                                + "-fx-border-color: transparent");
                // Creat a pane
                Pane pane = new Pane();
                pane.setPrefSize(939.2, 427.4);
                pane.getChildren().addAll(doneSprintLabel, scrollPane);
                pane.setStyle(
                                "-fx-background-color: #ffffff;"
                                                + "-fx-border-color: #92badd;"
                                                + "-fx-border-width: 2px;"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;");
                return pane;
        }

        public static Pane currentSprintBox() {
                // Create a label for current sprint Box
                Label currentSprintLabel = new Label("Current Sprint");
                currentSprintLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 22px; -fx-alignment: center; -fx-font-family: 'Helvetica'; ");
                currentSprintLabel.setPrefSize(180.4, 35.1);
                currentSprintLabel.setLayoutX(379.4);
                currentSprintLabel.setLayoutY(20);
                // Create a new Pane
                Pane currentSprintBox = new Pane();
                currentSprintBox.getChildren().addAll(currentSprintLabel);
                currentSprintBox.setPrefSize(939.2, 199.8);
                currentSprintBox.setStyle(
                                "-fx-background-color: #ffffff;"
                                                + "-fx-border-color: #92badd;"
                                                + "-fx-border-width: 2px;"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;");
                // Check if the current sprint is null
                if (AppContext.get("CurrentSprint") == null) {
                        // Crate a button to create a new sprint
                        FancyButtonClass createNew = new FancyButtonClass(
                                        "No sprint now! Do you want to create a new sprint?",
                                        885.8,
                                        60.4,
                                        26.7,
                                        75.1);
                        createNew.setOnAction(e -> {
                                AppContext.set("lastPage", "SprintListPage");
                                AppContext.set("currentPage", "AddNewSprintPage");
                                NavigationManager.navigateToAddNewSprintPage();
                        });
                        currentSprintBox.getChildren().add(createNew);
                } else {
                        // Create a sprint box
                        Pane sprintBox = CurrentSprintPage.sprintBox(4,
                                        "Tao Planning cho du an phat trien cong nghe phan mem",
                                        "01/01/2023", "01/15/2023", 885.8f);
                        sprintBox.setLayoutX(26.7);
                        sprintBox.setLayoutY(75.1);
                        currentSprintBox.getChildren().add(sprintBox);
                }
                return currentSprintBox;
        }
}
