package pbl3_gradle.controllers;
import pbl3_gradle.models.*;

public class Validator {
    public static final Validator Instance = new Validator();

    private Validator() {
        //
    }

    private String validateUsernameRes = "";
    private String validatePasswordRes = "";

    //kiểm tra tên đăng nhập có hợp lệ hay không
    public boolean validateUsername(String username) {
        //kiểm tra tên đăng nhập có hợp lệ hay không
        if (username == null || username.isEmpty()) {
            validateUsernameRes = "Username cannot be empty";
            return false;
        }

        //kiểm tra tên đăng nhập đúng cú pháp hay không
        if (!username.matches("^[a-zA-Z0-9_]{5,20}$")) {
            validateUsernameRes = "Username format is not correct";
            return false;
        }

        //kiểm tra tên đăng nhập đã tồn tại hay chưa
        if (!DataManager.Instance.verifyUsername(username)
            || username.equalsIgnoreCase("system")
            || username.equalsIgnoreCase("admin")) {
            validateUsernameRes = "Username is not available";
            return false;
        }

        validateUsernameRes = "";
        return true;
    }

    //kiểm tra mật khẩu có hợp lệ hay không
    public boolean validatePassword(String pass1, String pass2) {
        //kiểm tra 2 mật khẩu không được rỗng
        if (pass1 == null || pass2 == null || pass1.isEmpty() || pass2.isEmpty()) {
            validatePasswordRes = "Password cannot be empty";
            return false;
        }

        //kiểm tra mật khẩu 1 đúng cú pháp không
        if (!pass1.matches("^[a-zA-Z0-9!@#$%^&*]{8,32}$")) {
            validatePasswordRes = "Password format is not correct";
            return false;
        }

        //kiểm tra 2 mật khẩu có giống nhau không
        if (!pass1.equals(pass2)) {
            validatePasswordRes = "Password does not match";
            return false;
        }

        validatePasswordRes = "";
        return true;
    }

    //lấy kết quả của hàm validateUsername
    public String getValidateUsernameRes() {
        return validateUsernameRes;
    }

    //lấy kết quả của hàm validatePassword
    public String getValidatePasswordRes() {
        return validatePasswordRes;
    }
}
