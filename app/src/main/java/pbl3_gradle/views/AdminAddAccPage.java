package pbl3_gradle.views;

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.MenuBarClass;
import pbl3_gradle.common.PasswordTextFieldClass;
import pbl3_gradle.common.RoundedRect;
import javafx.scene.control.TextField;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;

public class AdminAddAccPage {
        public Pane getView() {
                // Tao menu bar
                Pane menuBar = MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png",
                                "Administrator", "AdminAddAccPage");
                // Tao main label
                Label mainLb = new Label("ADD ACCOUNT");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26 px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(257.2, 44.6);
                mainLb.setLayoutX(698.9);
                mainLb.setLayoutY(33.3);
                // Tao hinh chu nhat
                RoundedRect rect1 = new RoundedRect(385.3, 94.9, 916.3, 588.9, "#ffffff", "#92badd", 2, 36);
                // Tao group 4 label
                Label lb1 = new Label("Username: ");
                Label lb2 = new Label("Password: ");
                Label lb3 = new Label("Enter-Password: ");
                Label lb4 = new Label("Role: ");
                LabelStyle(lb1, "#2f74eb", "left", 250.0, 37.4);
                LabelStyle(lb2, "#2f74eb", "left", 250.0, 37.4);
                LabelStyle(lb3, "#2f74eb", "left", 250.0, 37.4);
                LabelStyle(lb4, "#2f74eb", "left", 250.0, 37.4);

                VBox vbox = new VBox();
                vbox.getChildren().addAll(lb1, lb2, lb3, lb4);
                vbox.setSpacing(44);
                vbox.setLayoutX(420.2);
                vbox.setLayoutY(178.8);
                vbox.setPrefSize(250, 281.5);
                // Tao group 3 textfield
                TextField tf1 = new TextField();
                PasswordTextFieldClass tf2 = new PasswordTextFieldClass();
                PasswordTextFieldClass tf3 = new PasswordTextFieldClass();
                ComboBox<String> cbb = new ComboBox<>();
                cbb.getItems().addAll("Scrum Master", "Developer", "Product Owner", "Project Owner");

                tf1.setPromptText("Enter username");
                TextFieldStyle(tf1, "#2f74eb", 577.1, 54.5);
                PwTextFieldStyle(tf2, "#2f74eb", 577.1, 54.5);
                PwTextFieldStyle(tf3, "#2f74eb", 577.1, 54.5);
                comboBxStyle(cbb, "#2f74eb", 577.1, 54.5);
                // choiceBox.setPromptText("Select role");
                VBox vbox1 = new VBox();
                vbox1.getChildren().addAll(tf1, tf2, tf3, cbb);
                vbox1.setSpacing(25);
                vbox1.setLayoutX(670.2);
                vbox1.setLayoutY(175.9);
                vbox1.setPrefSize(577.1, 292.9);
                // Tao button
                FancyButtonClass btnCreate = new FancyButtonClass("Create", 213.1, 59.8, 614.4, 556.5);
                FancyButtonClass btnClear = new FancyButtonClass("Clear", 213.1, 59.8, 881.4, 556.5);
                // Tao pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, rect1, btnClear, btnCreate);
                pane.getChildren().addAll(vbox, vbox1);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;

        }

        public static void LabelStyle(Label label, String collor, String aligment, Double width, Double height) {
                label.setPrefSize(width, height);
                label.setStyle(
                                "-fx-text-fill: " + collor + ";"
                                                + " -fx-font-size: 23 px;"
                                                + " -fx-alignment: " + aligment + "; "
                                                + "-fx-font-family:'Helvetica';");
        }

        public static void TextFieldStyle(TextField textField, String collor, Double width,
                        Double height) {
                textField.setPrefSize(width, height);
                textField.setStyle(
                                "-fx-text-fill: " + collor + ";"
                                                + " -fx-background-color: #c4dff8;"
                                                + " -fx-font-size: 16 px;"
                                                + " -fx-alignment:  center-left; "
                                                + "-fx-font-family:'Helvetica';"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;"
                                                + "-fx-padding: 0 0 0 30;");
        }

        public static void PwTextFieldStyle(PasswordTextFieldClass textField, String collor, Double width,
                        Double height) {
                textField.setPrefSize(width, height);
                textField.setStyle(
                                "-fx-text-fill: " + collor + ";"
                                                + " -fx-background-color: #c4dff8;"
                                                + " -fx-font-size: 16 px;"
                                                + " -fx-alignment:  center-left; "
                                                + "-fx-font-family:'Helvetica';"
                                                + "-fx-border-radius: 36px;"
                                                + "-fx-background-radius: 36px;"
                                                + "-fx-padding: 0 0 0 30;");
        }

        public static void comboBxStyle(ComboBox<String> cbb, String color, double width, double height) {
                // Kích thước
                cbb.setPrefSize(width, height);
                cbb.setPromptText("Select role");
                // Style cho chính cbb (nền, border, font chung)
                cbb.setStyle(
                                "-fx-background-color: #c4dff8;" +
                                                "-fx-border-radius: 36px;" +
                                                "-fx-background-radius: 36px;" +
                                                "-fx-font-family: 'Helvetica';");

                // Cursor khi hover
                cbb.setCursor(Cursor.HAND);

                // 1. Style cho phần hiển thị giá trị đã chọn (button cell)
                cbb.setButtonCell(new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                        setText(cbb.getPromptText());
                                        // Ép màu xám cho prompt
                                        setStyle("-fx-text-fill: #BABCC1;"
                                                        + "-fx-font-size: 16px;"
                                                        + "-fx-font-family: 'Helvetica';"
                                                        + "-fx-aligment: center-left;"
                                                        + "-fx-padding: 0 0 0 30;");

                                } else {
                                        // Khi có giá trị → show text với màu bạn truyền vào
                                        setText(item);
                                        setStyle(
                                                        "-fx-text-fill: " + color + ";" +
                                                                        "-fx-font-size: 16px;" +
                                                                        "-fx-font-family: 'Helvetica';" +
                                                                        "-fx-aligment: center-left;" +
                                                                        "-fx-padding: 0 0 0 30;");
                                }
                        }
                });

                // 2. Style cho các mục trong dropdown list
                cbb.setCellFactory(listView -> new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                        setText(null);
                                        setStyle("");
                                } else {
                                        setText(item);
                                        setStyle(
                                                        "-fx-text-fill: " + color + ";" +
                                                                        "-fx-font-size: 16px;" +
                                                                        "-fx-font-family: 'Helvetica';");
                                }
                        }
                });

        }

        public static Pane MenuBarStyle_Layer1(String image, String text, String page) {
                // Tạo hình chữ nhật cho menu
                RoundedRect rect = new RoundedRect();
                // Tạo hình avatar
                Image imageA = new Image(image);

                AvatarViewClass avatar = new AvatarViewClass(imageA, 162.3, 2);
                avatar.setLayoutX(67.3);
                avatar.setLayoutY(38.3);
                // Tao Label cho avatar
                Label avarLb = new Label(text);
                avarLb.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 20 px; -fx-alignment: center; -fx-font-family: :'Helvetica';");
                avarLb.setPrefSize(195.1, 31.7);
                avarLb.setLayoutX(50.5);
                avarLb.setLayoutY(218.7);
                // Tao MenuBar
                MenuBarClass menuBar = new MenuBarClass(1, page);
                menuBar.setLayoutX(15.5);
                menuBar.setLayoutY(347.7);
                Pane pane = new Pane();
                pane.getChildren().addAll(rect, avatar, avarLb, menuBar);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(296.1, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

}
