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
import pbl3_gradle.controllers.*;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.User;

import java.util.List;

public class ProjectMembersPage {
    public Pane getView() {
        // Tạo menu bar
        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Member Project", "MemberProjectPage");

        // Tạo tiêu đề chính
        Label mainLabel = new Label("PROJECT MEMBERS");
        mainLabel.setStyle(
                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        mainLabel.setPrefSize(302.2, 44.6);
        mainLabel.setLayoutX(683);
        mainLabel.setLayoutY(22.6);

        // Tạo ô tìm kiếm
        Image findImage = new Image("file:src/main/resources/image/FindImage.png");
        AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
        findAvatar.setLayoutX(392);
        findAvatar.setLayoutY(110.3);

        TextField findtext = new TextField();
        EditAcc_ShowAccPage.setStyleFindText(findtext, 966.5, 65.9, 351.0, 100.8);

        // Khung hình chữ nhật nền
        RoundedRect rect1 = new RoundedRect(344.3, 190.7, 980, 540, "transparent", "#92badd", 2, 36);

        // Tạo GridPane chứa các profile
        GridPane gridPane = new GridPane();
        gridPane.setHgap(27);
        gridPane.setVgap(39);
        gridPane.setPadding(new Insets(15, 13, 20, 13));

        // Lấy danh sách thành viên từ DB theo idProject

        int projectId = CurrentProject.Instance.getIdProject();
        List<User> memberList = DataManager.Instance.getMemberByProject(projectId);

        for (int i = 0; i < memberList.size(); i++) {
            User user = memberList.get(i);
            System.out.println(user.getAvatar());
            Image image1 = new Image("file:src/main/resources/image/ImageAvatar.png"); // Đường dẫn avatar lấy từ DB
            AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 0);

            FancyButtonClass profileBtn = new FancyButtonClass(
                    avatar1, user.getUserName(), 286.6, 82.6, "#c4dff8", "#2f74eb");

            profileBtn.setOnAction(e -> {
                // Không làm gì khi click - chỉ để xem
            });

            gridPane.add(profileBtn, i % 3, i / 3);
        }

        // Tạo scroll pane bao ngoài
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setPadding(new Insets(0, 0, 0, 10));
        scrollPane.setPrefSize(961.9, 520);
        scrollPane.setLayoutX(353.0);
        scrollPane.setLayoutY(204.0);
        scrollPane.setStyle(
                "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Tạo tổng thể giao diện
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, mainLabel, findAvatar, findtext, rect1, scrollPane);
        pane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        return pane;
    }
}
