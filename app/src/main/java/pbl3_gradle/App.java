package pbl3_gradle;

//import org.checkerframework.checker.units.qual.A;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import pbl3_gradle.views.TestView;
//import pbl3_gradle.views.LoginPage;
///import pbl3_gradle.views.AdminAddAccPage;
import pbl3_gradle.views.EditAcc_ShowAccPage;

public class App extends Application {
    public void start(Stage stage) {
        // LoginPage view = new LoginPage();
        // AdminAddAccPage view = new AdminAddAccPage();
        EditAcc_ShowAccPage view = new EditAcc_ShowAccPage();
        Scene scene = new Scene(view.getView(), 1366, 768);
        stage.setScene(scene);
        stage.setTitle("PBL3");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
