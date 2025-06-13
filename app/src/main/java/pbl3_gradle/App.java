package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pbl3_gradle.util.NavigationManager;


//import pbl3_gradle.views.LoginPage;
//import pbl3_gradle.views.DeleteAccPage;
import pbl3_gradle.views.EditAcc_ShowAccPage;
import pbl3_gradle.views.*;
import pbl3_gradle.views.ProfileMemberPage;

public class App extends Application {
    public void start(Stage stage) {
//         CurrentSprintPage view = new CurrentSprintPage();
         LoginPage view = new LoginPage();
        // DeleteAccPage view admindfdsfsdf= new DeleteAccPage();
//        ProfileMemberPage view = new ProfileMemberPage();
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
