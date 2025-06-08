package pbl3_gradle.controllers;
import pbl3_gradle.models.*;
import java.util.Calendar;
import java.util.Date;
import java.time.*;

public class Validator {
    public static final Validator Instance = new Validator();

    private Validator() {
        //
    }

    private String validateUsernameRes = "";
    private String validatePasswordRes = "";

    //kiểm tra tên đăng nhập có hợp lệ hay không
    public Boolean validateUsername(String username) {
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
    public Boolean validatePassword(String pass1, String pass2) {
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

    //kiểm tra số điện thoại có hợp lệ hay không
    public Boolean validatePhone(String phone){
        final String PHONE_REGEX = "^([+]?\\d{1,3}[-.\\s]?)?(\\(\\d{1,4}\\)[-.\\s]?)?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";

        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return phone.matches(PHONE_REGEX);
    }

    //kiểm tra email có hợp lệ hay không
    public Boolean validateEmail(String email){
        final String STRICT_EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches(STRICT_EMAIL_REGEX);
    }

    //kiểm tra due date có hợp lệ hay không
    public Boolean validateDueDate(int year, int month, int day, int hour24, int minute){
        Date now = new Date();

        // Create Calendar object for the due date
        Calendar dueCal = Calendar.getInstance();
        dueCal.set(year, month - 1, day, hour24, minute, 0); // month is 0-based
        Date dueDate = dueCal.getTime();

        // Return true if due date is in the future
        return dueDate.after(now);
    }
  
    //kiểm tra project name có hợp lệ hay không
    public Boolean validateProjectName(String projectName) {
        if (projectName == null || projectName.trim().isEmpty()) {
            return false;
        }
        // Kiểm tra độ dài tên dự án
        if (projectName.length() > 50) {
            return false;
        }
        // Kiểm tra ký tự hợp lệ
        if (!projectName.matches("^[a-zA-Z0-9_\\s]+$")) {
            return false;
        }
        return true;
    }

    //kiểm tra date có hợp lệ hay không
    public Boolean validateDate(LocalDate startdate, LocalDate enddate) {
        if (startdate == null || enddate == null) {
            return false;
        }
        // Kiểm tra ngày không được là ngày trước hôm nay
        LocalDate today = LocalDate.now();
        if (startdate.isBefore(today)) {
            return false;
        }
        //Kiểm tra ngày kết thúc không được trước ngày bắt đầu
        if (enddate.isBefore(startdate)) {
            return false;
        }
        // Kiểm tra ngày kết thúc không được là ngày trước hôm nay
        if (enddate.isBefore(today)) {
            return false;
        }
        return true;
    }
    //ABCXYZ
    //??????
}
