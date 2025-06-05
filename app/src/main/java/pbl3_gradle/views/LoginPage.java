package pbl3_gradle.views;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.AvatarViewClass;
import pbl3_gradle.common.FancyButtonClass;
import pbl3_gradle.common.PasswordTextFieldClass;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.controllers.Account;
import pbl3_gradle.controllers.DataManager;
import pbl3_gradle.models.CurrentUser;
import pbl3_gradle.util.NavigationManager;
import javafx.scene.image.Image;

public class LoginPage {

        public Pane getView() {
                // Create a new TextField for username
                TextField username = new TextField();
                username.setPromptText("Enter username");
                AdminAddAccPage.TextFieldStyle(username, "#2f74eb;", 423.1, 59.8);
                username.setLayoutX(578);
                username.setLayoutY(299.7);
                // Create a new TextField for password
                PasswordTextFieldClass password = new PasswordTextFieldClass();
                AdminAddAccPage.PwTextFieldStyle(password, "#2f74eb;", 423.1, 59.8);
                password.setLayoutX(578);
                password.setLayoutY(384);
                // Create a new Label for username
                Label lb2 = new Label("Username:");
                lb2.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 23 px; ");
                lb2.setPrefSize(161.6, 35.9);
                lb2.setLayoutX(399.7);
                lb2.setLayoutY(311.6);
                // Create a new Label for password
                Label lb3 = new Label("Password:");
                lb3.setStyle(
                                "-fx-text-fill: #2f74eb; -fx-font-size: 23 px; ");
                lb3.setPrefSize(161.6, 35.9);
                lb3.setLayoutX(399.7);
                lb3.setLayoutY(396);
               // create new a login button
                FancyButtonClass loginButton = new FancyButtonClass("Log in", 213.1, 59.8, 576.4, 487.8);
                loginButton.setOnAction(e -> {
                        String user = username.getText().trim();
                        String pass = password.getPassword();

                        System.out.println("Username: " + user);
                        System.out.println("Password: " + pass);
                        System.out.println(Account.hashPassword(pass));
                        if (user.isEmpty() || pass.isEmpty()) {
                                showAlert("Please enter both username and password.");
                                return;
                        }

                        boolean isLoggedIn = pbl3_gradle.controllers.Account.Instance.login(user, pass);
                        if (isLoggedIn) {
                                if(CurrentUser.Instance.getRole()==1){
                                 NavigationManager.navigateToAdminAddAccPage();}
                                else NavigationManager.navigateToProfileMemberPage();
                        } else {
                                String error = pbl3_gradle.controllers.Account.Instance.getLoginResult();
                                showAlert(error.isEmpty() ? "Login failed: Invalid username or password." : error);
                        }
                });
                Image image = new Image(
                                "file:src/main/resources/image/ImageAvatar.png");
                AvatarViewClass avatar = new AvatarViewClass(image, 120.8, 2);
                avatar.setLayoutX(622.6);
                avatar.setLayoutY(134.8);
                RoundedRect rect = new RoundedRect(252.6, 79, 860.8, 560.9, "transparent", "#92badd", 2, 49);
                Pane pane = new Pane();
                pane.getChildren().addAll(rect, username, password, lb2, lb3, loginButton, avatar);
                pane.setStyle(
                                "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);

                return pane;

        }
        private void showAlert(String message) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }


}
