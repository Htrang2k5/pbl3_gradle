        package pbl3_gradle.views;

        import javafx.scene.image.Image;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.KeyEvent;
        import javafx.scene.layout.Pane;
        import pbl3_gradle.common.*;
        import pbl3_gradle.controllers.DataManager;
        import pbl3_gradle.models.Item;
        import pbl3_gradle.models.ProductBacklog;
        import pbl3_gradle.util.AppContext;
        import pbl3_gradle.util.NavigationManager;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;

        public class DetailBacklogPage {
                public Pane getView(Item item) {
                        // Tao MenuBar
                        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Detail Backlog", "DetailBacklogPage");
                        // Tao button Back
                        Image image1 = new Image(
                                "file:src/main/resources/image/Back_Icon.png");
                        ImageButtonClass btnBack = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                        btnBack.setOnAction(e -> {
                                if (AppContext.get("lastPage") == "ProductBacklogPage") {
                                        AppContext.remove("lastPage");
                                        AppContext.set("currentPage", "ProductBacklogPage");
                                        NavigationManager.navigateToProductBacklogPage();
                                } else if (AppContext.get("lastPage") == "CurrentSprintPage") {
                                        AppContext.remove("lastPage");
                                        AppContext.set("currentPage", "CurrentSprintPage");
                                        NavigationManager.navigateToCurrentSprintPage();
                                }
                        });
                        // Tao backlog lam tieu de
                        Pane backlog = ProductBacklogPage.backlog(1, item);
                        backlog.setPrefSize(894.4, 68.8);
                        backlog.setLayoutX(386.2);
                        backlog.setLayoutY(63.9);
                        // Tao hop chua description
                        RoundedRect rect = new RoundedRect(339.2, 156.1, 988.5, 570.4, "#c4dff8", "#ffffff", 0, 36);
                        // Tao label description
                        Label description = new Label(
                                item.getDescription());
                        description.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 18px; -fx-alignment: TOP_LEFT; -fx-font-family: 'Helvetica';");
                        description.setPrefWidth(903);
                        description.setMaxHeight(519.9);
                        description.setLayoutX(386.2);
                        description.setLayoutY(182);
                        description.setWrapText(true);
                        // Tao area text de chinh sua description
                        TextArea textAreaDescription = new TextArea(description.getText());
                        textAreaDescription.setStyle(
                                "-fx-background-color: #ffffff;"
                                        + "-fx-text-fill: #2f74eb;"
                                        + "-fx-font-size: 18px; "
                                        + "-fx-font-family: 'Helvetica';"
                                        + "-fx-border-color: #c4dff8;");
                        textAreaDescription.setPrefSize(903, 519.9);
                        textAreaDescription.setLayoutX(386.2);
                        textAreaDescription.setLayoutY(182);
                        textAreaDescription.setWrapText(true);
                        textAreaDescription.setVisible(false); // An TextArea khi mo trang
                        // Hàm chuyển sang chế độ edit
                        Runnable showEditMode = () -> {
                                String current = description.getText();
                                // Nếu đang ở placeholder, clear để người dùng nhập
                                if ("Click to add a description...".equals(current)) {
                                        textAreaDescription.clear();
                                } else {
                                        textAreaDescription.setText(current);
                                        // Đặt caret ở cuối
                                        textAreaDescription.positionCaret(current.length());
                                }
                                textAreaDescription.setVisible(true);
                                textAreaDescription.setManaged(true);
                                textAreaDescription.requestFocus();
                        };

                        // Hàm commit nội dung từ TextArea về Label
                        Runnable commitEdit = () -> {
                                String newText = textAreaDescription.getText().trim();
                                if (newText.isEmpty()) {
                                        description.setText("Click to add a description...");
                                } else {
                                        description.setText(newText);
                                        item.setDescription(newText);
                                        ProductBacklog productBacklog= DataManager.Instance.getCurrentProductBacklog();
                                        DataManager.Instance.updateItemInProductBacklog(productBacklog, item);

                                }
                                // Ẩn TextArea, hiện Label
                                textAreaDescription.setVisible(false);
                                textAreaDescription.setManaged(false);
                        };

                        // Double‐click trên Label sẽ chuyển sang edit mode
                        description.setOnMouseClicked(event -> {
                                if (event.getClickCount() == 2) {
                                        showEditMode.run();
                                }
                        });

                        // Khi TextArea mất focus => commit nội dung
                        textAreaDescription.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                                if (!isNowFocused) {
                                        commitEdit.run();
                                }
                        });

                        // Nhấn Ctrl+Enter (hoặc Shift+Enter) cũng commit nội dung
                        textAreaDescription.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                                if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                                        // ngăn ngừa ký tự xuống dòng mặc định (nếu muốn)
                                        event.consume();
                                        commitEdit.run();
                                }
                        });
                        // Tao pane chinh
                        Pane pane = new Pane();
                        pane.getChildren().addAll(menuBar, btnBack, backlog, rect, description, textAreaDescription);
                        pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                        pane.setPrefSize(1366, 768);
                        pane.setLayoutX(0);
                        pane.setLayoutY(0);
                        return pane;
                }
        }