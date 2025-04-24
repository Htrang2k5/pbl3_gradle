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
            } else if (pageName.equals("DeleteAccPage")) {
                btnDelete.setStyleButton("#ffffff", "#2f74eb");
                btnDelete.removeEffects();
            } else if (pageName.equals("ChangePasswordPage")) {
                btnChagne.setStyleButton("#ffffff", "#2f74eb");
                btnChagne.removeEffects();
            }
            this.getChildren().addAll(btnAdd, btnEdit, btnDelete, btnChagne, btnLogout);
            this.setPrefSize(265.9, 311.3);
            this.setSpacing(21);
        }
    }
}
