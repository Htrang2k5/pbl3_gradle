package pbl3_gradle.common;

import javafx.scene.layout.VBox;
import pbl3_gradle.util.AppContext;
import pbl3_gradle.util.NavigationManager;

public class MenuBarClass extends VBox {
    public MenuBarClass(int layer, String pageName) {
        if (layer == 1) {
            FancyButtonClass btnAdd = new FancyButtonClass("Add Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnEdit = new FancyButtonClass("Edit Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnDelete = new FancyButtonClass("Delete Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnChagne = new FancyButtonClass("Change Password", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnLogout = new FancyButtonClass("Log out", 265.9, 50.2, "#92badd", "#ffffff");
            btnAdd.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToAdminAddAccPage();
            });
            btnEdit.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToEditAccShowAccPage();
            });
            btnDelete.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToDeleteAccPage();
            });
            btnChagne.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToChangePasswordPage();
            });
            btnLogout.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToLoginPage();
            });
            if (pageName.equals("AdminAddAccPage")) {
                btnAdd.setStyleButton("#ffffff", "#2f74eb");
                btnAdd.removeEffects();
                btnAdd.setOnAction(null);

            } else if (pageName.equals("EditAcc_ShowAccPage") || pageName.equals("EditAcc_EditingPage")) {
                btnEdit.setStyleButton("#ffffff", "#2f74eb");
                btnEdit.removeEffects();
                btnEdit.setOnAction(null);
            } else if (pageName.equals("DeleteAccPage")) {
                btnDelete.setStyleButton("#ffffff", "#2f74eb");
                btnDelete.removeEffects();
                btnDelete.setOnAction(null);
            } else if (pageName.equals("ChangePasswordPage")) {
                btnChagne.setStyleButton("#ffffff", "#2f74eb");
                btnChagne.removeEffects();
                btnChagne.setOnAction(null);
            }
            this.getChildren().addAll(btnAdd, btnEdit, btnDelete, btnChagne, btnLogout);
            this.setPrefSize(265.9, 311.3);
            this.setSpacing(21);
        } else if (layer == 2) {
            FancyButtonClass btnProfile = new FancyButtonClass("Profile", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnProject = new FancyButtonClass("Project", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnNotifications = new FancyButtonClass("Notifications", 265.9, 50.2, "#92badd",
                    "#ffffff");
            FancyButtonClass btnLogout = new FancyButtonClass("Log out", 265.9, 50.2, "#92badd", "#ffffff");
            btnProfile.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToProfileMemberPage();
            });
            btnProject.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToCurrentProjectPage();
            });
            btnLogout.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToLoginPage();
            });
            btnNotifications.setOnAction(e -> {
                AppContext.clear();
                NavigationManager.navigateToCommonNotificationsPage();
            });
            if (pageName.equals("ProfileMemberPage")) {
                btnProfile.setStyleButton("#ffffff", "#2f74eb");
                btnProfile.removeEffects();
                btnProfile.setOnAction(null);
            } else if (pageName.equals("CurrentProjectPage") || pageName.equals("CompeletedProjectPage")) {
                btnProject.setStyleButton("#ffffff", "#2f74eb");
                btnProject.removeEffects();
                btnProject.setOnAction(null);
            } else if (pageName.equals("CommonNotificationsPage")) {
                btnNotifications.setStyleButton("#ffffff", "#2f74eb");
                btnNotifications.removeEffects();
                btnNotifications.setOnAction(null);
            }
            this.getChildren().addAll(btnProfile, btnProject, btnNotifications, btnLogout);
            this.setPrefSize(265.9, 257.2);
            this.setSpacing(21);
        }
    }
}
