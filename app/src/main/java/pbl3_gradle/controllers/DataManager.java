package pbl3_gradle.controllers;

import pbl3_gradle.models.SqlParameter;

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
}
