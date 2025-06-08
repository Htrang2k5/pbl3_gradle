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
                AppContext.set("currentPage", "AdminAddAccPage");
                NavigationManager.navigateToAdminAddAccPage();
            });
            btnEdit.setOnAction(e -> {
                AppContext.set("currentPage", "EditAcc_EditingPage");
                NavigationManager.navigateToEditAccShowAccPage();
            });
            btnDelete.setOnAction(e -> {
                AppContext.set("currentPage", "DeleteAccPage");
                NavigationManager.navigateToDeleteAccPage();
            });
            btnChagne.setOnAction(e -> {
                AppContext.set("currentPage", "ChangePasswordPage");
                NavigationManager.navigateToChangePasswordPage();
            });
            btnLogout.setOnAction(e -> {
                AppContext.set("currentPage", "LoginPage");
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
                AppContext.set("currentPage", "ProfileMemberPage");
                NavigationManager.navigateToProfileMemberPage();
            });
            btnProject.setOnAction(e -> {
                AppContext.set("currentPage", "CurrentProjectPage");
                NavigationManager.navigateToCurrentProjectPage();
            });
            btnLogout.setOnAction(e -> {
                AppContext.set("currentPage", "LoginPage");
                NavigationManager.navigateToLoginPage();
            });
            btnNotifications.setOnAction(e -> {
                AppContext.set("currentPage", "CommonNotificationsPage");
                NavigationManager.navigateToCommonNotificationsPage();
            });
            if (pageName.equals("ProfileMemberPage")) {
                btnProfile.setStyleButton("#ffffff", "#2f74eb");
                btnProfile.removeEffects();
                btnProfile.setOnAction(null);
            } else if (pageName.equals("CurrentProjectPage") || pageName.equals("CompeletedProjectPage")
                    || pageName.equals("EditProjectPage")) {
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
        } else {
            FancyButtonClass btnProductBacklog = new FancyButtonClass("Product Backlog", 265.9, 50.2, "#92badd",
                    "#ffffff");
            FancyButtonClass btnSprint = new FancyButtonClass("Sprint", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnKanbanBoard = new FancyButtonClass("Kanban Board", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnBurndown_Chart = new FancyButtonClass("Burndown Chart", 265.9, 50.2, "#92badd",
                    "#ffffff");
            FancyButtonClass btnMeetingPlan = new FancyButtonClass("Meeting Plan", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnMembers = new FancyButtonClass("Members", 265.9, 50.2, "#92badd", "#ffffff");
            FancyButtonClass btnNotifications = new FancyButtonClass("Notifications", 265.9, 50.2, "#92badd",
                    "#ffffff");
            btnProductBacklog.setOnAction(e -> {
                AppContext.set("currentPage", "ProductBacklogPage");
                NavigationManager.navigateToProductBacklogPage();
            });
            btnSprint.setOnAction(e -> {
                AppContext.set("currentPage", "CurrentSprintPage");
                NavigationManager.navigateToCurrentSprintPage();
            });
            btnKanbanBoard.setOnAction(e -> {
                AppContext.set("currentPage", "KanbanBoardPage");
                NavigationManager.navigateToKanbanBoardPage();
            });
            btnBurndown_Chart.setOnAction(e -> {
                AppContext.set("currentPage", "BurndownChartPage");
                NavigationManager.navigateToBurndownChartPage();
            });
            btnMembers.setOnAction(e -> {
                AppContext.set("currentPage", "ProjectMembersPage");
                NavigationManager.navigateToProjectMembersPage();
            });
            if (pageName.equals("ProductBacklogPage")) {
                btnProductBacklog.setStyleButton("#ffffff", "#2f74eb");
                btnProductBacklog.removeEffects();
                btnProductBacklog.setOnAction(null);
            } else if (pageName.equals("DetailBacklogPage")) {
                if (AppContext.get("currentPage").equals("ProductBacklogPage")) {
                    btnProductBacklog.setStyleButton("#ffffff", "#2f74eb");
                    btnProductBacklog.removeEffects();
                    btnProductBacklog.setOnAction(null);
                } else if (AppContext.get("currentPage").equals("CurrentSprintPage")) {
                    btnSprint.setStyleButton("#ffffff", "#2f74eb");
                    btnSprint.removeEffects();
                    btnSprint.setOnAction(null);
                }
            } else if (pageName.equals("CurrentSprintPage") || pageName.equals("AddNewSprintPage")
                    || pageName.equals("SprintListPage")) {
                btnSprint.setStyleButton("#ffffff", "#2f74eb");
                btnSprint.removeEffects();
                btnSprint.setOnAction(null);
            } else if (pageName.equals("KanbanBoardPage") || pageName.equals("DetailTaskPage")) {
                btnKanbanBoard.setStyleButton("#ffffff", "#2f74eb");
                btnKanbanBoard.removeEffects();
                btnKanbanBoard.setOnAction(null);
            } else if (pageName.equals("BurndownChartPage")) {
                btnBurndown_Chart.setStyleButton("#ffffff", "#2f74eb");
                btnBurndown_Chart.removeEffects();
                btnBurndown_Chart.setOnAction(null);
            } else if (pageName.equals("ProjectMembersPage")) {
                btnMembers.setStyleButton("#ffffff", "#2f74eb");
                btnMembers.removeEffects();
                btnMembers.setOnAction(null);
            }
            this.getChildren().addAll(btnProductBacklog, btnSprint, btnKanbanBoard, btnBurndown_Chart,
                    btnMeetingPlan, btnMembers, btnNotifications);
            this.setPrefSize(269.5, 456.8);
            this.setSpacing(17.8);
        }
    }
}
