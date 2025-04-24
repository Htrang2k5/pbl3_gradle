package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pbl3_gradle.util.NavigationManager;
//import pbl3_gradle.views.TestView;
import pbl3_gradle.views.LoginPage;
///import pbl3_gradle.views.AdminAddAccPage;
//import pbl3_gradle.views.EditAcc_ShowAccPage;
//import pbl3_gradle.views.EditAcc_EditingPage;

public class App extends Application {
    public void start(Stage stage) {
        LoginPage view = new LoginPage();
        // AdminAddAccPage view = new AdminAddAccPage();
        // EditAcc_ShowAccPage view = new EditAcc_ShowAccPage();
        // EditAcc_EditingPage view = new EditAcc_EditingPage();
        Scene scene = new Scene(view.getView(), 1366, 768);
        stage.setScene(scene);
        stage.setTitle("PBL3");
        stage.setResizable(false);
        stage.show();
        NavigationManager.setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
