package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class EditAcc_ShowAccPage {
        public Pane getView() {
                // Thanh menu bar
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png",
                                "Administrator",
                                "EditAcc_ShowAccPage");

                // Tiêu đề chính
                Label mainLb = new Label("EDIT ACCOUNT");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-font-family: Arial;"
                                                + " -fx-font-weight: bold;");
                mainLb.setAlignment(Pos.CENTER);
                mainLb.setPrefSize(257.2, 44.6);
                mainLb.setLayoutX(698.9);
                mainLb.setLayoutY(33.3);

                // Khung hình chữ nhật trang trí
                RoundedRect rect1 = new RoundedRect(
                                344.3, 190.7,
                                980, 547.4,
                                "transparent", "#92badd",
                                2, 36);

                // ScrollPane chứa danh sách người dùng
                ScrollPane scrollPane = loadUserListScrollPane();

                // TextField và icon tìm kiếm
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass findAvatar = new AvatarViewClass(findImage, 46.8, 0);
                findAvatar.setLayoutX(392);
                findAvatar.setLayoutY(110.3);

                TextField findtext = new TextField();
                setStyleFindText(findtext, 966.5, 65.9, 351.0, 100.8);
                findtext.setAlignment(Pos.CENTER_LEFT);

                // Pane chính
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, rect1, scrollPane, findtext, findAvatar);
                pane.setStyle("-fx-background-color: #ffffff;");
                pane.setPrefSize(1366, 768);

                return pane;
        }

        private ScrollPane loadUserListScrollPane() {
                GridPane gridPane = new GridPane();
                gridPane.setHgap(27);
                gridPane.setVgap(39);
                gridPane.setPadding(new Insets(15, 10, 20, 10));

                var userList = DataManager.Instance.getAllUser();
                for (int i = 0; i < userList.size(); i++) {
                        int index = i;
                        // Dùng avatar mặc định nếu chưa có
                        Image image1 = new Image("file:src/main/resources/image/ImageAvatar.png");
                        AvatarViewClass avatar1 = new AvatarViewClass(image1, 62.4, 2);

                        FancyButtonClass profileBtn = new FancyButtonClass(
                                        avatar1,
                                        userList.get(i).getUserName(),
                                        286.6, 82.6,
                                        "#c4dff8", "#2f74eb");

                        profileBtn.setOnAction(e -> {
                                AppContext.set("userSelected", userList.get(index));
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
                                "-fx-background-color: transparent;"
                                                + " -fx-background: transparent;"
                                                + " -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

                return scrollPane;
        }

        public static void setStyleFindText(TextField findtext,
                        Double width, Double height,
                        Double x, Double y) {
                findtext.setStyle(
                                "-fx-background-color: #ffffff;"
                                                + " -fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 23px;"
                                                + " -fx-border-radius: 36px;"
                                                + " -fx-background-radius: 36px;"
                                                + " -fx-border-color: #92badd;"
                                                + " -fx-border-width: 2px;"
                                                + " -fx-font-family: Helvetica;"
                                                + " -fx-padding: 0 0 0 100;");
                findtext.setPrefSize(width, height);
                findtext.setLayoutX(x);
                findtext.setLayoutY(y);
                findtext.setPromptText("Search");
        }
}
