package pbl3_gradle.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
// import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.Board;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.Task;
import pbl3_gradle.models.TaskList;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class KanbanBoardPage {
        public Pane getView() {
                // Create MenuBar
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Kanban Board", "KanbanBoardPage");

                // Create the main label
                Label mainLabel = new Label("KANBAN BOARD");
                mainLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(216.9, 44.6);
                mainLabel.setLayoutX(339.8);
                mainLabel.setLayoutY(25.8);

                // HBox chứa các task list
                HBox taskListsContainer = new HBox(20);
                setupVBoxDropTarget(taskListsContainer);
                taskListsContainer.setPadding(new Insets(10, 10, 0, 10));
                taskListsContainer.setFillHeight(false);

                // Lấy danh sách TaskList
                List<TaskList> taskList = DataManager.Instance.getTaskListByBoardId(
                                DataManager.Instance.getBoardIdByProject(CurrentProject.Instance.getIdProject()));

                for (TaskList taskListItem : taskList) {
                        VBox tl = listTasks(taskListItem, taskListsContainer);
                        taskListsContainer.getChildren().add(tl);
                }

                // Nút thêm Task List mới
                FancyButtonClass addNewTaskListButton = new FancyButtonClass("Add New Task List", 345.7, 69.1, 0, 0);
                addNewTaskListButton.setOnAction(e -> {
                        TaskList newtl = new TaskList();
                        newtl.setName("New Task List");
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(100) + 1;
                        newtl.setIdTaskList(randomNumber);
                        DataManager.Instance.createTaskList(newtl, DataManager.Instance
                                        .getBoardIdByProject(CurrentProject.Instance.getIdProject()));
                        VBox newTaskList = listTasks(newtl, taskListsContainer);
                        taskListsContainer.getChildren().remove(addNewTaskListButton);
                        taskListsContainer.getChildren().add(newTaskList);
                        taskListsContainer.getChildren().add(addNewTaskListButton);
                });
                taskListsContainer.getChildren().add(addNewTaskListButton);

                // ScrollPane chứa taskListsContainer
                ScrollPane scrollPane = new ScrollPane(taskListsContainer);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; "
                                + "-fx-border-color: transparent; -fx-border-width: 0px;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefSize(1000, 1000);
                scrollPane.setLayoutX(330.8);
                scrollPane.setLayoutY(70);

                // Main pane
                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, scrollPane);
                mainPane.setPrefSize(1366, 768);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");

                return mainPane;
        }

        public static Pane taskPane(Task task) {
                Pane taskPane = new Pane();
                taskPane.setPrefSize(305.6, 118.4);
                if (task.getStatus()) {
                        taskPane.setStyle(
                                        "-fx-background-color: #c4dff8;"
                                                        + "-fx-border-radius: 10px;"
                                                        + "-fx-background-radius: 10px;"
                                                        + "-fx-border-color: #92badd;"
                                                        + "-fx-border-width: 2px;"
                                                        + "-fx-cursor: hand;");
                } else
                        // Neu task chua hoan thanh
                        taskPane.setStyle(
                                        "-fx-background-color: #c4dff8;"
                                                        + "-fx-border-radius: 10px;"
                                                        + "-fx-background-radius: 10px;"
                                                        + "-fx-cursor: hand;");
                // Tao label ten cho task
                Label taskLabel = new Label(task.getTitle());
                taskLabel.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + "-fx-font-size: 18px;"
                                                + "-fx-alignment: TOP_LEFT;"
                                                + "-fx-font-family: 'Helvetica';");
                taskLabel.setWrapText(true);
                taskLabel.setPrefSize(244.1, 100.3);
                taskLabel.setLayoutX(48);
                taskLabel.setLayoutY(12);
                // Tao checkbox cho task
                CheckBox checkBox = new CheckBox(null);
                checkBox.setPrefSize(50, 70);
                checkBox.setStyle("-fx-mark-color: #2f74eb;" + // Màu của dấu check ✓
                                "-fx-box-border: #2f74eb;" + // Màu viền hộp
                                "-fx-background-color: transparent;");
                checkBox.setPrefSize(30.1, 30.1);
                // checkBox.setScaleX(1.1); // Phóng to theo chiều ngang
                // checkBox.setScaleY(1.1); // Phóng to theo chiều dọc
                checkBox.setLayoutX(10);
                checkBox.setLayoutY(7);
                checkBox.setSelected(task.getStatus());
                checkBox.setOnAction(event -> {
                        // Xu ly su kien
                        if (checkBox.isSelected()) {
                                taskPane.setStyle(
                                                "-fx-background-color: #c4dff8;"
                                                                + "-fx-border-radius: 10px;"
                                                                + "-fx-background-radius: 10px;"
                                                                + "-fx-border-color: #92badd;"
                                                                + "-fx-border-width: 2px;"
                                                                + "-fx-cursor: hand;");
                        } else {
                                taskPane.setStyle(
                                                "-fx-background-color: #c4dff8;"
                                                                + "-fx-border-radius: 10px;"
                                                                + "-fx-background-radius: 10px;"
                                                                + "-fx-cursor: hand;");
                        }
                        // Xu ly thay doi trang thai cua task ve controller o day he Phat
                        task.setStatus(checkBox.isSelected());
                        DataManager.Instance.updateTask(task); // Cập nhật trạng thái task vào DB
                });
                taskPane.getChildren().addAll(checkBox, taskLabel);
                setupDraggableTaskPane(taskPane); // Thiết lập drag target cho task pane
                applyClickEffect(taskPane);
                taskPane.setOnMouseClicked(event -> {
                        AppContext.set("currentPage", "TaskDetailPage");
                        NavigationManager.navigateToDetailTaskPage(task);
                });
                taskPane.setUserData(task);
                return taskPane;
        }

        public static VBox listTasks(TaskList taskList, HBox taskListContainer) {
                // Label hiển thị tên task list
                Label listLabel = new Label(taskList.getName());
                listLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-alignment: left; -fx-font-family: 'Helvetica';");
                listLabel.setPrefSize(235.5, 32.5);
                listLabel.setLayoutX(10);
                listLabel.setLayoutY(5);

                // TextField để sửa tên
                TextField textField = new TextField(listLabel.getText());
                textField.setPrefSize(235.5, 32.5);
                textField.setLayoutX(10);
                textField.setLayoutY(5);
                textField.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 16px; -fx-alignment: left; -fx-font-family: 'Helvetica';");
                textField.setVisible(false);

                listLabel.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                                textField.setText(listLabel.getText());
                                listLabel.setVisible(false);
                                textField.setVisible(true);
                                textField.requestFocus();
                                textField.selectAll();
                        }
                });

                textField.setOnAction(e -> {
                        listLabel.setText(textField.getText());
                        taskList.setName(textField.getText());
                        DataManager.Instance.updateTaskList(taskList); // Cập nhật tên task list vào DB
                        textField.setVisible(false);
                        listLabel.setVisible(true);
                });

                textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                        if (!isNowFocused) {
                                listLabel.setText(textField.getText());
                                textField.setVisible(false);
                                listLabel.setVisible(true);
                        }
                });

                // Button more (context menu)
                Image image = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreButton = new ImageButtonClass(image, 36.2, 32.5, 250, 0);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Delete all tasks");
                MenuItem menuItem2 = new MenuItem("Delete this task list");
                menuItem1.setStyle("-fx-font-size: 14px; -fx-alignment: center; -fx-font-family:'Helvetica';");
                contextMenu.getItems().addAll(menuItem1, menuItem2);

                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0);
                        } else {
                                contextMenu.hide();
                        }
                });

                Pane headerPane = new Pane();
                headerPane.getChildren().addAll(listLabel, textField, moreButton);
                headerPane.setPrefSize(286.2, 32.4);

                // Lấy danh sách Task từ DB
                List<Task> tasks = DataManager.Instance.getTaskByTaskListId(taskList.getIdTaskList());

                // Tạo VBox chứa các Task
                VBox taskPanes = new VBox(10);
                taskPanes.setUserData(taskList);
                taskPanes.setMinHeight(20);
                taskPanes.setMinWidth(305.6);

                for (Task task : tasks) {
                        Pane taskPane = taskPane(task); // <-- Sử dụng thông tin task thực sự
                        taskPane.setUserData(task); // <-- BẮT BUỘC PHẢI CÓ DÒNG NÀY
                        taskPanes.getChildren().add(taskPane);
                }

                setupTaskDropTarget(taskPanes);
                taskPanes.setPickOnBounds(true);

                // Xóa toàn bộ task
                menuItem1.setOnAction(e -> {
                        if (!taskPanes.getChildren().isEmpty()) {
                                DataManager.Instance.deleteTaskListData(taskList.getIdTaskList());
                                taskPanes.getChildren().clear();
                        }
                });

                // ScrollPane chứa danh sách task
                ScrollPane scrollPane = new ScrollPane(taskPanes);
                scrollPane.setPrefWidth(305.6);
                scrollPane.setMinHeight(30);
                scrollPane.setMaxHeight(550);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; "
                                + "-fx-border-color: transparent; -fx-border-width: 0px;");

                // Nút thêm Task mới
                FancyButtonClass addNewTaskButton = new FancyButtonClass("Add New Task", 305.6, 46.5, 0, 0);
                addNewTaskButton.setOnAction(e -> {
                        // Tạo một task mới và thêm vào danh sách
                        Task task = new Task();
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(100) + 1;
                        task.setIdTask(randomNumber);
                        task.setTitle("New Task");
                        task.setDescription(" ");
                        task.setStatus(false);
                        DataManager.Instance.createTask(task, taskList.getIdTaskList());
                        Pane newTaskPane = taskPane(task); // nếu bạn có hàm này
                        taskPanes.getChildren().add(newTaskPane);
                        setupTaskDropTarget(taskPanes);

                });

                // VBox chính chứa mọi thành phần
                VBox mainPane = new VBox();
                mainPane.getChildren().addAll(headerPane, scrollPane, addNewTaskButton);
                mainPane.setStyle("-fx-background-color: #ffffff;"
                                + "-fx-border-color: #92badd;"
                                + "-fx-border-radius: 36px;"
                                + "-fx-background-radius: 36px;"
                                + "-fx-border-width: 2px;"
                                + "-fx-cursor: hand;");
                mainPane.setPadding(new Insets(10, 10, 10, 15));
                mainPane.setSpacing(10);
                mainPane.setPrefWidth(345.7);
                mainPane.setMaxHeight(800);

                // Xóa task list
                menuItem2.setOnAction(e -> {
                        DataManager.Instance.deleteTaskList(taskList);
                        taskListContainer.getChildren().remove(mainPane);
                });

                setupDraggableVBox(mainPane); // thiết lập drag-n-drop
                return mainPane;
        }

        public static void applyClickEffect(Pane pane) {
                EventHandler<MouseEvent> clickPressedHandler = e -> animateScale(pane, 0.95);
                EventHandler<MouseEvent> clickReleasedHandler = e -> animateScale(pane, 1.00);
                EventHandler<MouseEvent> clickDraggedHandler = e -> animateScale(pane, 1.00);

                pane.addEventHandler(MouseEvent.MOUSE_PRESSED, clickPressedHandler);
                pane.addEventHandler(MouseEvent.MOUSE_RELEASED, clickReleasedHandler);
                pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, clickDraggedHandler);
        }

        private static void animateScale(Pane pane, double scale) {
                ScaleTransition st = new ScaleTransition(Duration.millis(150), pane);
                st.setToX(scale);
                st.setToY(scale);
                st.play();
        }

        // Note for Phat: Sau ni anh huong cho keo tha nen anh huong du lieu
        // Khong can hieu het, copy gui chatgpt hoi no xu ly du lieu o dau de no chi cho
        // a :>
        // Kéo thả và sắp xếp lại Pane trong VBox (các task trong một cột)
        private static void setupDraggableTaskPane(Pane taskPane) {
                taskPane.setOnDragDetected(event -> {
                        Dragboard db = taskPane.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(taskPane.getChildren().get(1).toString()); // chứa dữ liệu
                        db.setContent(content);
                        db.setDragView(taskPane.snapshot(null, null));
                        event.consume();
                });
                taskPane.setOnDragDone(event -> {
                        // Khôi phục lại opacity sau khi kết thúc drag
                        taskPane.setOpacity(1.0);
                        event.consume();
                });
        }

        private static void setupTaskDropTarget(VBox vbox) { // Thiết lập drag target cho task list
                vbox.setOnDragOver(event -> {
                        if (event.getGestureSource() instanceof Pane
                                        && ((Pane) event.getGestureSource()).getParent() instanceof VBox) {
                                event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                });

                vbox.setOnDragDropped(event -> {
                        boolean success = false;

                        if (event.getGestureSource() instanceof Pane) {
                                Pane dragged = (Pane) event.getGestureSource();
                                VBox source = (VBox) dragged.getParent();

                                // Bước 1: gỡ bỏ taskPane đang kéo ra khỏi VBox gốc
                                source.getChildren().remove(dragged);
                                // xóa khỏi DB

                                // Bước 2: dự phòng vị trí chèn là cuối cùng
                                ObservableList<Node> children = vbox.getChildren();
                                int insertIndex = children.size();

                                // Lấy toạ độ Y của con trỏ chuột (trong toạ độ Scene)
                                double cursorY = event.getSceneY();

                                // Bước 3: duyệt các taskPane hiện có để tìm vị trí chèn
                                for (int i = 0; i < children.size(); i++) {
                                        Node child = children.get(i);
                                        Bounds bounds = child.localToScene(child.getBoundsInLocal());

                                        // Tính trung điểm Y của child
                                        double centerY = bounds.getMinY() + bounds.getHeight() / 2.0;

                                        // Nếu trỏ chuột nằm phía trên trung điểm của child này,
                                        // tức là chúng ta cần chèn trước child đó
                                        if (cursorY < centerY) {
                                                insertIndex = i;
                                                break;
                                        }
                                }

                                // Bước 4: thêm dragged vào vị trí đã tính
                                children.add(insertIndex, dragged);
                                // Lấy Task từ taskPane
                                Task task = (Task) dragged.getUserData();
                                Task newTask = task;

                                TaskList newTaskList = (TaskList) vbox.getUserData();
                                DataManager.Instance.deleteTaskData(task.getIdTask());
                                DataManager.Instance.createTask(newTask, newTaskList.getIdTaskList());
                                //
                                success = true;

                        }

                        event.setDropCompleted(success);
                        event.consume();
                });
        }

        private static void setupDraggableVBox(VBox column) {
                column.setOnDragDetected(event -> {
                        Dragboard db = column.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(""); // bạn có thể dùng id hoặc index nếu cần
                        db.setContent(content);
                        event.consume();
                });

                column.setOnDragDone(event -> {
                        column.setOpacity(1.0);
                        event.consume();
                });
        }

        private static void setupVBoxDropTarget(HBox board) {
                board.setOnDragOver(event -> {
                        if (event.getGestureSource() instanceof VBox) {
                                event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                });

                board.setOnDragDropped(event -> {
                        boolean success = false;

                        if (event.getGestureSource() instanceof VBox) {
                                VBox dragged = (VBox) event.getGestureSource();
                                ObservableList<Node> children = board.getChildren();

                                // Bỏ VBox đang kéo khỏi HBox (nếu nó đã nằm ở đó trước đó)
                                children.remove(dragged);

                                // Tính vị trí chèn mặc định: sau hết (nếu không tìm được vị trí giữa các phần
                                // tử)
                                int insertIndex = children.size();

                                // Duyệt qua từng Node con (là các VBox khác) để tìm vị trí chèn
                                for (int i = 0; i < children.size(); i++) {
                                        Node node = children.get(i);
                                        Bounds bounds = node.getBoundsInParent();

                                        // Tính trục giữa X của node hiện tại
                                        double nodeCenterX = bounds.getMinX() + bounds.getWidth() / 2.0;

                                        // Nếu điểm thả (event.getX()) nhỏ hơn trung điểm, tức là thả vào bên trái nửa
                                        // đầu node hiện tại
                                        if (event.getX() < nodeCenterX) {
                                                insertIndex = i;
                                                break;
                                        }
                                }

                                // Thêm dragged vào vị trí đã tính được
                                children.add(insertIndex, dragged);
                                success = true;
                        }

                        event.setDropCompleted(success);
                        event.consume();
                });
        }

}
