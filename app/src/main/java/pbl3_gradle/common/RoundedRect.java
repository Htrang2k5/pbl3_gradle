package pbl3_gradle.common;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoundedRect extends Rectangle {

    public RoundedRect(double x, double y, double width, double height, String fillColor, String borderColor,
            double borderWidth, double arc) {
        super(x, y, width, height);

        // Bo tròn góc
        this.setArcWidth(arc * 2);
        this.setArcHeight(arc * 2);

        // Màu nền
        this.setFill(Color.web(fillColor)); // Ví dụ: "#ffffff" hoặc "transparent"

        // Viền
        this.setStroke(Color.web(borderColor)); // Ví dụ: "#000000"
        this.setStrokeWidth(borderWidth);
    }

    // Constructor rút gọn nếu muốn mặc định
    public RoundedRect() {
        this(0, 0, 296.1, 768, "#c4dff8", "#ffffff", 0, 0);
    }
}
