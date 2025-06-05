package pbl3_gradle.common;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PasswordTextFieldClass extends TextField {
    private final StringBuilder realPassword = new StringBuilder(); // Lưu mật khẩu thật
    private final PauseTransition maskingDelay = new PauseTransition(Duration.seconds(1));

    public PasswordTextFieldClass() {
        setPromptText("Enter password");

        // Khi hết thời gian delay, ẩn toàn bộ ký tự
        maskingDelay.setOnFinished(e -> {
            maskAll();
            positionCaret(getText().length());
        });

        // Xử lý phím nhập văn bản (ký tự thường)
        addEventFilter(KeyEvent.KEY_TYPED, this::handleInsert);

        // Xử lý phím xóa BACK_SPACE
        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                int caret = getCaretPosition();
                int start = getSelection().getStart();
                int end = getSelection().getEnd();

                if (end > start) {
                    realPassword.delete(start, end);
                    maskAll();
                    positionCaret(start);
                } else if (caret > 0) {
                    realPassword.deleteCharAt(caret - 1);
                    maskAll();
                    positionCaret(caret - 1);
                }

                event.consume();
            }
        });

        // Đảm bảo luôn được focus để bắt phím
        Platform.runLater(this::requestFocus);
    }

    public PasswordTextFieldClass(String password) {
        this();
        realPassword.append(password);
        setText("*".repeat(realPassword.length()));
        positionCaret(getText().length());
    }

    private void handleInsert(KeyEvent event) {
        String ch = event.getCharacter();

        if (ch.isEmpty() || ch.equals("\r") || ch.equals("\n") || ch.equals("\t")) {
            event.consume();
            return;
        }

        int start = getCaretPosition();
        int end = getSelection().getEnd();

        if (end > start) {
            realPassword.delete(start, end);
        }

        realPassword.insert(start, ch);

        // Hiển thị tạm ký tự vừa nhập
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < realPassword.length(); i++) {
            display.append(i == start ? realPassword.charAt(i) : '*');
        }

        setText(display.toString());
        positionCaret(start + 1);

        maskingDelay.playFromStart();
        event.consume();
    }

    private void maskAll() {
        setText("*".repeat(realPassword.length()));
    }

    public String getRealPassword() {
        return realPassword.toString();
    }

    public void clearPassword() {
        realPassword.setLength(0);
        setText("");
    }

    public String getPassword() {
        return realPassword.toString();
    }
}