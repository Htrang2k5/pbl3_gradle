package pbl3_gradle.views;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.PasswordTextFieldClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.controllers.Account;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.controllers.Validator;
import pbl3_gradle.models.CurrentUser;
import pbl3_gradle.models.User;

public class ChangePasswordPage {
        public Pane getView() {
                // Tao menu bar
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                                "ChangePasswordPage");

                // Tao main Label
                Label mainLb = new Label("CHANGE PASSWORD");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26 px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(362.7, 44.6);
                mainLb.setLayoutX(661.4);
                mainLb.setLayoutY(37.3);

                // Tao khung hinh chu nhat
                RoundedRect rect = new RoundedRect(349.4, 152.3, 966.5, 500.4, "transparent", "#92badd", 2, 36);
                // Tao form edit
                // Tao group 4 label
                Label lb1 = new Label("Current Password: ");
                Label lb2 = new Label("New Password: ");
                Label lb3 = new Label("Enter-Password: ");
                AdminAddAccPage.LabelStyle(lb1, "#2f74eb", "left", 328.4, 35.9);
                AdminAddAccPage.LabelStyle(lb2, "#2f74eb", "left", 328.4, 35.9);
                AdminAddAccPage.LabelStyle(lb3, "#2f74eb", "left", 328.4, 35.9);

                VBox vbox = new VBox();
                vbox.getChildren().addAll(lb1, lb2, lb3);
                vbox.setSpacing(62);
                vbox.setLayoutX(450);
                vbox.setLayoutY(236.1);
                vbox.setPrefSize(328.4, 234.9);
                // Tao group 3 textfield
                PasswordTextFieldClass tf1 = new PasswordTextFieldClass();
                PasswordTextFieldClass tf2 = new PasswordTextFieldClass();
                PasswordTextFieldClass tf3 = new PasswordTextFieldClass();
                AdminAddAccPage.TextFieldStyle(tf1, "#2f74eb", 495.8, 54.5);
                AdminAddAccPage.PwTextFieldStyle(tf2, "#2f74eb", 495.8, 54.5);
                AdminAddAccPage.PwTextFieldStyle(tf3, "#2f74eb", 495.8, 54.5);
                VBox vbox1 = new VBox();
                vbox1.getChildren().addAll(tf1, tf2, tf3);
                vbox1.setSpacing(44);
                vbox1.setLayoutX(740.0);
                vbox1.setLayoutY(226.8);
                vbox1.setPrefSize(495.8, 251.5);
                // Tao button save
                FancyButtonClass saveBtn = new FancyButtonClass("SAVE", 213.1, 59.8, 726, 553.1);
                saveBtn.setOnAction(e -> {
                        String currentPw = tf1.getPassword();
                        String newPw = tf2.getPassword();
                        String confirmPw = tf3.getPassword();

                        // Lấy username hiện tại
                        String currentUsername = CurrentUser.Instance.getUserName();
                        // in ra username lên màn hình
                        System.out.println("Current username: " + currentUsername);
                        // Hash password cũ để kiểm tra

                        String hashedCurrentPw = Account.hashPassword(currentPw);
                         System.out.println("Hashed current password: " + currentPw);
                        System.out.println("Hashed current password: " + hashedCurrentPw);
                        if (!DataManager.Instance.verifyLogin(currentUsername, hashedCurrentPw)) {
                                showAlert(Alert.AlertType.ERROR, "Current password is incorrect.");
                                return;
                        }

                        // Kiểm tra mật khẩu mới và xác nhận bằng Validator
                        if (!Validator.Instance.validatePassword(newPw, confirmPw)) {
                                showAlert(Alert.AlertType.ERROR, Validator.Instance.getValidatePasswordRes());
                                return;
                        }
User admin = DataManager.Instance.getUserInfoByID(CurrentUser.Instance.getUserID());
                        // Hash mật khẩu mới
                     admin.setUserPassword(Account.hashPassword(newPw));

                   DataManager.Instance.updateUserInfoByID(admin);

                });

                // Tao pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, rect, vbox, vbox1, saveBtn);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }
        private void showAlert(Alert.AlertType type, String message) {
                Alert alert = new Alert(type);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }

}
