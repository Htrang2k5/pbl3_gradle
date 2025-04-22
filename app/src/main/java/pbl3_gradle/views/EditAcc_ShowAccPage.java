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

public class EditAcc_ShowAccPage {
        public Pane getView() {
                // Lay du lieu tu database
                TestController testData = new TestController();
                // Tạo hình chữ nhật cho menu
                RoundedRect rect = new RoundedRect();
                // Tạo hình avatar
                Image image = new Image(
                                "file:src/main/resources/image/ImageAvatar.png");

                AvatarViewClass avatar = new AvatarViewClass(image, 162.3, 2);
                avatar.setLayoutX(67.3);
                avatar.setLayoutY(38.3);
                // Tao Label cho avatar
                Label avarLb = new Label("Administrator");
                avarLb.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 20px; -fx-alignment: center; -fx-font-family:'Helvetica';");
                avarLb.setPrefSize(195.1, 31.7);
                avarLb.setLayoutX(50.5);
                avarLb.setLayoutY(218.7);
                // Tao MenuBar
                MenuBarClass menuBar = new MenuBarClass(1, "EditAcc_ShowAccPage");
                menuBar.setLayoutX(15.5);
                menuBar.setLayoutY(347.7);
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
                        Image image1 = new Image(
                                        testData.getUserList().get(i).getAvatar());
                        AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 0);
                        FancyButtonClass profileBtn = new FancyButtonClass(
                                        avatar1, testData.getUserList().get(i).getUsename(), 286.6, 82.6, "#c4dff8",
                                        "#2f74eb");
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
                pane.getChildren().addAll(rect, avatar, avarLb, menuBar, mainLb, rect1, scrollPane, findtext,
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
