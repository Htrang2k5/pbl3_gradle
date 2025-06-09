package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.controllers.*;
import pbl3_gradle.models.*;

public class CommonNotificationsPage {
        public Pane getView() {
                // Create menu bar
                String userRole = "";
                if (CurrentUser.Instance.getRole() == 2) {
                        userRole = "Product Owner";
                } else if (CurrentUser.Instance.getRole() == 3) {
                        userRole = "Scrum Master";
                } else if (CurrentUser.Instance.getRole() == 4) {
                        userRole = "Development Team";
                }
                Pane menuBar = ProfileMemberPage.MenuBarStyle_Layer2(
                        "file:src/main/resources/image/ImageAvatar.png", CurrentUser.Instance.getFullName(),
                        userRole,
                        "CommonNotificationsPage");
                // Create a main label
                Label mainLb = new Label("NOTIFICATIONS");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: CENTER_LEFT; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(287.5, 41.1);
                mainLb.setLayoutX(325.5);
                mainLb.setLayoutY(35.7);
                // Create Notification list

                VBox notificationItem1 = NotificationItem("You fogot your task! Do it now!",
                                "The task in list todo, you have to do it almost ten minutes!");
                VBox notificationItem2 = NotificationItem("You have a meeting at 3PM",
                                "The meeting is about the project, please join it!");
                // Kiem tra do dai cua thong bao
                String longDescription = "This is a long description used to test how the label behaves when its content spans multiple lines. "
                                + "The purpose of this text is to simulate a realistic block of information that a user might encounter in an application. "
                                + "It includes multiple sentences to ensure that text wrapping, padding, and layout adjustments can be tested effectively. "
                                + "By using this description, developers can verify how the UI element responds to varying content lengths, font settings, "
                                + "and container resizing, which are all essential considerations when designing responsive and user-friendly interfaces.";

                VBox notificationItem3 = NotificationItem("You have a meeting at 3PM",
                                longDescription);
                VBox totalNotification = new VBox();
                totalNotification.getChildren().addAll(notificationItem1, notificationItem2,
                                notificationItem3);
                totalNotification.setSpacing(24);
                ScrollPane scrollPane = new ScrollPane(totalNotification);
                scrollPane.setStyle(
                                "-fx-background-color: transparent;"
                                                + "-fx-border-color: transparent;"
                                                + "-fx-background: transparent;");
                scrollPane.setPrefSize(1009, 623.5);
                scrollPane.setLayoutX(348);
                scrollPane.setLayoutY(100.6);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, scrollPane);
                pane.setStyle("-fx-background-color: #ffffff;");
                pane.setPrefSize(1366.0, 768.0);
                return pane;
        }

        public static VBox NotificationItem(String title, String content) {

                Label titleLabel = new Label(title);
                titleLabel.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 15px;"
                                                + " -fx-alignment: CENTER_LEFT; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");

                titleLabel.setPrefSize(783.4, 40);
                // Set a maximum width for the title label
                // Tao hinh chuong
                Image image = new Image("file:src/main/resources/image/Bell_Icon.png");
                AvatarViewClass bellIcon = new AvatarViewClass(image, 32.2, 0);
                bellIcon.setLayoutX(32);
                bellIcon.setLayoutY(19.8);

                // Tao button more
                Image image2 = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image2, 36.2, 32.5, 0, 0);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Delete");
                menuItem1.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1);
                moreButton.setOnMouseClicked(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0); // Hiện phía dưới button
                        } else {
                                contextMenu.hide();
                        }
                });
                HBox titleLayout = new HBox();
                titleLayout.getChildren().addAll(bellIcon, titleLabel, moreButton);
                titleLayout.setPrefWidth(865.4);
                titleLayout.setSpacing(7);

                VBox notificationItem = new VBox();
                Label contentLabel = new Label(content);
                contentLabel.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 15px;"
                                                + " -fx-alignment: TOP_LEFT; "
                                                + "-fx-font-family:'Arial';");
                contentLabel.setWrapText(true);
                contentLabel.setMaxWidth(834.2); // Set a maximum width for the content label
                contentLabel.setPadding(new Insets(0, 0, 0, 39.2));
                notificationItem.getChildren().addAll(titleLayout, contentLabel);
                notificationItem.setSpacing(5);
                notificationItem.setPadding(new Insets(5, 10, 35, 35));
                notificationItem.setStyle(
                                "-fx-background-color: #c4dff8;"
                                                + " -fx-border-color: #92badd;"
                                                + " -fx-border-width: 2px;"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;");
                notificationItem.setPrefWidth(941.2);
                return notificationItem;
        }
}
