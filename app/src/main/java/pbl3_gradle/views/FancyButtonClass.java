package pbl3_gradle.views;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.EventHandler;

public class FancyButtonClass extends Button {
    public FancyButtonClass(String text, double width, double height, double x, double y) {
        super(text);
        initStyle(width, height, x, y);
        initHoverEffect();
        initClickEffect();
    }

    public FancyButtonClass(String text, double width, double height, String bgColor, String textColor) {
        super(text);
        initStyleMenuButton(width, height, bgColor, textColor);
        initHoverEffect();
        initClickEffect();
    }

    private void initStyle(double width, double height, double x, double y) {
        this.setStyle(
                "-fx-background-color: #ffffff;"
                        + "-fx-text-fill: #2f74eb;"
                        + "-fx-font-size: 16px;"
                        + "-fx-border-radius: 36px;"
                        + "-fx-background-radius: 36px;"
                        + "-fx-border-color: #92badd;"
                        + "-fx-border-width: 2px;"
                        + "-fx-font-family: 'Helvetica';"
                        + "-fx-cursor: hand;");
        this.setPrefSize(width, height);
        this.setCursor(javafx.scene.Cursor.HAND);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    private void initStyleMenuButton(double width, double height, String bgCollor, String textColor) {
        this.setStyle(
                "-fx-background-color: " + bgCollor + ";"
                        + "-fx-text-fill: " + textColor + ";"
                        + "-fx-font-size: 16px;"
                        + "-fx-border-radius: 36px;"
                        + "-fx-background-radius: 36px;");
        this.setPrefSize(width, height);
        this.setCursor(javafx.scene.Cursor.HAND);
    }

    public void setStyleButton(String bgColor, String textColor) {
        this.setStyle(
                "-fx-background-color: " + bgColor + ";"
                        + "-fx-text-fill: " + textColor + ";"
                        + "-fx-font-size: 16px;"
                        + "-fx-border-radius: 36px;"
                        + "-fx-background-radius: 36px;"
                        + "-fx-font-family: 'Helvetica';");
    }

    private final DropShadow hoverShadow = new DropShadow(10, Color.rgb(0, 0, 0, 0.6));

    private final EventHandler<MouseEvent> hoverEnterHandler = e -> {
        this.setEffect(hoverShadow);
        animateScale(1.05);
    };

    private final EventHandler<MouseEvent> hoverExitHandler = e -> {
        this.setEffect(null);
        animateScale(0.95);
    };

    private final EventHandler<MouseEvent> clickPressedHandler = e -> animateScale(0.95);
    private final EventHandler<MouseEvent> clickReleasedHandler = e -> animateScale(1.05);

    private void initHoverEffect() {
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, hoverEnterHandler);
        this.addEventHandler(MouseEvent.MOUSE_EXITED, hoverExitHandler);
    }

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

    public void removeEffects() {
        // Xóa hiệu ứng hiện tại
        this.setEffect(null);
        this.setScaleX(1.0);
        this.setScaleY(1.0);

        // Gỡ đúng event handler
        this.removeEventHandler(MouseEvent.MOUSE_ENTERED, hoverEnterHandler);
        this.removeEventHandler(MouseEvent.MOUSE_EXITED, hoverExitHandler);
        this.removeEventHandler(MouseEvent.MOUSE_PRESSED, clickPressedHandler);
        this.removeEventHandler(MouseEvent.MOUSE_RELEASED, clickReleasedHandler);
    }

}
