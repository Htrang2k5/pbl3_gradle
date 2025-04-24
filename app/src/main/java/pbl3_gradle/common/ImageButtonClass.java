package pbl3_gradle.common;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ImageButtonClass extends Button {
    public ImageButtonClass(Image image, double width, double height, double x, double y) {
        super();
        setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-cursor: hand;");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setGraphic(imageView);
        setLayoutX(x);
        setLayoutY(y);
        initClickEffect();
    }

    private final EventHandler<MouseEvent> clickPressedHandler = e -> animateScale(0.95);
    private final EventHandler<MouseEvent> clickReleasedHandler = e -> animateScale(1.05);

    private void initClickEffect() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, clickPressedHandler);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, clickReleasedHandler);
    }

    private void animateScale(double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), this);
        st.setToX(scale);
        st.setToY(scale);
        st.play();
    }

}
