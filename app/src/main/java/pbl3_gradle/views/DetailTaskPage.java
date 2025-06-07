package pbl3_gradle.views;

import java.time.LocalDate;
import java.util.function.Consumer;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import pbl3_gradle.common.*;
import pbl3_gradle.util.*;
import javafx.scene.control.*;

public class DetailTaskPage {
    public Pane getView() {
        // Create MenuBar
        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Detail Task", "DetailTaskPage");
        // Tao hinh chu nhat bao phu noi dung
        RoundedRect contentBackground = new RoundedRect(334.8, 65, 984.6, 669.1, "#ffffff", "#92badd", 2, 36);
        // Create a button back
        Image image1 = new Image(
                "file:src/main/resources/image/Back_Icon.png");
        ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
        backButton.setOnAction(e -> {
            AppContext.set("currentPage", "KanbanBoardPage");
            NavigationManager.navigateToKanbanBoardPage();
        });
        // Create a label for the task name
        HBox taskNameBox = MainLabelOfTask(" Implement Feature X");
        // Create a description area
        VBox descriptionBox = descriptionArea(
                "This is a detailed description of the task. It can include multiple lines of text, "
                        + "formatting, and other information relevant to the task.");
        // Create a member area
        HBox memberBox = memberArea();
        // Create a comment area
        VBox commentBox = commentArea();
        // Create a date area
        HBox dateBox = dateArea("2023/10/31");

        // Create a Vbox containing the content
        VBox contenBox = new VBox();
        contenBox.setSpacing(15);
        contenBox.getChildren().addAll(taskNameBox, memberBox, dateBox, descriptionBox, commentBox);
        ScrollPane scrollPane = new ScrollPane(contenBox);
        scrollPane.setPrefSize(700.1, 645);
        scrollPane.setLayoutX(354.3);
        scrollPane.setLayoutY(76.8);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; "
                        + "-fx-border-color: transparent; -fx-border-width: 0px; ");
        // Create a vbox containing the buttons
        VBox buttonBox = listButton();
        buttonBox.setLayoutX(1082.3);
        buttonBox.setLayoutY(80);

