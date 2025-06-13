package pbl3_gradle.views;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.common.ImageButtonClass;
import pbl3_gradle.controllers.Account;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.controllers.Validator;
import pbl3_gradle.models.User;
import pbl3_gradle.util.AppContext;
import javafx.geometry.Insets;
import pbl3_gradle.models.UserClass;
import pbl3_gradle.util.NavigationManager;

import javax.swing.*;

public class EditAcc_EditingPage {

        public Pane getView() {
                // Lay du lieu
                User userFromView = (User) AppContext.get("userSelected");
                User user = DataManager.Instance.getUserInfoByID(userFromView.getUserID());
                String oldUsername = user.getUserName();
                System.out.println(user.getUserID() + " " + user.getUserName() + " " + user.getRole());
                System.out.flush();
                // Tap menu Bar
                Pane menuBar = AdminAddAccPage.MenuBarStyle_Layer1(
                                "file:src/main/resources/image/ImageAvatar.png", "Administrator",
                                "EditAcc_EditingPage");

                // Tao main label
                Label mainLb = new Label("EDIT ACCOUNT");
                mainLb.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 26px;"
                                                + " -fx-alignment: center; "
                                                + "-fx-font-family:'Arial';"
                                                + "-fx-font-weight: bold;");
                mainLb.setPrefSize(290.2, 41.1);
                mainLb.setLayoutX(361.8);
                mainLb.setLayoutY(26.2);
                // Tao button back
                Image image1 = new Image(
                                "file:src/main/resources/image/Back_Icon.png");
                ImageButtonClass backButton = new ImageButtonClass(image1, 53.4, 53.4, 308.4, 10.4);
                backButton.setOnAction(e -> {
                        AppContext.remove("currentPage");
                        AppContext.remove("userSelected");
                        NavigationManager.navigateToEditAccShowAccPage();
                });
                // Tao khung hinh chu nhat
                RoundedRect rect1 = new RoundedRect(345.7, 118.1, 967.1, 599, "transparent", "#92badd", 2, 36);
                // Tao info avatar
                HBox ava = new HBox();
                ava.setStyle("-fx-background-color: #c4dff8;"
                                + "-fx-border-color: #2f74eb;"
                                + "-fx-border-radius: 36px;"
                                + "-fx-background-radius: 36px;"
                                + "-fx-border-width: 2px;");
                ava.setPrefSize(312, 82.6);
                ava.setLayoutX(673.2);
                ava.setLayoutY(76.8);
                Image image = new Image("file:src/main/resources/image/ImageAvatar.png");
                AvatarViewClass avatar1 = new AvatarViewClass(image, 62.4, 2);
                Label username = new Label(user.getUserName());
                username.setStyle(
                                "-fx-text-fill: #2f74eb;"
                                                + " -fx-font-size: 23px;"
                                                + " -fx-alignment: left; "
                                                + "-fx-font-family:'Helvetica';");
                ava.getChildren().addAll(avatar1, username);
                ava.setSpacing(5);
                avatar1.setPadding(new Insets(0, 20, 0, 45));
                ava.setAlignment(Pos.CENTER_LEFT);
                // Tao form edit
                // Tao group 4 label
                Label lb1 = new Label("Username: ");
                Label lb2 = new Label("Password: ");
                Label lb3 = new Label("Enter-Password: ");
                Label lb4 = new Label("Role: ");
                AdminAddAccPage.LabelStyle(lb1, "#2f74eb", "left", 276.1, 41.3);
                AdminAddAccPage.LabelStyle(lb2, "#2f74eb", "left", 276.1, 41.3);
                AdminAddAccPage.LabelStyle(lb3, "#2f74eb", "left", 276.1, 41.3);
                AdminAddAccPage.LabelStyle(lb4, "#2f74eb", "left", 276.1, 41.3);

                VBox vbox = new VBox();
                vbox.getChildren().addAll(lb1, lb2, lb3, lb4);
                vbox.setSpacing(44);
                vbox.setLayoutX(410);
                vbox.setLayoutY(214.3);
                vbox.setPrefSize(276.1, 310.8);
                // Tao group 3 textfield
                TextField tf1 = new TextField(oldUsername);
                PasswordField tf2 = new PasswordField();
                tf2.setPromptText("Enter new password");
                PasswordField tf3 = new PasswordField();
                tf3.setPromptText("Re-enter new password");
                ComboBox<String> cbb = new ComboBox<>();
                cbb.getItems().addAll( "Product Owner", "Scrum Master", "Development Team");
                String userRole = switch (user.getRole()) {
                        case 2 -> "Product Owner";
                        case 3 -> "Scrum Master";
                        case 4 -> "Development Team";
                        default -> "";
                };
                cbb.setValue(userRole);
                AdminAddAccPage.TextFieldStyle(tf1, "#2f74eb", 637.2, 60.1);
                AdminAddAccPage.PwTextFieldStyle(tf2, "#2f74eb", 637.2, 60.1);
                AdminAddAccPage.PwTextFieldStyle(tf3, "#2f74eb", 637.2, 60.1);
                AdminAddAccPage.comboBxStyle(cbb, "#2f74eb", 637.2, 60.1);
                VBox vbox1 = new VBox();
                vbox1.getChildren().addAll(tf1, tf2, tf3, cbb);
                vbox1.setSpacing(25);
                vbox1.setLayoutX(640);
                vbox1.setLayoutY(209.5);
                vbox1.setPrefSize(637.2, 315.8);
                // Tao button
                FancyButtonClass btnSave = new FancyButtonClass("Save", 213.1, 59.8, 566.6, 586.4);
                FancyButtonClass btnClear = new FancyButtonClass("Clear", 213.1, 59.8, 878.6, 586.4);
                btnSave.setOnAction(e -> {
                        String newUsername = tf1.getText();
                        String newPassword = tf2.getText();
                        String retypePassword = tf3.getText();
                        int newRole = cbb.getSelectionModel().getSelectedIndex() + 2; // hoặc getValue()

                        if (newUsername.isEmpty()) {
                                // báo lỗi
                                System.out.println("Tên đăng nhập không được để trống");
                                JOptionPane.showMessageDialog(null, "Tên đăng nhập không được để trống.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        // kiểm tra tên đăng nhập có hợp lệ không
                        // chỉ kiểm tra khi đổi tên đăng nhập
                        if (!oldUsername.equals(newUsername)) {
                                if (DataManager.Instance.verifyUsername(newUsername)) {
                                        System.out.println("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                                        JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                        }

                        // kiểm tra mật khẩu có hợp lệ không
                        if (!Validator.Instance.validatePasswordFormat(newPassword)) {
                                System.out.println("Mật khẩu không hợp lệ");
                                JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ. Vui lòng nhập lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        if (!newPassword.equals(retypePassword)) {
                                // báo lỗi
                                System.out.println("Passwords do not match");
                                JOptionPane.showMessageDialog(null, "Mật khẩu không khớp. Vui lòng nhập lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        user.setUserName(newUsername);
                        if (!newPassword.isEmpty()) {
                                newPassword = Account.hashPassword(newPassword);
                                user.setUserPassword(newPassword);
                        }
                        user.setRole(newRole);
                        DataManager.Instance.updateUserInfoByID(user);
                        AppContext.remove("userSelected");
                        NavigationManager.navigateToEditAccShowAccPage();
                });


                // Tao Pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, mainLb, backButton, rect1, ava, vbox, vbox1,
                                btnClear,
                                btnSave);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

}
