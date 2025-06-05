package pbl3_gradle.views;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.controllers.ProjectController;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.CurrentUser;
import pbl3_gradle.models.Project;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.CustomMessageBox;
import pbl3_gradle.util.NavigationManager;

import java.util.List;

public class CurrentProjectPage {

        public Pane getView() {
                Pane root = new Pane();
                root.setPrefSize(1366, 768);
                root.setStyle("-fx-background-color: #ffffff;");

                Pane menuBar = createMenuBar();
                Label titleLabel = createTitleLabel();
                ImageButtonClass moreButton = createMoreButton();
                TextField searchField = createSearchField();
                AvatarViewClass searchIcon = createSearchIcon();

                GridPane projectsGrid = createProjectsGrid();
                ScrollPane scrollPane = createScrollPane(projectsGrid);

                root.getChildren().addAll(menuBar, titleLabel, moreButton, searchField, searchIcon, scrollPane);
                return root;
        }

        private Pane createMenuBar() {
                return ProfileMemberPage.MenuBarStyle_Layer2(
                        "file:src/main/resources/image/ImageAvatar.png",
                        "Nguyễn Thị Huyền Trang",
                        "Scrum Master",
                        "CurrentProjectPage");
        }

        private Label createTitleLabel() {
                Label label = new Label("CURRENT PROJECTS");
                label.setLayoutX(678.6);
                label.setLayoutY(43.3);
                label.setPrefSize(290.2, 41.1);
                label.setStyle("""
                -fx-text-fill: #2f74eb;
                -fx-font-size: 26px;
                -fx-alignment: center;
                -fx-font-family: 'Arial';
                -fx-font-weight: bold;
                """);
                return label;
        }

        private ImageButtonClass createMoreButton() {
                Image moreIcon = new Image("file:src/main/resources/image/MoreIcon.png");
                ImageButtonClass moreButton = new ImageButtonClass(moreIcon, 44.4, 44.4, 1244.8, 41.2);

                ContextMenu contextMenu = new ContextMenu();
                MenuItem completedItem = new MenuItem("Completed Projects");
                completedItem.setStyle("-fx-font-size: 20px; -fx-font-family:'Helvetica';");
                contextMenu.getItems().add(completedItem);

                completedItem.setOnAction(e -> {
                        AppContext.set("currentPage", "CompeletedProjectPage");
                        NavigationManager.navigateToCompeletedProjectPage();
                });
              if(CurrentUser.Instance.getRole()==2) {
                      MenuItem newProject = new MenuItem("New Project");
                      newProject.setStyle("-fx-font-size: 20px; -fx-font-family:'Helvetica';");
                      contextMenu.getItems().add(newProject);
              }
                moreButton.setOnAction(e -> {
                        if (!contextMenu.isShowing()) {
                                contextMenu.show(moreButton, Side.BOTTOM, 0, 0);
                        } else {
                                contextMenu.hide();
                        }
                });

                return moreButton;
        }

        private TextField createSearchField() {
                TextField searchField = new TextField();
                EditAcc_ShowAccPage.setStyleFindText(searchField, 981.8, 65.9, 338.1, 124.7);
                return searchField;
        }

        private AvatarViewClass createSearchIcon() {
                Image findImage = new Image("file:src/main/resources/image/FindImage.png");
                AvatarViewClass icon = new AvatarViewClass(findImage, 46.8, 0);
                icon.setLayoutX(379.7);
                icon.setLayoutY(134.3);
                return icon;
        }

        private GridPane createProjectsGrid() {
                GridPane grid = new GridPane();
                grid.setHgap(24);
                grid.setVgap(24);
                grid.setPadding(new Insets(5));

                // Sample Projects

                List<Project> allProjects = DataManager.Instance.getAllProject();
                Button[] projects = new Button[allProjects.size()];
                for (int i = 0; i < allProjects.size() ; i++) {
                        Project project = allProjects.get(i);
                        projects[i] = createProjectButton(project.getProjectName(), project.getDescription());
                }

                for (int i = 0; i < projects.length; i++) {
                        grid.add(projects[i], i % 2, i / 2);
                }

                return grid;
        }

        private ScrollPane createScrollPane(GridPane content) {
                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setPrefSize(1050, 530);
                scrollPane.setLayoutX(310);
                scrollPane.setLayoutY(220);
                scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                return scrollPane;
        }

