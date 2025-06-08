package pbl3_gradle.models;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pbl3_gradle.controllers.*;
import pbl3_gradle.models.*;

public class BurndownChart {
    public void start(Stage stage) {
//        Project project = new Project();
//        project.setIdProject(1);
//        project = DataManager.Instance.getProjectByID(project.getIdProject());
//        DataManager.Instance.setCurrentProject(project);

        stage.setTitle("Sprint Burndown Chart");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Sprint");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Days");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Burndown Chart");

        XYChart.Series<Number, Number> estimatedSeries = new XYChart.Series<>();
        estimatedSeries.setName("Estimated");

        XYChart.Series<Number, Number> actualSeries = new XYChart.Series<>();
        actualSeries.setName("Actual");

//        SprintList sprintList = DataManager.Instance.getCurrentSprintList();
//        int count = 1;
//        for (Sprint sprint : sprintList.getSprintList()) {
//            long estimatedDay = DataManager.Instance.getEstimatedTimeForSprint(sprint);
//            long actualDay = DataManager.Instance.getActualTimeForSprint(sprint);
//            estimatedSeries.getData().add(new XYChart.Data<>(count, estimatedDay));
//            actualSeries.getData().add(new XYChart.Data<>(count, actualDay));
//            count++;
//        }

        lineChart.getData().addAll(estimatedSeries, actualSeries);

        VBox root = new VBox(lineChart);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
