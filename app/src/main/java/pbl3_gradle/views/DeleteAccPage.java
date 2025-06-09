package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.User;
import pbl3_gradle.util.AppContext;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccPage {
        private List<User> selectedUsers = new ArrayList<>();

        public Pane getView() {
                AppContext.set("selectedUsers", selectedUsers);

                // Thanh menu
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                        "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                        "DeleteAccPage");

                // Tiêu đề chính
                Label mainLb = new Label("DELETE ACCOUNT");
                mainLb.setFont(Font.font("Arial", FontWeight.BOLD, 26));
                mainLb.setStyle("-fx-text-fill: #2f74eb;");
                mainLb.setLayoutX(668.3);
                mainLb.setLayoutY(37.3);

                // Thanh tìm kiếm
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(392);
                findAvatar.setLayoutY(110.3);

                TextField findText = new TextField();
                EditAcc_ShowAccPage.setStyleFindText(findText, 966.5, 65.9, 351.0, 100.8);

                // Hình chữ nhật nền
                RoundedRect rect1 = new RoundedRect(344.3, 190.7, 980, 440, "transparent", "#92badd", 2, 36);

                // ScrollPane chứa danh sách tài khoản
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(loadUsers());
                scrollPane.setPrefSize(961.9, 414.0);
                scrollPane.setLayoutX(353.0);
                scrollPane.setLayoutY(204.0);
                scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

                // Nút Delete
                FancyButtonClass btnDelete = new FancyButtonClass("Delete", 536.2, 59.8, 559.4, 667.5);
                btnDelete.setOnAction(e -> {
                        if (selectedUsers.isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select at least one user to delete.");
                                alert.showAndWait();
                                return;
                        }

                        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure you want to delete the selected users?",
                                ButtonType.YES, ButtonType.NO);
                        confirm.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.YES) {
                                        for (User user : selectedUsers) {
//                                                DataManager.Instance.deleteUserByID(user.getUserID());
                                                DataManager.Instance.disableUser(user.getUserID());
                                        }
                                        selectedUsers.clear();
                                        scrollPane.setContent(loadUsers()); // Refresh lại
                                        AppContext.set("selectedUsers", selectedUsers);
                                }
                        });
                });

                // Lắp ráp tất cả thành phần
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, findText, findAvatar, rect1, scrollPane, btnDelete);
                pane.setStyle("-fx-background-color: #ffffff;");
                pane.setPrefSize(1366, 768);

                return pane;
        }

        private GridPane loadUsers() {
                GridPane gridPane = new GridPane();
                gridPane.setHgap(27);
                gridPane.setVgap(39);
                gridPane.setPadding(new Insets(15, 13, 20, 13));

                List<User> userList = DataManager.Instance.getAllUser();
                for (int i = 0; i < userList.size(); i++) {
                        int index = i;
                        User user = userList.get(i);

                        Image image = new Image("file:src/main/resources/image/FindImage.png");
                        AvatarViewClass avatar = new AvatarViewClass(image, 62.4, 0);

                        FancyButtonClass profileBtn = new FancyButtonClass(
                                avatar,
                                user.getUserName(),
                                286.6, 82.6,
                                "#c4dff8", "#2f74eb"
                        );

                        profileBtn.setOnAction(e -> {
                                if (selectedUsers.contains(user)) {
                                        selectedUsers.remove(user);
                                        profileBtn.setStyleSelectButton(profileBtn, 0, "#2f74eb", "#c4dff8");
                                } else {
                                        selectedUsers.add(user);
                                        profileBtn.setStyleSelectButton(profileBtn, 2, "#2f74eb", "#ffffff");
                                }
                                AppContext.set("selectedUsers", selectedUsers);
                        });

                        gridPane.add(profileBtn, i % 3, i / 3);
                }

                return gridPane;
        }
}
