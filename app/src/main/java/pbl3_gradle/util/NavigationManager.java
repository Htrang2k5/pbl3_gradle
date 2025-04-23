package pbl3_gradle.util;

import javafx.stage.Stage;
import pbl3_gradle.views.AdminAddAccPage;
import pbl3_gradle.views.EditAcc_EditingPage;
import pbl3_gradle.views.EditAcc_ShowAccPage;
import pbl3_gradle.views.LoginPage;
import javafx.scene.layout.Pane;

public class NavigationManager {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigateTo(Pane view) {
        primaryStage.getScene().setRoot(view);
    }

    public static void navigateToAdminAddAccPage() {
        AdminAddAccPage adminAddAccPage = new AdminAddAccPage();
        navigateTo(adminAddAccPage.getView());
    }

    public static void navigateToEditAccEditingPage() {
        EditAcc_EditingPage editAccEditingPage = new EditAcc_EditingPage();
        navigateTo(editAccEditingPage.getView());
    }

    public static void navigateToEditAccShowAccPage() {
        EditAcc_ShowAccPage editAccShowAccPage = new EditAcc_ShowAccPage();
        navigateTo(editAccShowAccPage.getView());
    }

    public static void navigateToLoginPage() {
        LoginPage loginPage = new LoginPage();
        navigateTo(loginPage.getView());
    }

}
