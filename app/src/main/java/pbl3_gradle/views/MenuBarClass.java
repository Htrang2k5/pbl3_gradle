package pbl3_gradle.views;

import javafx.scene.layout.VBox;

public class MenuBarClass extends VBox {
    public MenuBarClass(int layer, String pageName) {
        if (layer == 1) {
            FancyButtonClass btnAdd = new FancyButtonClass("Add Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnEdit = new FancyButtonClass("Edit Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnDelete = new FancyButtonClass("Delete Acount", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnChagne = new FancyButtonClass("Change Password", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnLogout = new FancyButtonClass("Log out", 265.9, 50.2, "#92badd", "#ffffff");
            if (pageName.equals("AdminAddAccPage")) {
                btnAdd.setStyleButton("#ffffff", "#2f74eb");
                btnAdd.removeEffects();
            }
            this.getChildren().addAll(btnAdd, btnEdit, btnDelete, btnChagne, btnLogout);
            this.setPrefSize(265.9, 311.3);
            this.setSpacing(21);
        }
    }
}
