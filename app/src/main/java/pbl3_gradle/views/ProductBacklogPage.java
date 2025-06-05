package pbl3_gradle.views;

import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import pbl3_gradle.common.*;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class ProductBacklogPage {
        public Pane getView() {

                // Tao MenuBar
                Pane menuBar = MenuBarStyle_Layer3("Product Backlog", "ProductBacklogPage");
                // Tao main label
                Label mainLb = new Label("PRODUCT BACKLOG");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(359.7, 41.1);
                mainLb.setLayoutX(683);
                mainLb.setLayoutY(28.5);
                // Tao khung hinh chu nhat
                RoundedRect rect1 = new RoundedRect(357.9, 103.3, 958.5, 625.8, "transparent", "#92badd", 2, 36);
                // Tao backlog
                Pane backlog1 = backlog(1, "Backlog 1", "To Do");
                Pane backlog2 = backlog(2, "Backlog 2", "In Progress");
                Pane backlog3 = backlog(3, "Backlog 3", "Done");
                Pane backlog4 = backlog(4, "Backlog 4", "To Do");
                Pane backlog5 = backlog(5, "Backlog 5", "In Progress");
                Pane backlog6 = backlog(6, "Backlog 6", "Done");
                Pane backlog7 = backlog(7, "Backlog 7", "To Do");
                Pane backlog8 = backlog(8, "Backlog 8", "In Progress");
                Pane backlog9 = backlog(9, "Backlog 9", "Done");

                FancyButtonClass addBacklogBtn = new FancyButtonClass("+Add another backlog item", 899.5, 69.3, 0, 0);

                VBox vBox = new VBox(); // Tao VBox de chua cac backlog
                vBox.setSpacing(20); // Khoang cach giua cac backlog
                vBox.getChildren().addAll(backlog1, backlog2, backlog3, backlog4, backlog5,
                                backlog6, backlog7, backlog8, backlog9, addBacklogBtn);
                vBox.setPadding(new Insets(20, 15, 20, 30));

                ScrollPane scrollPane = new ScrollPane(vBox);
                scrollPane.setPrefSize(958.5, 625.8); // Kich thuoc scroll pane
                scrollPane.setLayoutX(357.9);
                scrollPane.setLayoutY(103.3);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Luon hien thi thanh cuon
                scrollPane.setStyle(
                                "-fx-background-color: transparent; "
                                                + "-fx-background: transparent;"
                                                + "-fx-border-color: transparent");
                // Tao pane
                Pane pane = new Pane(menuBar, mainLb, rect1, scrollPane);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public static Pane MenuBarStyle_Layer3(String text, String page) {
                // Tạo hình chữ nhật cho menu
                RoundedRect rect = new RoundedRect();
                // Tao khung hinh chu nhat cho project
                RoundedRect rect1 = new RoundedRect(15.1, 22.9, 265.9, 150.1, "#ffffff", "#92badd", 2, 12);
                // Tao ten Label cho project
                Label lbProject = new Label("Chicken learn to fly");
                //----------------------------------------
                lbProject.setPrefSize(197, 18.8);
                lbProject.setLayoutX(31.3);
                lbProject.setLayoutY(35.4);
                lbProject.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + "-fx-font-family: 'Arial';"
                                                + "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;");
                // Tao label description cho project
                Label lbDescription = new Label("Project description");
                //--------------------------------------
                lbDescription.setPrefWidth(233.4);
                lbDescription.setMaxHeight(103.3);
                lbDescription.setLayoutX(31.3);
                lbDescription.setLayoutY(59.6);
                lbDescription.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + "-fx-font-family: 'Helvetica';"
                                                + "-fx-font-size: 12px;"
                                                + "-fx-alignment: TOP_LEFT;");

                // Tao button more cho project
                Image image = new Image(
                                "file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 230, 25);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Out this project");
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
                menuItem1.setOnAction(e -> {
                        AppContext.set("currentPage", "CurrentProjectPage");
                        NavigationManager.navigateToCurrentProjectPage();
                });
                // Tao MenuBar
                MenuBarClass menuBar = new MenuBarClass(3, page);
                menuBar.setLayoutX(11.5);
                menuBar.setLayoutY(259.1);
                Pane pane = new Pane();
                pane.getChildren().addAll(rect, menuBar, rect1, lbProject,
                                lbDescription, moreButton);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(296.1, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public static Pane backlog(int number, String text, String status) {
                // Tao hinh tron danh so
                Circle circle = new Circle(34.4);
                circle.setStyle(
                                "-fx-fill: #ffffff; "
                                                + "-fx-stroke: #92badd;"
                                                + "-fx-stroke-width: 2;");
                Label lbNumber = new Label(String.valueOf(number));
                lbNumber.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + "-fx-font-family: 'Helvetica';"
                                                + "-fx-font-size: 16px;");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(circle, lbNumber);
                stackPane.setLayoutX(0);
                stackPane.setLayoutY(0);
                // Tao label cho backlog
                Label lbText = new Label(text);
                lbText.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + "-fx-font-family: 'Helvetica';"
                                                + "-fx-font-size: 16px;");
                lbText.setPrefSize(691.4, 24.7);
                lbText.setLayoutX(35.7);
                lbText.setLayoutY(17.3);
                // Tao more button
                Image image = new Image(
                                "file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 742.2, 10);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Delete");
                menuItem1.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                MenuItem menuItem2 = new MenuItem("Move to Sprint");
                menuItem2.setStyle(" -fx-font-size: 14px;"
                                + " -fx-alignment: center; "
                                + "-fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1, menuItem2);
                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0); // Hiện phía dưới button
                        } else {
                                contextMenu.hide();
                        }
                });
                Pane pane1 = new Pane();
                pane1.setPrefSize(795.2, 60);
                pane1.getChildren().addAll(lbText);
                if (!status.equals("Not Set")) {
                        pane1.getChildren().addAll(moreButton);
                }
                Button button = new Button();
                button.setGraphic(pane1);
                button.setStyle(
                                "-fx-background-color: #c4dff8;"
                                                + "-fx-background-radius: 36;"
                                                + "-fx-border-radius: 36;"
                                                + "-fx-cursor: hand;");
                if (!status.equals("Not Set")) {
                        CurrentProjectPage.applyClickEffect(button);
                        button.setOnMouseClicked(e -> {
                                NavigationManager.navigateToDetailBacklogPage();
                        });
                }
                // Tao checkbox
                CheckBox checkBox = new CheckBox(null);
                checkBox.setPrefSize(50, 70);
                checkBox.setStyle("-fx-mark-color: #2f74eb;" + // Màu của dấu check ✓
                                "-fx-box-border: #2f74eb;" + // Màu viền hộp
                                "-fx-background-color: #ffffff;");
                checkBox.setScaleX(1.1); // Phóng to theo chiều ngang
                checkBox.setScaleY(1.1); // Phóng to theo chiều dọc

                // Taoo pane
                HBox hBox = new HBox();
                hBox.setSpacing(20); // Khoang cach giua cac phan tu
                hBox.getChildren().addAll(stackPane, button);
                if (status.equals("Doing")) {
                        checkBox.setSelected(false);
                        hBox.getChildren().add(checkBox);
                }
                return hBox;
        }

}
