package pbl3_gradle.controllers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {
    public static final Account Instance = new Account();

    private Account(){
        System.out.println("Initializing Account"); //for debugging
    }

    //hash original password using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //đăng nhập với username và password
    public Boolean login(String username, String password){
        String hashedPassword = hashPassword(password);
        if (DataManager.Instance.verifyLogin(username, hashedPassword)){
            DataManager.Instance.processLogin(username, hashedPassword);
            return true;
        }
        else return false;
    }

    //lưu tài khoản mới vào DB với username, password, role
    public void registerUser(String username, String password, int role) {
        String hashedPassword = hashPassword(password);
        DataManager.Instance.registerNewUser(username, hashedPassword, role);
    }
}