package pbl3_gradle.views;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import pbl3_gradle.common.*;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.Item;
import pbl3_gradle.models.ProductBacklog;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

import java.util.Date;
import java.util.List;

public class ProductBacklogPage {
        private static VBox vBox;

        public Pane getView() {
                // Menu bar
                Pane menuBar = MenuBarStyle_Layer3("Product Backlog", "ProductBacklogPage");

                // Label tiêu đề
                Label mainLb = new Label("PRODUCT BACKLOG");
                mainLb.setStyle("-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family:'Arial'; -fx-font-weight: bold;");
                mainLb.setPrefSize(359.7, 41.1);
                mainLb.setLayoutX(683);
                mainLb.setLayoutY(28.5);

                // Viền khung scroll
                RoundedRect rect1 = new RoundedRect(357.9, 103.3, 958.5, 625.8, "transparent", "#92badd", 2, 36);

                // VBox chứa các backlog
                vBox = new VBox();
                vBox.setSpacing(20);
                vBox.setPadding(new Insets(20, 15, 20, 30));
                loadBacklogItems(); // Gọi hàm để load backlog vào VBox

                // Scroll pane chứa VBox
                ScrollPane scrollPane = new ScrollPane(vBox);
                scrollPane.setPrefSize(958.5, 625.8);
                scrollPane.setLayoutX(357.9);
                scrollPane.setLayoutY(103.3);
                scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
                scrollPane.setStyle(
                        "-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent");

                // Pane chính
                Pane pane = new Pane(menuBar, mainLb, rect1, scrollPane);
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);

