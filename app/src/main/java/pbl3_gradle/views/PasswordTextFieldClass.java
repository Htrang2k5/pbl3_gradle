package pbl3_gradle.views;

// co the gap loi khi xu ly su kien -> can test som
import javafx.animation.PauseTransition;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PasswordTextFieldClass extends TextField {
    private final StringBuilder realPassword = new StringBuilder();
    private final PauseTransition maskingDelay = new PauseTransition(Duration.seconds(1));

    public PasswordTextFieldClass() {
        setPromptText("Enter password");

        // Khi delay xong, mask toàn bộ
        maskingDelay.setOnFinished(e -> {
            setText("*".repeat(realPassword.length()));
            positionCaret(getText().length());
        });

        // Bắt Backspace / Delete
        addEventFilter(KeyEvent.KEY_PRESSED, this::handleDelete);

        // Bắt insertion
        addEventFilter(KeyEvent.KEY_TYPED, this::handleInsert);
    }

    private void handleDelete(KeyEvent event) {
        if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
            int start = getSelection().getStart();
            int end = getSelection().getEnd();
            int caret = getCaretPosition();

            if (end > start) {
                // Xóa selection
                realPassword.delete(start, end);
                maskAll();
                positionCaret(start);
            } else if (event.getCode() == KeyCode.BACK_SPACE && caret > 0) {
                realPassword.deleteCharAt(caret - 1);
                maskAll();
                positionCaret(caret - 1);
            } else if (event.getCode() == KeyCode.DELETE && caret < realPassword.length()) {
                realPassword.deleteCharAt(caret);
                maskAll();
                positionCaret(caret);
            }
            event.consume();
        }
    }

    private void handleInsert(KeyEvent event) {
        String ch = event.getCharacter();
        // Bỏ qua Enter, Tab, v.v.
        if (ch.isEmpty() || ch.equals("\r") || ch.equals("\n") || ch.equals("\t"))
            return;

        int start = getCaretPosition();
        int end = getSelection().getEnd();

        // Nếu có selection thì xóa trước
        if (end > start) {
            realPassword.delete(start, end);
        }

        // Thêm ký tự mới
        realPassword.insert(start, ch);

        // Hiển thị tạm thời: chỉ ký tự mới hiện, các ký tự khác mask
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < realPassword.length(); i++) {
            tmp.append(i == start ? realPassword.charAt(i) : '*');
        }
        setText(tmp.toString());
        positionCaret(start + 1);

        // Restart delay để mask hết
        maskingDelay.playFromStart();
        event.consume();
    }

    private void maskAll() {
        setText("*".repeat(realPassword.length()));
    }

    /** Trả về mật khẩu thật */
    public String getRealPassword() {
        return realPassword.toString();
    }

    /** Xóa hoàn toàn */
    public void clearPassword() {
        realPassword.setLength(0);
        setText("");
    }

    public String getPassword() {
        return realPassword.toString();
    }

}
