package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class EditProjectPage {
    public Pane getView() {
        // Tao MenuBar
        Pane menuBar = ProfileMemberPage.MenuBarStyle_Layer2(
                "file:src/main/resources/image/ImageAvatar.png", "Nguyễn Thị Huyền Trang",
                "Scrum Master", "EditProjectPage");
        // Tao button Back
        Image image1 = new Image(
                "file:src/main/resources/image/Back_Icon.png");
        ImageButtonClass btnBack = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
        btnBack.setOnAction(e -> {
            if (AppContext.get("lastPage") == "CurrentProjectPage") {
                AppContext.remove("lastPage");
                AppContext.set("currentPage", "CurrentProjectPage");
                NavigationManager.navigateToCurrentProjectPage();
            } else if (AppContext.get("lastPage") == "CompeletedProjectPage") {
                AppContext.remove("lastPage");
                AppContext.set("currentPage", "CompeletedProjectPage");
                NavigationManager.navigateToCompeletedProjectPage();
            }
        });
        // Tao text Field lam tieu de
        TextField titleProject = new TextField("Project Title");
        titleProject.setPrefSize(894.4, 68.8);
        titleProject.setLayoutX(386.2);
        titleProject.setLayoutY(63.9);
        titleProject.setStyle(
                "-fx-background-color: #c4dff8; "
                        + "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 24px; "
                        + "-fx-alignment: CENTER; "
                        + "-fx-font-family: 'Helvetica'; "
                        + "-fx-border-color: #92badd; "
                        + "-fx-border-width: 2px; "
                        + "-fx-border-radius: 36px; "
                        + "-fx-background-radius: 36px;");
        // Tao hop chua description
        RoundedRect rect = new RoundedRect(339.2, 156.1, 988.5, 570.4, "#c4dff8", "#ffffff", 0, 36);
        // Tao label description
        Label description = new Label(
                "Description: This is a detailed description of the backlog item. It includes all the necessary information that the team needs to understand the requirements and expectations for this item.");
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
        pane.getChildren().addAll(menuBar, btnBack, titleProject, rect, description, textAreaDescription);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }
}
