package pbl3_gradle.controllers;
import pbl3_gradle.models.SqlParameter;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.sql.*;

//create an instance to connect to the database right at the start
public class DBHelper {
    public static final DBHelper Instance = new DBHelper();
    private String url = "jdbc:mysql://localhost:3306/pbl3";
    private String user = "root";
    private String password = "";
    private Connection conn = null;
    private Boolean status = false;

    private DBHelper() {    //technically connected successfully
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null)
                status = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public ResultSet GetRecords(String query){
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }

    public ResultSet GetRecords(String query, SqlParameter[] params){
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (SqlParameter p : params) {
                if (p.value instanceof String) {
                    ps.setString(p.index, (String) p.value);
                } else if (p.value instanceof Integer) {
                    ps.setInt(p.index, (Integer) p.value);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }

    public void ExecuteDB(String query){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ExecuteDB(String query, SqlParameter[] params){
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (SqlParameter p : params) {
                if (p.value instanceof String) {
                    ps.setString(p.index, (String) p.value);
                } else if (p.value instanceof Integer) {
                    ps.setInt(p.index, (Integer) p.value);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
