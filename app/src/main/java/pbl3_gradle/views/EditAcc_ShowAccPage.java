package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.MenuBarClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.TestController;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class EditAcc_ShowAccPage {
        public Pane getView() {
                // Lay du lieu tu database
                TestController testData = new TestController();
                // Tap menu Bar
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                                "EditAcc_ShowAccPage");
                // Tao main label
                Label mainLb = new Label("EDIT ACCOUNT");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(257.2, 44.6);
                mainLb.setLayoutX(698.9);
                mainLb.setLayoutY(33.3);
                // Tao khung hinh chu nhat
                RoundedRect rect1 = new RoundedRect(344.3, 190.7, 980, 547.4, "transparent", "#92badd", 2, 36);

                // Tao scroll pane gom cac Profile button
                GridPane gridPane = new GridPane();
                gridPane.setHgap(27);
                gridPane.setVgap(39);
                gridPane.setPadding(new Insets(15, 10, 0, 10));
                for (int i = 0; i < testData.getUserList().size(); i++) {
                        int index = i;
                        Image image1 = new Image(
                                        testData.getUserList().get(i).getAvatar());
                        AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 0);
                        FancyButtonClass profileBtn = new FancyButtonClass(
                                        avatar1, testData.getUserList().get(i).getUsename(), 286.6, 82.6, "#c4dff8",
                                        "#2f74eb");
                        profileBtn.setOnAction(e -> {
                                // Luu thong tin user dang chon
                                AppContext.set("userSelected",
                                                testData.getUserList().get(index));
                                // Chuyen sang trang EditAcc_EditingPage
                                AppContext.set("currentPage", "EditAcc_EditingPage");
                                NavigationManager.navigateToEditAccEditingPage();
                        });
                        gridPane.add(profileBtn, i % 3, i / 3);
                }
                ScrollPane scrollPane = new ScrollPane(gridPane);
                scrollPane.setPrefSize(961.9, 508.8);
                scrollPane.setLayoutX(357.0);
                scrollPane.setLayoutY(208.0);
                scrollPane.setStyle(
                                "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                // Tao textField tim kiem
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(392);
                findAvatar.setLayoutY(110.3);
                TextField findtext = new TextField();
                setStyleFindText(findtext, 966.5, 65.9, 351.0, 100.8);

                // Tao pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, rect1, scrollPane, findtext,
                                findAvatar);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public void setStyleFindText(TextField findtext, Double width, Double height, Double x, Double y) {
                findtext.setStyle(
                                "-fx-background-color: #ffffff;"
                                                + "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 23 px; "
                                                + "-fx-border-radius: 36px; " + "-fx-background-radius: 36px; "
                                                + "-fx-border-color: #92badd;" + "-fx-border-width: 2px;"
                                                + "-fx-font-family: 'Helvetica';"
                                                + "-fx-aligment: center-left;"
                                                + "-fx-padding: 0 0 0 100;");
                findtext.setPrefSize(width, height);
                findtext.setLayoutX(x);
                findtext.setLayoutY(y);
                findtext.setPromptText("Search");
        }
}
