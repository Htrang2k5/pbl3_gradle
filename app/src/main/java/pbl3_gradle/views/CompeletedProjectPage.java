package pbl3_gradle.views;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.util.NavigationManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class CompeletedProjectPage {
        public Pane getView() {
                // Create menu bar
                Pane menuBar = ProfileMemberPage.MenuBarStyle_Layer2(
                                "file:src/main/resources/image/ImageAvatar.png", "Nguyễn Thị Huyền Trang",
                                "Scrum Master",
                                "CompeletedProjectPage");
                // Create a main label
                Label mainLb = new Label("COMPLETED PROJECTS");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(412.2, 41.1);
                mainLb.setLayoutX(623.7);
                mainLb.setLayoutY(43.3);
                // Create a back button
                Image image1 = new Image(
                                "file:src/main/resources/image/Back_Icon.png");
                ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                backButton.setOnAction(e -> {
                        // Navigate back to the previous page
                        NavigationManager.navigateToCurrentProjectPage();
                });
                // Tao text field tim kiem
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(379.7);
                findAvatar.setLayoutY(134.3);
                TextField findtext = new TextField();
                EditAcc_ShowAccPage.setStyleFindText(findtext, 981.8, 65.9, 338.1, 124.7);
                // Tao list current project
                String projectTitle = "Chicken learn to Fly";
                String projectDescription = "This is a project about chicken learn to fly";
                Button projectBtn1 = CurrentProjectPage.projectButton(projectTitle, projectDescription);
                Button projectBtn2 = CurrentProjectPage.projectButton("", "");
                Button projectBtn3 = CurrentProjectPage.projectButton("", "");
                Button projectBtn4 = CurrentProjectPage.projectButton("", "");
                Button projectBtn5 = CurrentProjectPage.projectButton("", "");
                Button projectBtn6 = CurrentProjectPage.projectButton("", "");
                GridPane gridPane = new GridPane();
                gridPane.setHgap(24); // Set horizontal gap between buttons
                gridPane.setVgap(24); // Set vertical gap between buttons
                gridPane.setPadding(new Insets(5, 5, 5, 5));
                gridPane.add(projectBtn1, 0 % 2, 0 / 2);
                gridPane.add(projectBtn2, 1 % 2, 1 / 2);
                gridPane.add(projectBtn3, 2 % 2, 2 / 2);
                gridPane.add(projectBtn4, 3 % 2, 3 / 2);
                gridPane.add(projectBtn5, 4 % 2, 4 / 2);
                gridPane.add(projectBtn6, 5 % 2, 5 / 2);

                ScrollPane scrollPane = new ScrollPane(gridPane);
                scrollPane.setPrefSize(1050, 530);
                scrollPane.setLayoutX(310);
                scrollPane.setLayoutY(220);
                scrollPane.setStyle(
                                "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // luon luon hien thi thanh cuon

                // Create a new Pane for the Completed Project Page
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, backButton, scrollPane, findtext,
                                findAvatar);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);

                // Add components to the pane here

                return pane;
        }
}
