package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pbl3_gradle.util.NavigationManager;
import pbl3_gradle.views.KanbanBoardPage;
//import pbl3_gradle.views.LoginPage;
//import pbl3_gradle.views.DeleteAccPage;
//import pbl3_gradle.views.ProfileMemberPage;
// import pbl3_gradle.views.SprintListPage;

public class App extends Application {
    public void start(Stage stage) {
        // LoginPage view = new LoginPage();
        // DeleteAccPage view = new DeleteAccPage();
        // ProfileMemberPage view = new ProfileMemberPage();
        // SprintListPage view = new SprintListPage();
        KanbanBoardPage view = new KanbanBoardPage();
        Scene scene = new Scene(view.getView(), 1366, 768);
        stage.setScene(scene);
        stage.setTitle("PBL3: Phần mềm hỗ trợ quản lý dự án");
        stage.setResizable(false);
        stage.show();
        NavigationManager.setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
