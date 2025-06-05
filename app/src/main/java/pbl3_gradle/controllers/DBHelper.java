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

//    public int ExecuteDB(String query, SqlParameter[] params){
//        try {
//            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            for (SqlParameter p : params) {
//                if (p.value instanceof String) {
//                    ps.setString(p.index, (String) p.value);
//                } else if (p.value instanceof Integer) {
//                    ps.setInt(p.index, (Integer) p.value);
//                } else if (p.value instanceof Date) {
//                    ps.setDate(p.index, new java.sql.Date(((java.util.Date) p.value).getTime()));
//                } else if (p.value instanceof Boolean) {
//                    ps.setBoolean(p.index, (Boolean) p.value);
//                } else {
//                    ps.setObject(p.index, p.value);
//                }
//            }
//            ps.executeUpdate();
//            return ps.getGeneratedKeys().getInt(1);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }

    // General-purpose execute (UPDATE, DELETE, ALTER, INSERT if no ID needed)
    public void ExecuteDB(String query, SqlParameter[] params) {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            setParameters(ps, params);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Insert with generated ID retrieval
    public int ExecuteInsertAndGetId(String query, SqlParameter[] params) {
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(ps, params);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return -1; // or throw exception depending on your error handling strategy
    }

    // Helper method to set parameters consistently
    private void setParameters(PreparedStatement ps, SqlParameter[] params) throws SQLException {
        for (SqlParameter p : params) {
            if (p.value instanceof String) {
                ps.setString(p.index, (String) p.value);
            } else if (p.value instanceof Integer) {
                ps.setInt(p.index, (Integer) p.value);
            } else if (p.value instanceof Date) {
                ps.setDate(p.index, new java.sql.Date(((java.util.Date) p.value).getTime()));
            } else if (p.value instanceof Boolean) {
                ps.setBoolean(p.index, (Boolean) p.value);
            } else {
                ps.setObject(p.index, p.value);
            }
        }
    }

}
