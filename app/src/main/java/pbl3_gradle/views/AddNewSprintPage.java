package pbl3_gradle.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.CurrentProject;
import pbl3_gradle.models.Sprint;
import pbl3_gradle.models.SprintList;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.CustomMessageBox;
import pbl3_gradle.util.NavigationManager;

public class AddNewSprintPage {
        public Pane getView() {
                // Create the menu bar
                Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Add new sprint", "AddNewSprintPage");
                // Create the main label
                Label mainLabel = new Label("ADD NEW SPRINT");
                mainLabel.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
                mainLabel.setPrefSize(302.2, 44.6);
                mainLabel.setLayoutX(683);
                mainLabel.setLayoutY(22.6);
                // Create the back button
                Image image1 = new Image(
                                "file:src/main/resources/image/Back_Icon.png");
                ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                backButton.setOnAction(e -> {
                        if (AppContext.get("lastPage") == "SprintListPage") {
                                AppContext.set("currentPage", "SprintListPage");
                                NavigationManager.navigateToSprintListPage();
                        } else if (AppContext.get("lastPage") == "CurrentSprintPage") {
                                AppContext.set("currentPage", "CurrentSprintPage");
                                NavigationManager.navigateToCurrentSprintPage();
                        }
                });
                // Crate redirect for informtion of the page
                RoundedRect rect1 = new RoundedRect(341.7, 110.7, 979.2, 622.3, "transparent", "#92badd", 2, 36);
                // Tao cac label nhap
                List<Label> labels = List.of(new Label("Choose the start time:"),
                                new Label("Choose the end time:"), new Label("Enter the name of sprint:"));
                for (Label label : labels) {
                        label.setStyle(
                                        "-fx-text-fill: #2f74eb; -fx-font-size: 22px; -fx-alignment: left; -fx-font-family: 'Helvetica'");
                        label.setPrefSize(359, 35.1);
                }
                labels.get(0).setLayoutX(397.6);
                labels.get(0).setLayoutY(167.1);
                labels.get(1).setLayoutX(397.6);
                labels.get(1).setLayoutY(245.2);
                labels.get(2).setLayoutX(397.6);
                labels.get(2).setLayoutY(323.4);
                // Tao hop chon thoi gian
                DatePicker startDatePicker = new DatePicker();
                startDatePicker.setValue(LocalDate.now());
                startDatePicker.setLayoutX(786.7);
                startDatePicker.setLayoutY(157.5);
                DatePicker endDatePicker = new DatePicker();
                endDatePicker.setValue(LocalDate.now().plusDays(14)); // Default to 14 days later
                endDatePicker.setLayoutX(786.7);
                endDatePicker.setLayoutY(235.6);
                List<DatePicker> datePickers = List.of(startDatePicker, endDatePicker);
                for (DatePicker datePicker : datePickers) {
                        datePicker.setPrefSize(495.8, 54.5);
                        datePicker.setStyle("-fx-background-color: #c4dff8;"
                                        + "-fx-border-radius: 36px;"
                                        + "-fx-background-radius: 36px;"
                                        + "-fx-padding: 0 0 0 30;");
                        datePicker.getEditor().setStyle(
                                        "-fx-text-fill: #2f74eb; "
                                                        + "-fx-font-family: Helvetica; "
                                                        + "-fx-font-size: 22px; "
                                                        + "-fx-background-color:  #c4dff8;;");
                }
                // Tao textField nhap ten
                TextArea nameSprint = new TextArea();
                nameSprint.setStyle(
                                "-fx-control-inner-background: #c4dff8;" + // nền bên trong vùng gõ
                                                "-fx-background-color:#c4dff8 ;" + // nền vùng ngoài (ẩn)
                                                "-fx-font-family: 'Helvetica';" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-text-fill: #2f74eb;" + // màu chữ chính
                                                "-fx-padding: 12 16 12 16;" + // padding đều
                                                "-fx-background-radius: 36px;" + // bo nhẹ góc
                                                "-fx-border-radius: 36px;" +
                                                "-fx-border-color: transparent;");
                nameSprint.setPrefSize(884.9, 243.5);
                nameSprint.setPrefRowCount(5);
                nameSprint.setLayoutX(404.3);
                nameSprint.setLayoutY(382.5);
                nameSprint.setWrapText(true);
                nameSprint.setPromptText("Enter the name of the sprint here...");
                // Tao button Create
                FancyButtonClass createButton = new FancyButtonClass("Create New Sprint", 295.4, 59.8, 491.2, 642.3);
                createButton.setOnAction(e -> {
                        String name = nameSprint.getText().trim();
                        LocalDate startDate = startDatePicker.getValue();
                        LocalDate endDate = endDatePicker.getValue();
                     Sprint sprint =  new Sprint();
                        sprint.setTitle(name);
                        sprint.setStartDate(java.sql.Date.valueOf(startDate));
                        sprint.setEstimatedEndDate(java.sql.Date.valueOf(endDate));
                        sprint.setStatus(false);
                        Random random = new Random();
                        int randomNumber = random.nextInt(100) + 1; // từ 1 đến 100
                        sprint.setIdSprint(randomNumber);
                        sprint.setIdProject(CurrentProject.Instance.getIdProject());
                        SprintList sprintList = new SprintList();
                        DataManager.Instance.addNewSprintToSprintList(sprintList, sprint);
                        NavigationManager.navigateToCurrentSprintPage();
                });
                // Tao button Clear
                FancyButtonClass clearButton = new FancyButtonClass("Clear", 295.4, 59.8, 886.9, 642.3);
                // Create the main pane
                Pane mainPane = new Pane();
                mainPane.getChildren().addAll(menuBar, mainLabel, backButton, rect1);
                for (Label label : labels) {
                        mainPane.getChildren().add(label);
                }
                for (DatePicker datePicker : datePickers) {
                        mainPane.getChildren().add(datePicker);
                }
                mainPane.getChildren().addAll(nameSprint, createButton, clearButton);
                mainPane.setStyle("-fx-background-color: #ffffff; -fx-background-size: cover;");
                mainPane.setPrefSize(1366, 768);
                return mainPane;
        }
}
