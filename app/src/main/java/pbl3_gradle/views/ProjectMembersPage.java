package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.*;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.User;
import pbl3_gradle.util.NavigationManager;

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
        // Tao button more
        // Nút "More"
        Image image = new Image("file:src/main/resources/image/Add_Icon.png");
        ImageButtonClass addButton = new ImageButtonClass(image, 56.9, 51, 1260.8, 20);

        // Tạo ô tìm kiếm
        Image findImage = new Image("file:src/main/resources/image/FindImage.png");
        AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
        findAvatar.toFront(); // Đặt lên trên các thành phần khác
        // findAvatar.setMouseTransparent(true);
        findAvatar.setLayoutX(392);
        findAvatar.setLayoutY(110);

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
        pane.getChildren().addAll(menuBar, mainLabel, rect1, scrollPane, findtext, findAvatar, addButton);
        pane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        addButton.setOnAction(e -> {
            VBox memberPane = createMemberOfTaskList();
            memberPane.setLayoutX(1100);
            memberPane.setLayoutY(80);
            memberPane.setPrefSize(200, 500);
            pane.getChildren().add(memberPane);
            memberPane.toFront(); // Đặt lên trên các thành phần khác
            addButton.setDisable(true); // Vô hiệu hóa nút "Add" sau khi mở
        });

        return pane;
    }

    public VBox createMemberOfTaskList() {
        VBox container = new VBox();
        container.setSpacing(10);
        container.setStyle(
                "-fx-background-color: #ffffff; "
                        + "-fx-border-color: #92badd; "
                        + "-fx-border-radius: 36; "
                        + "-fx-background-radius: 36; "
                        + "-fx-border-width: 2px; "
                        + "-fx-padding: 10; ");
        // Create a text field for searching members
        TextField searchField = new TextField();
        searchField.setPromptText("Search members...");
        searchField.setPadding(new Insets(0, 0, 0, 20));
        searchField.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 15px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; "
                        + "-fx-background-color: #ffffff; "
                        + "-fx-border-color: #92badd; "
                        + "-fx-border-radius: 20; "
                        + "-fx-background-radius: 20;"
                        + "-fx-border-width: 2px; ");
        searchField.setPrefSize(160, 40);
        container.getChildren().add(searchField);
        VBox memberList = new VBox();

        // List<User> members = DataManager.Instance.getAllUser();
        // List<User> membersOfTask =
        // DataManager.Instance.getMemberByTaskId(this.task.getIdTask());

        // for (User member : members) {
        // boolean isMember = false;
        // for (User memberInTask : membersOfTask) {

        // if (memberInTask.getUserID() == member.getUserID()) {
        // isMember = true;
        // break;
        // }
        // }
        // HBox memberBox = members(member, isMember);
        // memberList.getChildren().add(memberBox);
        // }
        HBox memberBox1 = members(new User(), true);
        HBox memberBox2 = members(new User(), false);
        HBox memberBox3 = members(new User(), true);
        memberList.getChildren().addAll(memberBox1, memberBox2, memberBox3);
        ScrollPane scrollPane = new ScrollPane(memberList);
        scrollPane.setFitToWidth(true); // Đặt chiều rộng của ScrollPane bằng với VBox
        scrollPane.setMinHeight(100); // Chiều cao tối thiểu
        scrollPane.setMaxHeight(280); // Chiều cao tối đa
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; "
                        + "-fx-border-color: transparent; -fx-border-width: 0px; ");
        // Add the scroll pane to the container
        container.getChildren().add(scrollPane);
        return container;
    }

    public HBox members(User user, Boolean isMember) {
        // Create a HBox to hold the member information
        HBox memberBox = new HBox();
        memberBox.setSpacing(5);
        memberBox.setPadding(new Insets(0, 0, 0, 10));
        // memberBox.setPrefHeight(24.7);
        // Content
        Image image = new Image("file:src/main/resources/image/ImageAvatar.png");
        AvatarViewClass avatar = new AvatarViewClass(image, 24.7, 0);
        Label nameLabel = new Label(user.getUserName());
        nameLabel.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 15px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        nameLabel.setPrefSize(90, 24.7);
        memberBox.getChildren().addAll(avatar, nameLabel);
        if (isMember)
        // Create a button to remove member
        {
            Image xImage = new Image(
                    "file:src/main/resources/image/X_Icon.png");
            ImageButtonClass removeButton = new ImageButtonClass(xImage, 24.7, 24.7, 0, 0);
            removeButton.setOnAction(e -> {
                // DataManager.Instance.removeMemberFromTask(user, this.task.getIdTask());
                // NavigationManager.navigateToDetailTaskPage(task);
            });
            memberBox.getChildren().add(removeButton);
        } else {
            Image addImage = new Image(
                    "file:src/main/resources/image/Add_Icon.png");
            ImageButtonClass addButton = new ImageButtonClass(addImage, 24.7, 24.7, 0, 0);
            addButton.setOnAction(e -> {
                // DataManager.Instance.addMemberToTask(user, this.task.getIdTask());
                // NavigationManager.navigateToDetailTaskPage(task);
            });
            memberBox.getChildren().add(addButton);
        }
        memberBox.setFillHeight(false);
        return memberBox;
    }
}