                return pane;
        }

        public static void loadBacklogItems() {
                vBox.getChildren().clear();
                ProductBacklog productBacklog = DataManager.Instance.getCurrentProductBacklog();
                List<Item> listItem = DataManager.Instance.getAllItemByBacklog(productBacklog.getIdProductBacklog(), 1);

                for (int i = 0; i < listItem.size(); i++) {
                        Pane backlogPane = backlog(i + 1, listItem.get(i));
                        vBox.getChildren().add(backlogPane);
                }

                // Nút add backlog cuối cùng
                FancyButtonClass addBacklogBtn = new FancyButtonClass("+Add another backlog item", 899.5, 69.3, 0, 0);
                addBacklogBtn.setOnAction(e -> {
                        Item newItem = new Item();
                        newItem.setStatus(false);
                        newItem.setTitle("New Backlog Item");
                        newItem.setDescription("Description of the new backlog item");
                        DataManager.Instance.addNewItemToProductBacklog(productBacklog, newItem);
                        loadBacklogItems(); // Reload lại sau khi thêm
                });
                vBox.getChildren().add(addBacklogBtn);
        }

        public static Pane MenuBarStyle_Layer3(String text, String page) {
                RoundedRect rect = new RoundedRect();
                RoundedRect rect1 = new RoundedRect(15.1, 22.9, 265.9, 150.1, "#ffffff", "#92badd", 2, 12);

                Label lbProject = new Label(CurrentProject.Instance.getProjectName());
                lbProject.setPrefSize(197, 18.8);
                lbProject.setLayoutX(31.3);
                lbProject.setLayoutY(35.4);
                lbProject.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");

                Label lbDescription = new Label("Project description");
                lbDescription.setPrefWidth(233.4);
                lbDescription.setMaxHeight(103.3);
                lbDescription.setLayoutX(31.3);
                lbDescription.setLayoutY(59.6);
                lbDescription.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-alignment: TOP_LEFT;");

                Image image = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 230, 25);
                ContextMenu contextMenu = new ContextMenu();

                MenuItem menuItem1 = new MenuItem("Out this project");
                menuItem1.setStyle(" -fx-font-size: 14px; -fx-alignment: center; -fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1);

                moreButton.setOnMouseClicked(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0);
                        } else {
                                contextMenu.hide();
                        }
                });

                menuItem1.setOnAction(e -> {
                        AppContext.set("currentPage", "CurrentProjectPage");
                        NavigationManager.navigateToCurrentProjectPage();

                });

                MenuBarClass menuBar = new MenuBarClass(3, page);
                menuBar.setLayoutX(11.5);
                menuBar.setLayoutY(259.1);

                Pane pane = new Pane();
                pane.getChildren().addAll(rect, menuBar, rect1, lbProject, lbDescription, moreButton);
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(296.1, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);

                return pane;
        }

        public static Pane backlog(int number, Item item) {
                Circle circle = new Circle(34.4);
                circle.setStyle("-fx-fill: #ffffff; -fx-stroke: #92badd; -fx-stroke-width: 2;");
                Label lbNumber = new Label(String.valueOf(number));
                lbNumber.setStyle("-fx-text-fill: #2f74eb; -fx-font-family: 'Helvetica'; -fx-font-size: 16px;");
                StackPane stackPane = new StackPane(circle, lbNumber);
                stackPane.setLayoutX(0);
                stackPane.setLayoutY(0);

                Label lbText = new Label(item.getTitle());
                lbText.setStyle("-fx-text-fill: #2f74eb; -fx-font-family: 'Helvetica'; -fx-font-size: 16px;");
                lbText.setPrefSize(691.4, 24.7);
                lbText.setLayoutX(35.7);
                lbText.setLayoutY(17.3);
                // Tao textField click 2 lan chuot vao de sua label
                TextField textField = new TextField(lbText.getText());
                textField.setPrefSize(691.4, 24.7);
                textField.setLayoutX(35.7);
                textField.setLayoutY(17.3);
                textField.setStyle(
                        "-fx-text-fill: #2f74eb;"
                                + "-fx-font-family: 'Helvetica';"
                                + "-fx-font-size: 16px;"
                                + "-fx-background-color: #ffffff;"
                                + "-fx-border-color: #92badd;");
                textField.setVisible(false);

                Image image = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 742.2, 10);
                ContextMenu contextMenu = new ContextMenu();

                MenuItem menuItem1 = new MenuItem("Delete");
                menuItem1.setOnAction(e -> {
                        ProductBacklog productBacklog = DataManager.Instance.getCurrentProductBacklog();
                        DataManager.Instance.removeItemFromProductBacklog(productBacklog, item);
                        loadBacklogItems(); // Reload lại sau khi xóa
                });
                menuItem1.setStyle(" -fx-font-size: 14px; -fx-alignment: center; -fx-font-family:'Helvetica';");

                MenuItem menuItem2 = new MenuItem("Move to Sprint");
                menuItem2.setStyle(" -fx-font-size: 14px; -fx-alignment: center; -fx-font-family:'Helvetica';");

                menuItem2.setOnAction(e -> {
                        Boolean check = DataManager.Instance.checkCurrentSprint(CurrentProject.Instance.getIdProject());
                        if (!check) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("No current sprint available. Please create a sprint first.");
                                alert.showAndWait();
                                return;
                        }
                        item.setBacklogType(0);
                        ProductBacklog productBacklog= DataManager.Instance.getCurrentProductBacklog();
                        DataManager.Instance.updateItemInProductBacklog(productBacklog, item);
                        loadBacklogItems();
                });

                contextMenu.getItems().addAll(menuItem1, menuItem2);

                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0);
                        } else {
                                contextMenu.hide();
                        }
                });

                Pane pane1 = new Pane(lbText, textField);
                pane1.setPrefSize(795.2, 60);



                Button button = new Button();
                button.setGraphic(pane1);
                button.setStyle("-fx-background-color: #c4dff8; -fx-background-radius: 36; -fx-border-radius: 36; -fx-cursor: hand;");
                CurrentProjectPage.applyClickEffect(button);
                if (AppContext.get("currentPage").equals("DetailBacklogPage")) {
                        button.setOnMouseClicked(event -> {
                                if (event.getClickCount() == 2) {
                                        textField.setText(lbText.getText());
                                        lbText.setVisible(false);
                                        textField.setVisible(true);
                                        textField.requestFocus();
                                        textField.selectAll();
                                }
                        });
                        textField.setOnAction(e -> {
                                lbText.setText(textField.getText());
                                item.setTitle( textField.getText());
                                ProductBacklog productBacklog= DataManager.Instance.getCurrentProductBacklog();
                                DataManager.Instance.updateItemInProductBacklog(productBacklog, item);
                                textField.setVisible(false);
                                lbText.setVisible(true);
                        });
                } else if (AppContext.get("currentPage").equals("ProductBacklogPage")) {
                        pane1.getChildren().add(moreButton);
                        button.setOnMouseClicked(e -> {
                                AppContext.set("lastPage", "ProductBacklogPage");
                                AppContext.set("currentPage", "DetailBacklogPage");
                                NavigationManager.navigateToDetailBacklogPage(item);
                        });
                } else if (AppContext.get("currentPage").equals("CurrentSprintPage")) {

                        button.setOnMouseClicked(e -> {NavigationManager.navigateToDetailBacklogPage(item);
                                AppContext.set("lastPage", "CurrentSprintPage");
                                AppContext.set("currentPage", "DetailBacklogPage");
                        });
                }

                CheckBox checkBox = new CheckBox();
                checkBox.setPrefSize(50, 70);
                checkBox.setStyle("-fx-mark-color: #2f74eb; -fx-box-border: #2f74eb; -fx-background-color: #ffffff;");
                checkBox.setScaleX(1.1);
                checkBox.setScaleY(1.1);

                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.getChildren().addAll(stackPane, button);

                if (item.getStatus().equals("Doing")) {
                        checkBox.setSelected(false);
                        hBox.getChildren().add(checkBox);
                }

                return hBox;
        }
}