        public static Button createProjectButton(String title, String description) {
                Pane projectPane = new Pane();
                projectPane.setPrefSize(469.9, 166);

                Label titleLabel = new Label(title);
                titleLabel.setId("projectTitle");
                titleLabel.setLayoutX(37.1);
                titleLabel.setLayoutY(10.3);
                titleLabel.setPrefSize(361.4, 34.7);
                titleLabel.setStyle("""
                -fx-text-fill: #2f74eb;
                -fx-font-size: 20px;
                -fx-font-family:'Helvetica';
                -fx-font-weight: bold;
                """);

                Label descLabel = new Label(description);
                descLabel.setLayoutX(10.5);
                descLabel.setLayoutY(59.4);
                descLabel.setPrefSize(448.8, 82.7);
                descLabel.setWrapText(true);
                descLabel.setStyle("""
                -fx-text-fill: #2f74eb;
                -fx-font-size: 15px;
                -fx-font-family:'Helvetica';
                """);

                Image moreImg = new Image("file:src/main/resources/image/MoreIcon2.png");
                ImageButtonClass moreBtn = new ImageButtonClass(moreImg, 36.2, 36.2, 412.8, 9.4);

                projectPane.getChildren().addAll(titleLabel, descLabel, moreBtn);

                Button projectButton = new Button();
                projectButton.setGraphic(projectPane);
                projectButton.setStyle("""
                -fx-background-color: #c4dff8;
                -fx-border-color: #92badd;
                -fx-border-width: 2px;
                -fx-border-radius: 36px;
                -fx-background-radius: 36px;
                -fx-cursor: hand;
                """);

                applyContextMenu(projectButton, moreBtn);
                applyClickEffect(projectButton);

                projectButton.setOnMouseClicked(e -> {
                        AppContext.set("currentPage", "ProductBacklogPage");
                        NavigationManager.navigateToProductBacklogPage();
                });

                return projectButton;
        }

        private static void applyContextMenu(Button button, ImageButtonClass moreBtn) {
                ContextMenu menu = new ContextMenu();
                MenuItem doneItem = new MenuItem("Done");
                MenuItem deleteItem = new MenuItem("Delete");
                MenuItem redoItem = new MenuItem("Redo");

                doneItem.setStyle("-fx-font-size: 14px; -fx-font-family:'Helvetica';");
                deleteItem.setStyle("-fx-font-size: 14px; -fx-font-family:'Helvetica';");
                redoItem.setStyle("-fx-font-size: 14px; -fx-font-family:'Helvetica';");

                Project prj= new Project();
                Node graphic = button.getGraphic(); // Đây là projectPane
                if (graphic instanceof Pane projectPane) {
                        Label titleLabel = (Label) projectPane.lookup("#projectTitle");
                        if (titleLabel != null) {
                                //in ra
                                System.out.println("Project Title: " + titleLabel.getText());
                              prj= DataManager.Instance.getProjectByName(titleLabel.getText());
                        }
                }
                 int idProject = prj.getIdProject();
                System.out.println("Project ID: " + idProject);

                String currentPage = (String) AppContext.get("currentPage");

                if ("CurrentProjectPage".equals(currentPage)) {
                        menu.getItems().addAll(doneItem, deleteItem);

                        doneItem.setOnAction(e -> {
                                ProjectController.Instance.markDoneProject(idProject);  // Gọi hàm đánh dấu project là đã hoàn thành
                                CustomMessageBox.show("Success!", "Done project!");
                        });
                        deleteItem.setOnAction(e -> {
                                ProjectController.Instance.removeProject(idProject);
                                CustomMessageBox.show("Success!", "Deleted project!"); // Thông báo thành công
                        });

                } else {
                        menu.getItems().addAll(redoItem, deleteItem);
                        redoItem.setOnAction(e -> CustomMessageBox.show("Success!", "Redo project!"));
                }

                moreBtn.setOnMouseClicked(e -> {
                        if (!menu.isShowing()) {
                                menu.show(moreBtn, Side.BOTTOM, 0, 0);
                        } else {
                                menu.hide();
                        }
                });
        }

        public static void applyClickEffect(Button button) {
                button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> animateScale(button, 0.95));
                button.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> animateScale(button, 1.00));
        }

        private static void animateScale(Button button, double scale) {
                ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
                st.setToX(scale);
                st.setToY(scale);
                st.play();
        }
}
