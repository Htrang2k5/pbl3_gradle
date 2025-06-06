package pbl3_gradle.views;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.TestController;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ProjectMembersPage {
    public Pane getView() {
        // Create menu bar
        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Member Project", "MemberProjectPage");

        // Create main label
        Label mainLabel = new Label("PROJECT MEMBERS");
        mainLabel.setStyle(
                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        mainLabel.setPrefSize(302.2, 44.6);
        mainLabel.setLayoutX(683);
        mainLabel.setLayoutY(22.6);
        // Lay du lieu tu database
        TestController testData = new TestController();
        // Tao textField tim kiem
        Image findImage = new Image("file:src/main/resources/image/FindImage.png");
        AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
        findAvatar.setLayoutX(392);
        findAvatar.setLayoutY(110.3);
        TextField findtext = new TextField();
        EditAcc_ShowAccPage.setStyleFindText(findtext, 966.5, 65.9, 351.0, 100.8);
        // Tao khung hinh chu nhat
        RoundedRect rect1 = new RoundedRect(344.3, 190.7, 980, 540, "transparent", "#92badd", 2, 36);
        // Tao scroll pane gom cac Profile button
        GridPane gridPane = new GridPane();
        gridPane.setHgap(27);
        gridPane.setVgap(39);
        gridPane.setPadding(new Insets(15, 13, 20, 13));
        for (int i = 0; i < testData.getUserList().size(); i++) {
            Image image1 = new Image(
                    testData.getUserList().get(i).getAvatar());
            AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 0);
            FancyButtonClass profileBtn = new FancyButtonClass(
                    avatar1, testData.getUserList().get(i).getUsename(), 286.6, 82.6, "#c4dff8",
                    "#2f74eb");
            profileBtn.setOnAction(e -> {
                // khong set action, member o day chi de xem
            });
            gridPane.add(profileBtn, i % 3, i / 3);
        }
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setPadding(new Insets(0, 0, 0, 10));
        scrollPane.setPrefSize(961.9, 520);
        scrollPane.setLayoutX(353.0);
        scrollPane.setLayoutY(204.0);
        scrollPane.setStyle(
                "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // Create the pane
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, mainLabel, findAvatar, findtext, rect1, scrollPane);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        return pane;
    }
}
