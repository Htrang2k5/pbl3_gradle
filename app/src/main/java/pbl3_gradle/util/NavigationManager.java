package pbl3_gradle.util;

import javafx.stage.Stage;
import pbl3_gradle.models.Item;
import pbl3_gradle.views.*;
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

    public static void navigateToDeleteAccPage() {
        DeleteAccPage deleteAccPage = new DeleteAccPage();
        navigateTo(deleteAccPage.getView());
    }

    public static void navigateToChangePasswordPage() {
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        navigateTo(changePasswordPage.getView());
    }

    public static void navigateToProfileMemberPage() {
        ProfileMemberPage profileMemberPage = new ProfileMemberPage();
        navigateTo(profileMemberPage.getView());
    }

    public static void navigateToCurrentProjectPage() {
        CurrentProjectPage currentProjectPage = new CurrentProjectPage();
        navigateTo(currentProjectPage.getView());
    }

    public static void navigateToCompeletedProjectPage() {
        CompeletedProjectPage compeletedProjectPage = new CompeletedProjectPage();
        navigateTo(compeletedProjectPage.getView());
    }

    public static void navigateToCommonNotificationsPage() {
        CommonNotificationsPage commonNotificationsPage = new CommonNotificationsPage();
        navigateTo(commonNotificationsPage.getView());
    }

    public static void navigateToProductBacklogPage() {
        ProductBacklogPage productBacklogPage = new ProductBacklogPage();
        navigateTo(productBacklogPage.getView());
    }

    public static void navigateToDetailBacklogPage(Item item) {
        DetailBacklogPage detailBacklogPage = new DetailBacklogPage();
        navigateTo(detailBacklogPage.getView(item));
    }

    public static void navigateToCurrentSprintPage() {
        CurrentSprintPage currentSprintPage = new CurrentSprintPage();
        navigateTo(currentSprintPage.getView());
    }

    public static void navigateToAddNewSprintPage() {
        AddNewSprintPage addNewSprintPage = new AddNewSprintPage();
        navigateTo(addNewSprintPage.getView());
    }

    public static void navigateToSprintListPage() {
        SprintListPage sprintListPage = new SprintListPage();
        navigateTo(sprintListPage.getView());
    }
}
