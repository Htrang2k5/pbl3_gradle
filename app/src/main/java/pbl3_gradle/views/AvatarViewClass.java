package pbl3_gradle.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;

public class AvatarViewClass extends StackPane {
    public AvatarViewClass(Image image, double Size, double borderWidth) {
        // Tính kích thước hình vuông nhỏ nhất từ width và height
        double width = image.getWidth();
        double height = image.getHeight();
        double squareSize = Math.min(width, height);

        // Tính vị trí bắt đầu để crop từ giữa
        double x = (width - squareSize) / 2;
        double y = (height - squareSize) / 2;

        // Crop thành hình vuông
        PixelReader reader = image.getPixelReader();
        WritableImage croppedImage = new WritableImage(reader, (int) x, (int) y, (int) squareSize, (int) squareSize);
        // ImageView
        ImageView imageView = new ImageView(croppedImage);
        imageView.setFitWidth(Size);
        imageView.setFitHeight(Size);
        imageView.setPreserveRatio(false);

        // Clip (cắt ảnh)
        double radiu = Size / 2;
        Circle clip = new Circle(radiu, radiu, radiu);
        imageView.setClip(clip);

        // Border
        Circle border = new Circle(radiu, radiu, radiu + borderWidth / 2);
        Color borderColor = Color.web("#92badd"); // Màu viền
        border.setStroke(borderColor);
        border.setStrokeWidth(borderWidth);
        border.setFill(Color.TRANSPARENT);

        // Add vào StackPane
        getChildren().addAll(border, imageView);
    }
}
