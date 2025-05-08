package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.util.NavigationManager;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.control.ScrollPane;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.CustomMessageBox;

public class CurrentProjectPage {
        public Pane getView() {
                // Create menu bar
                Pane menuBar = ProfileMemberPage.MenuBarStyle_Layer2(
                                "file:src/main/resources/image/ImageAvatar.png", "Nguyễn Thị Huyền Trang",
                                "Scrum Master",
                                "CurrentProjectPage");
                // Create a main label
                Label mainLb = new Label("CURRENT PROJECTS");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(290.2, 41.1);
                mainLb.setLayoutX(678.6);
                mainLb.setLayoutY(43.3);
                // Create a more button
                Image image1 = new Image(
                                "file:src/main/resources/image/MoreIcon.png");
                ImageButtonClass moreButton = new ImageButtonClass(image1, 44.4, 44.4, 1244.8, 41.2);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Completed Projects");
                menuItem1.setStyle(" -fx-font-size: 20px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1);
                contextMenu.setPrefSize(283.3, 089.8);
                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0); // Hiện phía dưới button
                        } else {
                                contextMenu.hide();
                        }
                });
                menuItem1.setOnAction(e -> {
                        // Navigate to CompletedProjectPage
                        AppContext.set("currentPage", "CompeletedProjectPage");
                        NavigationManager.navigateToCompeletedProjectPage();
                });
                // Tao text field tim kiem
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(379.7);
                findAvatar.setLayoutY(134.3);
                TextField findtext = new TextField();
                EditAcc_ShowAccPage.setStyleFindText(findtext, 981.8, 65.9, 338.1, 124.7);
                // Tao list current project
                String projectTitle = "Chicken learn to Fly fasjdfjsadfasldkfasdifjasp";
                String projectDescription = "This is a project about chicken learn to fly nasdkfjaosidfjasdfnasjdfnasdjfndsofnsaoifdjaoisdjasdjfasidfjaosdifoasdjnfsaofhs";
                Button projectBtn1 = projectButton(projectTitle, projectDescription);
                Button projectBtn2 = projectButton("", "");
                Button projectBtn3 = projectButton("", "");
                Button projectBtn4 = projectButton("", "");
                Button projectBtn5 = projectButton("", "");
                Button projectBtn6 = projectButton("", "");
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
                // Create Pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, moreButton, findtext, findAvatar,
                                scrollPane);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public static Button projectButton(String title, String description) {
                Pane projectPane = new Pane();
                projectPane.setPrefSize(469.9, 166);
                Label titleLbb = new Label(title);
                titleLbb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 20px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Helvetica';"
                                                + "-fx-font-weight: bold;");
                titleLbb.setPrefSize(361.4, 34.7);
                titleLbb.setLayoutX(37.1);
                titleLbb.setLayoutY(10.3);
                Label descriptionLb = new Label(description);
                descriptionLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 15px;"
                                                + " -fx-alignment: TOP_LEFT; "
                                                + "-fx-font-family:'Helvetica';");
                descriptionLb.setPrefSize(448.8, 82.7);
                descriptionLb.setLayoutX(10.5);
                descriptionLb.setLayoutY(59.4);
                descriptionLb.setWrapText(true);
                descriptionLb.setMaxSize(448.8, 82.7);

                Image image = new Image(
                                "file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 36.2, 412.8, 9.4);

                projectPane.getChildren().addAll(titleLbb, descriptionLb, moreButton);
                projectPane.setPrefSize(469.9, 166);

                Button projectBtn = new Button();
                projectBtn.setGraphic(projectPane);
                projectBtn.setStyle(
                                "-fx-background-color: #c4dff8;"
                                                + " -fx-border-color: #92badd;"
                                                + " -fx-border-width: 2px;"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;"
                                                + "-fx-cursor: hand;");
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Done");
                MenuItem menuItem2 = new MenuItem("Delete");
                MenuItem menuItem3 = new MenuItem("Redo");
                menuItem1.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                menuItem2.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                menuItem3.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                if (AppContext.get("currentPage") == "CurrentProjectPage") {
                        contextMenu.getItems().addAll(menuItem1, menuItem2);
                        menuItem1.setOnAction(e -> {
                                CustomMessageBox.show("Success!", "Done project!");
                        });
                } else {
                        contextMenu.getItems().addAll(menuItem3, menuItem2);
                        menuItem3.setOnAction(e -> {
                                CustomMessageBox.show("Success!", "Redo project!");
                        });
                }
                moreButton.setOnMouseClicked(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0); // Hiện phía dưới button
                        } else {
                                contextMenu.hide();
                        }
                });
                applyClickEffect(projectBtn);

                // projectBtn.setOnAction(e -> {
                // // Navigate to ProjectDetailPage
                // AppContext.set("currentPage", "ProductBacklogPage");
                // NavigationManager.navigateToProductBacklogPage();
                // });

                projectBtn.setOnMouseClicked(e -> {
                        AppContext.set("currentPage", "ProductBacklogPage");
                        NavigationManager.navigateToProductBacklogPage();
                });
                return projectBtn;
        }

        public static void applyClickEffect(Button button) {
                EventHandler<MouseEvent> clickPressedHandler = e -> animateScale(button, 0.95);
                EventHandler<MouseEvent> clickReleasedHandler = e -> animateScale(button, 1.00);

                button.addEventHandler(MouseEvent.MOUSE_PRESSED, clickPressedHandler);
                button.addEventHandler(MouseEvent.MOUSE_RELEASED, clickReleasedHandler);
        }

        private static void animateScale(Button button, double scale) {
                ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
                st.setToX(scale);
                st.setToY(scale);
                st.play();
        }

}
