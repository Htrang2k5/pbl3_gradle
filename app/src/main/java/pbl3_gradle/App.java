package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pbl3_gradle.views.TestView;

public class App extends Application {
    public void start(Stage stage) {
        TestView view = new TestView();
        Scene scene = new Scene(view.getView(), 400, 300);
        stage.setScene(scene);
        stage.setTitle("PBL3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
