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
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.Sprint;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class SprintListPage {

        public Pane getView() {
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Sprint List", "SprintListPage");

                Label mainLabel = new Label("SPRINT LIST");
                mainLabel.setStyle("-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(212.4, 44.6);
                mainLabel.setLayoutX(717.1);
                mainLabel.setLayoutY(19.3);

                Image image1 = new Image("file:src/main/resources/image/Back_Icon.png");
                ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                backButton.setOnAction(e -> {
                        AppContext.set("currentPage", "CurrentSprintPage");
                        NavigationManager.navigateToCurrentSprintPage();
                });

                Pane doneSprintBox = doneSprint();
                doneSprintBox.setLayoutX(350);
                doneSprintBox.setLayoutY(76.8);

                Pane currentSprintBox = currentSprintBox();
                currentSprintBox.setLayoutX(350);
                currentSprintBox.setLayoutY(536.1);

                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, backButton, doneSprintBox, currentSprintBox);
                mainPane.setPrefSize(1366, 768);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                return mainPane;
        }

        public Pane doneSprint() {
                Label doneSprintLabel = new Label("Done Sprint");
                doneSprintLabel.setStyle("-fx-text-fill: #2f74eb; -fx-font-size: 22px; -fx-alignment: center; -fx-font-family: 'Helvetica'; ");
                doneSprintLabel.setPrefSize(168.1, 35.1);
                doneSprintLabel.setLayoutX(385.6);
                doneSprintLabel.setLayoutY(20);

                VBox doneSprintList = new VBox();
                List<Sprint> finishedSprints = DataManager.Instance.getFinishedSprints();

                for (Sprint sprint : finishedSprints) {
                        Pane sprintPane = CurrentSprintPage.sprintBox(sprint, 885.8f);
                        doneSprintList.getChildren().add(sprintPane);
                }

                doneSprintList.setSpacing(20);
                doneSprintList.setPadding(new Insets(20, 0, 20, 20));
                ScrollPane scrollPane = new ScrollPane(doneSprintList);
                scrollPane.setPrefSize(939.2, 352.3);
                scrollPane.setLayoutX(5);
                scrollPane.setLayoutY(75.1);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent");

                Pane pane = new Pane();
                pane.setPrefSize(939.2, 427.4);
                pane.getChildren().addAll(doneSprintLabel, scrollPane);
                pane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #92badd; -fx-border-width: 2px; -fx-border-radius: 36px; -fx-background-radius: 36px;");
                return pane;
        }

        public Pane currentSprintBox() {
                Label currentSprintLabel = new Label("Current Sprint");
                currentSprintLabel.setStyle("-fx-text-fill: #2f74eb; -fx-font-size: 22px; -fx-alignment: center; -fx-font-family: 'Helvetica'; ");
                currentSprintLabel.setPrefSize(180.4, 35.1);
                currentSprintLabel.setLayoutX(379.4);
                currentSprintLabel.setLayoutY(20);

                Pane currentSprintBox = new Pane();
                currentSprintBox.getChildren().addAll(currentSprintLabel);
                currentSprintBox.setPrefSize(939.2, 199.8);
                currentSprintBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #92badd; -fx-border-width: 2px; -fx-border-radius: 36px; -fx-background-radius: 36px;");

                List<Sprint> unfinishedSprints = DataManager.Instance.getUnfinishedSprints();

                if (unfinishedSprints.isEmpty()) {
                        FancyButtonClass createNew = new FancyButtonClass("No sprint now! Do you want to create a new sprint?", 885.8, 60.4, 26.7, 75.1);
                        createNew.setOnAction(e -> {
                                AppContext.set("lastPage", "SprintListPage");
                                AppContext.set("currentPage", "AddNewSprintPage");
                                NavigationManager.navigateToAddNewSprintPage();
                        });
                        currentSprintBox.getChildren().add(createNew);
                } else {
                        Sprint currentSprint = unfinishedSprints.get(0); // giả sử chỉ có 1 sprint đang hoạt động
                        Pane sprintBox = CurrentSprintPage.sprintBox(currentSprint, 885.8f);
                        sprintBox.setLayoutX(26.7);
                        sprintBox.setLayoutY(75.1);
                        currentSprintBox.getChildren().add(sprintBox);
                }

                return currentSprintBox;
        }
}
