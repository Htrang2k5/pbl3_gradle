package pbl3_gradle.controllers;

import pbl3_gradle.models.*;

import java.util.*;
import java.sql.*;

public class DataManager {
    public static final DataManager Instance = new DataManager();

    private DataManager() {
        // Private constructor to prevent instantiation
    }

    public String processData(ResultSet rs) { //testing
//        List<String> data = new ArrayList<>();
        String res = "";
        try{
            while (rs.next()) {
                String name = rs.getString("username");
                res = res + name + " ";
            }
        }
        catch(Exception e){

        }
        return res;
    }

    //kiểm tra xem username đã tồn tại trong DB chưa
    public boolean verifyUsername(String username) {
        String query = "SELECT * FROM user WHERE BINARY username = ?";
        SqlParameter[] param = {
                new SqlParameter(1, username)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            return !rs.next();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //kiểm tra xem username và password có hợp lệ không
    public boolean verifyLogin (String username, String password){
        String query = "SELECT * FROM user WHERE BINARY username = ? AND BINARY password = ?";
        SqlParameter[] param = {
                new SqlParameter(1, username),
                new SqlParameter(2, password)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            return rs.next();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //lấy thông tin đăng nhập và lưu vào CurrentUser
    public void processLogin(String username, String password) {
        String query = "SELECT * FROM user WHERE BINARY username = ? AND BINARY password = ?";
        SqlParameter[] param = {
                new SqlParameter(1, username),
                new SqlParameter(2, password)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try{
            if (rs.next()){
                CurrentUser.Instance.setUserID(rs.getInt("idUser"));
                CurrentUser.Instance.setUserName(rs.getString("username"));
                CurrentUser.Instance.setUserPassword(rs.getString("password"));
                CurrentUser.Instance.setEmail(rs.getString("email"));
                CurrentUser.Instance.setFullName(rs.getString("fullName"));
                CurrentUser.Instance.setRole(rs.getInt("role"));
                CurrentUser.Instance.setEnglishName(rs.getString("englishName"));
                CurrentUser.Instance.setPhone(rs.getString("phone"));
                CurrentUser.Instance.setAddress(rs.getString("address"));
                CurrentUser.Instance.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUser(){
        String query = "SELECT idUser, username FROM user WHERE role NOT IN (0, 1);";
        ResultSet rs = DBHelper.Instance.GetRecords(query);

        List<User> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("idUser"));
                user.setUserName(rs.getString("username"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public User getUserInfoByID(int userID){
        String query = "SELECT * FROM user WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, userID)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        User user = new User();
        try {
            if (rs.next()) {
                user.setUserID(rs.getInt("idUser"));
                user.setUserName(rs.getString("username"));
                user.setUserPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getInt("role"));
                user.setEnglishName(rs.getString("englishName"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void updateUserInfoByID(User user){
        String query = "UPDATE user SET username = ?, password = ?, email = ?, fullName = ?, role = ?, englishName = ?, phone = ?, address = ?, avatar = ? WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, user.getUserName()),
                new SqlParameter(2, user.getUserPassword()),
                new SqlParameter(3, user.getEmail()),
                new SqlParameter(4, user.getFullName()),
                new SqlParameter(5, user.getRole()),
                new SqlParameter(6, user.getEnglishName()),
                new SqlParameter(7, user.getPhone()),
                new SqlParameter(8, user.getAddress()),
                new SqlParameter(9, user.getAvatar()),
                new SqlParameter(10, user.getUserID())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void deleteUserByID(int userID){
        String query = "DELETE FROM user WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, userID)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void registerNewUser(String username, String password, int role) {
        String query = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, username),
                new SqlParameter(2, password),
                new SqlParameter(3, role)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }
}
