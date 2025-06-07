package pbl3_gradle.views;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BurndownChartPage {
    public Pane getView() {
        // Create MenuBar
        // Create the menu bar
        Pane menuBar = ProductBacklogPage.MenuBarStyle_Layer3("Burndown Chart", "BurndownChartPage");
        // Create the main label
        Label mainLabel = new Label("BURNDOWN CHART");
        mainLabel.setStyle(
                "-fx-text-fill: #2f74eb; -fx-font-size: 26px; -fx-alignment: center; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        mainLabel.setPrefSize(302.2, 44.6);
        mainLabel.setLayoutX(683);
        mainLabel.setLayoutY(22.6);
        // Create burndown chart view
        VBox burndownChartView = BurndownChartPage.burndownChartView();
        burndownChartView.setLayoutX(373.3);
        burndownChartView.setLayoutY(100.4);
        // Create the pane
        Pane pane = new Pane();
        pane.getChildren().addAll(menuBar, mainLabel, burndownChartView);
        pane.setStyle(
                "-fx-background-color: #ffffff; -fx-background-size: cover;");
        pane.setPrefSize(1366, 768);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        return pane;
    }

    public static VBox burndownChartView() {
        // Trục X là Sprint ngày (hoặc số sprint)
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Sprint Day");

        // Trục Y là story points còn lại
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Remaining Story Points");

        // Tạo LineChart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        // lineChart.setTitle("Burndown Chart");
        lineChart.setPrefSize(910.8, 569.2);

        // Estimated line (đường dự kiến)
        XYChart.Series<String, Number> estimatedSeries = new XYChart.Series<>();
        estimatedSeries.setName("Estimated");

        // Actual line (đường thực tế)
        XYChart.Series<String, Number> actualSeries = new XYChart.Series<>();
        actualSeries.setName("Actual");

        // Giả sử sprint kéo dài 7 ngày, bắt đầu với 30 story points
        estimatedSeries.getData().add(new XYChart.Data<>("Day 1", 30));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 2", 25));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 3", 20));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 4", 15));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 5", 10));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 6", 5));
        estimatedSeries.getData().add(new XYChart.Data<>("Day 7", 0));

        // Dữ liệu thực tế
        actualSeries.getData().add(new XYChart.Data<>("Day 1", 30));
        actualSeries.getData().add(new XYChart.Data<>("Day 2", 28));
        actualSeries.getData().add(new XYChart.Data<>("Day 3", 24));
        actualSeries.getData().add(new XYChart.Data<>("Day 4", 18));
        actualSeries.getData().add(new XYChart.Data<>("Day 5", 10));
        actualSeries.getData().add(new XYChart.Data<>("Day 6", 4));
        actualSeries.getData().add(new XYChart.Data<>("Day 7", 0));

        // Thêm dữ liệu vào biểu đồ
        lineChart.getData().add(estimatedSeries);
        lineChart.getData().add(actualSeries);
        // Change line colors using CSS
        estimatedSeries.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #2f74eb; -fx-stroke-width: 2px;");
        actualSeries.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #b4a5fa; -fx-stroke-width: 2px;");
        VBox root = new VBox(lineChart);
        root.setStyle(
                "-fx-padding: 20; "
                        + "-fx-background-color: #ffffff;"
                        + "-fx-alignment: center;"
                        + "-fx-border-color: #c4dff8;"
                        + "-fx-border-width: 2;"
                        + "-fx-border-radius: 36;"
                        + "-fx-background-radius: 36;");
        // root.setPrefSize(910.8, 569.2);
        return root;
    }
}
