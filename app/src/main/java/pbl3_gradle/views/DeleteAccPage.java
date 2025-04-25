package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.controllers.TestController;
import pbl3_gradle.models.UserClass;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccPage {
        public Pane getView() {
                // Lay du lieu tu database
                TestController testData = new TestController();
                // Tao danh sach de luu cac user duoc chon
                List<UserClass> selectedUers = new ArrayList<>();
                AppContext.set("selectedUsers", selectedUers);
                // Tao menu bar
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                                "DeleteAccPage");
                // Tao main label
                Label mainLb = new Label("DELETE ACCOUNT");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(318.3, 44.6);
                mainLb.setLayoutX(668.3);
                mainLb.setLayoutY(37.3);

                // Tao textField tim kiem
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(392);
                findAvatar.setLayoutY(110.3);
                TextField findtext = new TextField();
                EditAcc_ShowAccPage.setStyleFindText(findtext, 966.5, 65.9, 351.0, 100.8);
                // Tao khung hinh chu nhat
                RoundedRect rect1 = new RoundedRect(344.3, 190.7, 980, 440, "transparent", "#92badd", 2, 36);
                // Tao scroll pane gom cac Profile button
                GridPane gridPane = new GridPane();
                gridPane.setHgap(27);
                gridPane.setVgap(39);
                gridPane.setPadding(new Insets(15, 13, 20, 13));
                for (int i = 0; i < testData.getUserList().size(); i++) {
                        int index = i;
                        Image image1 = new Image(
                                        testData.getUserList().get(i).getAvatar());
                        AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 0);
                        FancyButtonClass profileBtn = new FancyButtonClass(
                                        avatar1, testData.getUserList().get(i).getUsename(), 286.6, 82.6, "#c4dff8",
                                        "#2f74eb");
                        profileBtn.setOnAction(e -> {
                                if (selectedUers.contains(testData.getUserList().get(index))) {
                                        selectedUers.remove(testData.getUserList().get(index));
                                        profileBtn.setStyleSelectButton(profileBtn, 0, "#2f74eb", "#c4dff8");

                                } else {
                                        selectedUers.add(testData.getUserList().get(index));
                                        profileBtn.setStyleSelectButton(profileBtn, 2, "#2f74eb", "#ffffff");
                                }
                                AppContext.set("selectedUsers", selectedUers);
                        });
                        gridPane.add(profileBtn, i % 3, i / 3);
                }
                ScrollPane scrollPane = new ScrollPane(gridPane);
                scrollPane.setPrefSize(961.9, 414.0);
                scrollPane.setLayoutX(353.0);
                scrollPane.setLayoutY(204.0);
                scrollPane.setStyle(
                                "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                // Tao button delete
                FancyButtonClass btnDelete = new FancyButtonClass("Delete", 536.2, 59.8, 559.4, 667.5);
                // Tao pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, findtext, findAvatar, rect1, scrollPane, btnDelete);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }
}
