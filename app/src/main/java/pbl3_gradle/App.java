package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import pbl3_gradle.views.TestView;
import pbl3_gradle.views.LoginPage;

public class App extends Application {
    public void start(Stage stage) {
        LoginPage view = new LoginPage();
        Scene scene = new Scene(view.getView(), 1366, 768);
        stage.setScene(scene);
        stage.setTitle("PBL3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