        // Tao Pane chinh
        Pane mainPane = new Pane();
        mainPane.getChildren().addAll(menuBar, contentBackground, backButton, scrollPane, buttonBox);
        mainPane.setPrefSize(1366, 768);
        mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
        return mainPane;
    }

    public static HBox MainLabelOfTask(String labelName) {
        // Create a checkbox for the task
        CheckBox checkBox = new CheckBox(null);
        checkBox.setStyle("-fx-mark-color: #2f74eb;" + // Màu của dấu check ✓
                "-fx-box-border: #2f74eb;" + // Màu viền hộp
                "-fx-background-color: transparent;");
        checkBox.setPrefSize(25, 25);
        checkBox.setScaleX(1.5);
        checkBox.setScaleY(1.5);
        // Create a Label for the task name
        Label taskLabel = new Label(labelName);
        taskLabel.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: TOP_LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        taskLabel.setPrefWidth(628);
        taskLabel.setWrapText(true);
        // Create a TextField for editing the task name
        TextField taskTextField = new TextField(labelName);
        taskTextField.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: TOP_LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        taskTextField.setPrefWidth(628);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0, 0, 10, 10));
        hbox.getChildren().addAll(checkBox, taskLabel);
        // Set effects change task name
        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                taskTextField.setText(taskLabel.getText());
                hbox.getChildren().set(1, taskTextField); // Replace label with text fiel
                taskTextField.requestFocus();
                taskTextField.selectAll();
            }
        });
        taskTextField.setOnAction(e -> {
            taskLabel.setText(taskTextField.getText());
            hbox.getChildren().set(1, taskLabel); // Replace text field with label
        });
        hbox.setPrefWidth(685.8);
        return hbox;
    }

    public static VBox descriptionArea(String description) {
        // Create a desciption icon
        Image image2 = new Image(
                "file:src/main/resources/image/Description_Icon.png");
        ImageView descriptionIcon = new ImageView(image2);
        descriptionIcon.setFitWidth(38); // Set the width of the icon
        descriptionIcon.setFitHeight(38); // Set the height of the icon
        // Create a main label for the description
        Label descriptionLabel = new Label("Description");
        descriptionLabel.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        descriptionLabel.setPrefSize(562.1, 38);
        // Creeate a more button
        Image image = new Image(
                "file:src/main/resources/image/MoreIcon2.png");
        ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 250, 0);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Delete all tasks");
        menuItem1.setStyle(" -fx-font-size: 14px;"
                + " -fx-alignment: center; "
                + "-fx-font-family:'Helvetica';");
        MenuItem menuItem2 = new MenuItem("Delete this task list");
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
        // Tao mot hbox de chua cac thanh phan phia tren
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(descriptionIcon, descriptionLabel, moreButton);
        // Create a TextArea for the description
        VBox descriptionBox = createDescriptionBox(description, 683.9, 10);
        // Tao container chua cac thanh phan
        VBox container = new VBox();
        container.setSpacing(5);
        container.getChildren().addAll(hbox, descriptionBox);
        return container;
    }

    public static VBox createDescriptionBox(String initialText, double width, int lineNumber) {
        // Container chính
        VBox container = new VBox();
        container.setSpacing(5);
        container.setStyle(
                "-fx-background-color: #c4dff8; " + // màu nền nhẹ (giống vùng card mô tả)
                        "-fx-background-radius: 18; " + // bo góc
                        "-fx-border-radius: 18;");
        // Đặt kích thước cố định cho container
        container.setPrefWidth(width);

        // Label để hiển thị nội dung
        Label displayLabel = new Label(initialText);
        displayLabel.setWrapText(true);
        displayLabel.setPadding(new Insets(5, 10, 5, 15));
        displayLabel.setPrefWidth(width - 30);
        displayLabel.setStyle("-fx-font-size: 15px;"
                + "-fx-text-fill: #2f74eb;"
                + "-fx-family-font: 'Helvetica';"
                + "-fx-alignment: TOP_LEFT;");
        ScrollPane scrollPane = new ScrollPane(displayLabel);
        scrollPane.setFitToWidth(true); // Đặt chiều rộng của ScrollPane bằng với Label
        scrollPane.setMinHeight(50);
        scrollPane.setMaxHeight(300);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; "
                        + "-fx-border-color: transparent; -fx-border-width: 0px; ");
        // TextArea để chỉnh sửa nội dung
        TextArea editArea = new TextArea();
        editArea.setWrapText(true);
        editArea.setPrefRowCount(lineNumber); // mặc định hiển thị 10 dòng, khi cần sẽ scroll
        editArea.setPadding(new Insets(5));
        editArea.setStyle(
                "-fx-font-size: 15px; " +
                        "-fx-focus-color:rgb(55, 77, 95); " + // viền khi focus (giống bootstrap)
                        "-fx-faint-focus-color: rgba(102, 175, 233, 0.35);" // viền mờ khi focus
                        + "-fx-text-fill: #2f74eb;"
                        + "-fx-font-family: 'Helvetica';"
                        + "-fx-alignment: TOP_LEFT");
        editArea.setVisible(false);
        editArea.setManaged(false); // khi invisible thì không chiếm chỗ
        // Hàm chuyển sang chế độ edit
        Runnable showEditMode = () -> {
            String current = displayLabel.getText();
            // Nếu đang ở placeholder, clear để người dùng nhập
            if ("Click to add a description...".equals(current)) {
                editArea.clear();
            } else {
                editArea.setText(current);
                // Đặt caret ở cuối
                editArea.positionCaret(current.length());
            }
            scrollPane.setVisible(false);
            scrollPane.setManaged(false);

            editArea.setVisible(true);
            editArea.setManaged(true);
            editArea.requestFocus();
        };

        // Hàm commit nội dung từ TextArea về Label
        Runnable commitEdit = () -> {
            String newText = editArea.getText().trim();
            if (newText.isEmpty()) {
                displayLabel.setText("Click to add a description...");
            } else {
                displayLabel.setText(newText);
            }
            // Ẩn TextArea, hiện Label
            editArea.setVisible(false);
            editArea.setManaged(false);

            scrollPane.setVisible(true);
            scrollPane.setManaged(true);
        };

        // Double‐click trên Label sẽ chuyển sang edit mode
        scrollPane.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                showEditMode.run();
            }
        });

        // Khi TextArea mất focus => commit nội dung
        editArea.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                commitEdit.run();
            }
        });

        // Nhấn Ctrl+Enter (hoặc Shift+Enter) cũng commit nội dung
        editArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                // ngăn ngừa ký tự xuống dòng mặc định (nếu muốn)
                event.consume();
                commitEdit.run();
            }
        });

        // Thêm cả Label và TextArea vào container (TextArea ban đầu bị ẩn)
        container.getChildren().addAll(scrollPane, editArea);

        return container;
    }

    public static HBox memberArea() { // Truyen danh sach thanh vien cua task vao day
        // Create a label for the members
        Label memberLabel = new Label("Members: ");
        memberLabel.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        memberLabel.setPrefSize(100, 32);
        // Create a container for the member list view
        HBox container = new HBox();
        container.setSpacing(2);
        container.getChildren().add(memberLabel);
        for (int i = 0; i < 5; i++) {
            Image image = new Image("file:src/main/resources/image/ImageAvatar.png");
            AvatarViewClass avatar = new AvatarViewClass(image, 32, 0);
            container.getChildren().add(avatar);
        }
        container.setPadding(new Insets(0, 0, 0, 25));
        return container;
    }

    public static VBox commentArea() {
        // Create comment icon
        Image image2 = new Image(
                "file:src/main/resources/image/Comment_Icon.png");
        ImageView commentIcon = new ImageView(image2);
        commentIcon.setFitWidth(38); // Set the width of the icon
        commentIcon.setFitHeight(38); // Set the height of the icon
        // Create a main label for the comment
        Label commentLabel = new Label("Comment");
        commentLabel.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        commentLabel.setPrefSize(562.1, 38);
        // Creeate a more button
        Image image = new Image(
                "file:src/main/resources/image/MoreIcon2.png");
        ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 250, 0);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Add a new comment");
        menuItem1.setStyle(" -fx-font-size: 14px;"
                + " -fx-alignment: center; "
                + "-fx-font-family:'Helvetica';");
        MenuItem menuItem2 = new MenuItem("Delete all comments");
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
        // Tao mot hbox de chua cac thanh phan phia tren
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(commentIcon, commentLabel, moreButton);
        // Create a VBox to hold the comment area
        VBox totalArea = new VBox();
        totalArea.setSpacing(5);
        totalArea.getChildren().add(hbox);
        // Create list comment box
        for (int i = 0; i < 5; i++) {
            totalArea.getChildren().add(commentBox());
        }
        return totalArea;
    }

    public static HBox commentBox() {
        HBox comment = new HBox();
        comment.setFillHeight(false); // Không tự động điều chỉnh chiều cao của HBox
        comment.setSpacing(5);
        // Create a avatar for the comment
        Image image = new Image("file:src/main/resources/image/ImageAvatar.png");
        AvatarViewClass avatar = new AvatarViewClass(image, 32, 0);
        // Create a content box for the comment
        VBox contentBox = createDescriptionBox("This is a comment on the task. It can include multiple lines of text, "
                + "formatting, and other info relevant to the task.", 639.5, 4);
        comment.getChildren().addAll(avatar, contentBox);
        return comment;
    }

    public static VBox listButton() {
        FancyButtonClass joinButton = new FancyButtonClass("Join", 196, 51.2, 0, 0);
        FancyButtonClass memberButton = new FancyButtonClass("Members", 196, 51.2, 0, 0);
        FancyButtonClass dateButton = new FancyButtonClass("Date", 196, 51.2, 0, 0);
        FancyButtonClass deleteButton = new FancyButtonClass("Delete", 196, 51.2, 0, 0);
        FancyButtonClass leaveButton = new FancyButtonClass("Leave", 196, 51.2, 0, 0);
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.getChildren().addAll(joinButton, memberButton, dateButton, deleteButton);

        joinButton.setOnAction(e -> {
            vbox.getChildren().set(0, leaveButton);
        });
        leaveButton.setOnAction(e -> {
            vbox.getChildren().set(0, joinButton);
        });
        dateButton.setOnAction(e -> {
            if (vbox.getChildren().size() > 4) {
                vbox.getChildren().remove(4); // Xóa pane thành viên nếu đã có
            } else {
                // Hiện DatePicker
                VBox datePickerPane = createDueDatePickerPane(LocalDate.now(), selectedDate -> {
                    if (selectedDate != null) {
                        // dueDateLabel.setText("Due Date: " + selectedDate);
                        System.out.println("Người dùng chọn ngày: " + selectedDate);
                        // Xu ly du lieu dua vao db o day///
                    } else {
                        // dueDateLabel.setText("Chưa có Due Date");
                    }
                });
                vbox.getChildren().add(datePickerPane);
                datePickerPane.setVisible(true);
                datePickerPane.setManaged(true);
            }
        });

        memberButton.setOnAction(e -> {
            if (vbox.getChildren().size() > 4) {
                vbox.getChildren().remove(4); // Xóa pane thành viên nếu đã có
            } else {
                VBox memberPane = createMemberOfTaskList();
                vbox.getChildren().add(memberPane);
                memberPane.setVisible(true);
                memberPane.setManaged(true);
            }
        });
        return vbox;

    }

    public static HBox dateArea(String date) {
        Label main = new Label("Dute Date: ");
        main.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        main.setPrefSize(100, 32);
        Label dateString = new Label(date);
        dateString.setStyle(
                "-fx-text-fill: #2f74eb; "
                        + "-fx-font-size: 18px;"
                        + "-fx-alignment: LEFT; "
                        + "-fx-font-family: 'Helvetica'; ");
        dateString.setPrefSize(100, 32);
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0, 0, 0, 25));
        hbox.getChildren().addAll(main, dateString);
        return hbox;
    }

    public static VBox createDueDatePickerPane(LocalDate initialDate, Consumer<LocalDate> onDateConfirmed) {
        // 1. Tạo một VBox để chứa các thành phần UI, với khoảng cách (spacing) giữa các
        // con là 10px
        VBox container = new VBox(10);
        container.setPadding(new Insets(10)); // padding 10px tất cả các cạnh
        container.setStyle("-fx-background-color: #F9F9F9; -fx-border-color: #CCCCCC; -fx-border-width: 1;");

        // 2. Tạo DatePicker, gán giá trị khởi tạo (nếu có)
        DatePicker datePicker = new DatePicker();
        if (initialDate != null) {
            datePicker.setValue(initialDate);
        }

        // 3. Tạo nút xác nhận "Chọn"
        Button confirmButton = new Button("Chọn");
        confirmButton.setDefaultButton(true);
        // Khi bấm, lấy giá trị datePicker.getValue() rồi thực thi callback, và ẩn pane
        confirmButton.setOnAction(e -> {
            LocalDate selected = datePicker.getValue();
            if (onDateConfirmed != null) {
                onDateConfirmed.accept(selected);
            }
            // Ẩn luôn pane chứa DatePicker
            container.setVisible(false);
            container.setManaged(false);
            // xoa luon pane chua datePicker
            if (container.getParent() instanceof VBox parentVBox) { // Kiểm tra nếu container là con của VBox
                parentVBox.getChildren().remove(container);
            }

        });

        // 4. Cho DatePicker chiếm toàn bộ chiều ngang của container (nếu cần)
        datePicker.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(datePicker, Priority.ALWAYS);

        // 5. Thêm DatePicker và nút vào VBox
        container.getChildren().addAll(datePicker, confirmButton);

        // 6. Ban đầu ẩn pane (người gọi có thể override lại nếu muốn cho nó hiện ngay)
        container.setVisible(false);
        container.setManaged(false);

        return container;
    }

    public static VBox createMemberOfTaskList() {
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

        // Add some sample members
        HBox member1 = members("Alice", true);
        HBox member2 = members("Bob", false);
        HBox member3 = members("Charlie", true);
        HBox member4 = members("David", false);
        HBox member5 = members("Eve", true);
        HBox member6 = members("Frank", false);
        HBox member7 = members("Grace", true);
        HBox member8 = members("Hannah", false);
        HBox member9 = members("Ivy", true);
        HBox member10 = members("Jack", false);
        VBox memberList = new VBox();
        memberList.getChildren().addAll(member1, member2, member3, member4, member5,
                member6, member7, member8, member9, member10);

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

    public static HBox members(String memberName, Boolean isMember) {
        // Create a HBox to hold the member information
        HBox memberBox = new HBox();
        memberBox.setSpacing(5);
        memberBox.setPadding(new Insets(0, 0, 0, 10));
        // memberBox.setPrefHeight(24.7);
        // Content
        Image image = new Image("file:src/main/resources/image/ImageAvatar.png");
        AvatarViewClass avatar = new AvatarViewClass(image, 24.7, 0);
        Label nameLabel = new Label(memberName);
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
                // Xử lý xóa thành viên ở đây
            });
            memberBox.getChildren().add(removeButton);
        } else {
            Image addImage = new Image(
                    "file:src/main/resources/image/Add_Icon.png");
            ImageButtonClass addButton = new ImageButtonClass(addImage, 24.7, 24.7, 0, 0);
            addButton.setOnAction(e -> {
                // Xử lý thêm thành viên ở đây
            });
            memberBox.getChildren().add(addButton);
        }
        memberBox.setFillHeight(false);
        return memberBox;
    }
}
