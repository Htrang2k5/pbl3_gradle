package pbl3_gradle.util;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl3_gradle.common.RoundedRect;
import pbl3_gradle.common.FancyButtonClass;
import javafx.util.Duration;

public class CustomMessageBox {

    public static boolean show(String title, String message) {
        final boolean[] result = { false }; // kết quả trả về

        Stage dialog = new Stage();

        if (title.equals("Error!")) {
            dialog.initModality(Modality.APPLICATION_MODAL); // Chặn tương tác với cửa sổ chính
            dialog.setTitle(title);
            dialog.setMinWidth(612); // Đặt chiều rộng tối thiểu cho cửa sổ
            dialog.setMinHeight(291.3); // Đặt chiều cao tối thiểu cho cửa sổ
            dialog.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ

            Label label = new Label(message);
            label.setWrapText(true);
            label.setStyle(
                    "-fx-text-fill: #eb2d4b"
                            + " -fx-font-size: 18px;"
                            + " -fx-alignment: center; "
                            + "-fx-font-family:'Helvetica';");
            label.setPrefSize(429.4, 61.2);
            label.setLayoutX(89.7);
            label.setLayoutY(74.9);

            // Tao khung hinh chu nhat
            RoundedRect rect1 = new RoundedRect(34.3, 31.2, 546.6, 229, "transparent", "#92badd", 2, 36);

            // Tao button exit
            FancyButtonClass okBtn = new FancyButtonClass("OK", 166.6, 51.7, 224.3, 174.2);
            okBtn.setOnAction(e -> {
                result[0] = true;
                dialog.close();
            });

            Pane pane = new Pane();
            pane.setPrefSize(612, 291);
            pane.getChildren().addAll(rect1, label, okBtn);
            Scene scene = new Scene(pane);
            dialog.setScene(scene);
            dialog.showAndWait();

        } else if (title.equals("Warning!")) {
            dialog.initModality(Modality.APPLICATION_MODAL); // Chặn tương tác với cửa sổ chính
            dialog.setTitle(title);
            dialog.setMinWidth(612); // Đặt chiều rộng tối thiểu cho cửa sổ
            dialog.setMinHeight(291.3); // Đặt chiều cao tối thiểu cho cửa sổ
            dialog.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ

            Label label = new Label(message);
            label.setWrapText(true);
            label.setStyle(
                    "-fx-text-fill: #2f74eb;"
                            + " -fx-font-size: 18px;"
                            + " -fx-alignment: center; "
                            + "-fx-font-family:'Helvetica';");
            label.setPrefSize(429.4, 61.2);
            label.setLayoutX(89.7);
            label.setLayoutY(74.9);

            // Tao khung hinh chu nhat
            RoundedRect rect1 = new RoundedRect(34.3, 31.2, 546.6, 229, "transparent", "#92badd", 2, 36);

            // Tao button exit
            FancyButtonClass okBtn = new FancyButtonClass("OK", 166.6, 51.7, 113.4, 174.2);
            FancyButtonClass cancelBtn = new FancyButtonClass("Cancel", 166.6, 51.7, 328.4, 174.2);
            okBtn.setOnAction(e -> {
                result[0] = true;
                dialog.close();
            });
            cancelBtn.setOnAction(e -> {
                result[0] = false;
                dialog.close();
            });

            Pane pane = new Pane();
            pane.setPrefSize(612, 291);
            pane.getChildren().addAll(rect1, label, okBtn, cancelBtn);
            Scene scene = new Scene(pane);
            dialog.setScene(scene);
            dialog.showAndWait();

        } else if (title.equals("Success!")) {
            dialog.initModality(Modality.NONE); // Bo Chặn tương tác với cửa sổ chính
            dialog.setTitle(title);
            dialog.setMinWidth(612); // Đặt chiều rộng tối thiểu cho cửa sổ
            dialog.setMinHeight(291.3); // Đặt chiều cao tối thiểu cho cửa sổ
            dialog.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ
            dialog.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    dialog.close();
                }
            });
            // Tao label
            Label label = new Label(message);
            label.setWrapText(true);
            label.setStyle(
                    "-fx-text-fill: #2f74eb;"
                            + " -fx-font-size: 18px;"
                            + "-fx-font-family:'Helvetica';");
            label.setPrefWidth(467.4);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);

            // Tao khung hinh chu nhat
            VBox rect1 = new VBox();
            rect1.setPrefSize(546.6, 229);
            rect1.setStyle(
                    "-fx-background-color: #ffffff;"
                            + " -fx-background-radius: 36px;"
                            + " -fx-border-radius: 36px;"
                            + " -fx-border-color: #92badd;"
                            + " -fx-border-width: 2px;");
            rect1.setLayoutX(34.3);
            rect1.setLayoutY(31.2);
            rect1.getChildren().addAll(label);
            rect1.setAlignment(Pos.CENTER);

            Pane pane = new Pane();
            pane.setPrefSize(612, 291);
            pane.getChildren().addAll(rect1);
            pane.setStyle(
                    "-fx-background-color: #ffffff; -fx-background-size: cover;");
            Scene scene = new Scene(pane);
            dialog.setScene(scene);
            dialog.show();
            // Hẹn giờ tự động đóng
            PauseTransition delay = new PauseTransition(Duration.seconds(4));
            delay.setOnFinished(e -> dialog.close());
            delay.play();
        }

        return result[0];
    }
}

// Cach su dung
// boolean confirmed = CustomMessageBox.show("Xác nhận", "Bạn có chắc chắn muốn
// thoát không?");if(confirmed)
// {
// System.out.println("Người dùng chọn OK");
// }else
// {
// System.out.println("Người dùng chọn Hủy");
// }
