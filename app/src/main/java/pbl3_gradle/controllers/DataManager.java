package pbl3_gradle.controllers;

import pbl3_gradle.models.*;

import java.util.*;
import java.sql.*;
import java.util.Date;

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

    //Phần User

    //kiểm tra xem username đã tồn tại trong DB chưa
    public boolean verifyUsername(String username) {
        String query = "SELECT * FROM user WHERE BINARY username = ?";
        SqlParameter[] param = {
                new SqlParameter(1, username)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            return rs.next();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //kiểm tra xem username và password có hợp lệ không
    public boolean verifyLogin (String username, String password){
        if (!DataManager.Instance.verifyUsername(username)){
            Account.Instance.setLoginResult("User not found");
            return false;
        }

        String query = "SELECT username, password FROM user WHERE BINARY username = ?";
        SqlParameter[] param = {
                new SqlParameter(1, username)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try{
            if (rs.next()){
                String DBPassword = rs.getString("password");
                if (!password.equals(DBPassword)){
                    Account.Instance.setLoginResult("Wrong password");
                    return false;
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
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

    //Kiểm tra project đã có trong DB chưa
    public Boolean verifyProjectName (String projectName){
        String query = "SELECT * FROM project WHERE BINARY projectName = ?";
        SqlParameter[] param = {
                new SqlParameter(1, projectName)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Phần Project
    //Thêm mới một project
    public void addNewProject(Project project){
        String query = "INSERT INTO project (idProject, projectName, description, dateCreated, dateModified, status) VALUES (?, ?, ?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, project.getIdProject()),
                new SqlParameter(2, project.getProjectName()),
                new SqlParameter(3, project.getDescription()),
                new SqlParameter(4, project.getDateCreated()),
                new SqlParameter(5, project.getDateModified()),
                new SqlParameter(6, project.isStatus())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //Cập nhật trạng thái project: Undone -> Done
    public void updateProjectStatus(Project project){
        String query = "UPDATE project SET status = ? WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, project.isStatus()),
                new SqlParameter(2, project.getIdProject())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //Xoá một project
    public void deleteProject(int idProject){
        String query = "DELETE FROM project WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idProject)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //Cập nhật thông tin project
    public void updateProject(Project project){
        String query = "UPDATE project SET projectName = ?, description = ?, dateModified = ? WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, project.getProjectName()),
                new SqlParameter(2, project.getDescription()),
                new SqlParameter(3, project.getDateModified()),
                new SqlParameter(4, project.getIdProject())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    // Trả về project nếu tìm kiếm theo tên
    public Project getProjectByName(String projectName) {
        String query = "SELECT * FROM project WHERE BINARY projectName = ?"; //sửa
        SqlParameter[] param = {
                new SqlParameter(1, projectName)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);
        Project project = new Project();
        try
        {
            if (rs.next()) {
                project.setIdProject(rs.getInt("idProject"));
                project.setProjectName(rs.getString("projectName"));
                project.setDescription(rs.getString("description"));
                project.setDateCreated(rs.getDate("dateCreated"));
                project.setDateModified(rs.getDate("dateModified"));
                project.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    //Trả về project theo ID
    public Project getProjectByID(int idProject) {
        String query = "SELECT * FROM project WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idProject)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);
        Project project = new Project();
        try
        {
            if (rs.next()) {
                project.setIdProject(rs.getInt("idProject"));
                project.setProjectName(rs.getString("projectName"));
                project.setDescription(rs.getString("description"));
                project.setDateCreated(rs.getDate("dateCreated"));
                project.setDateModified(rs.getDate("dateModified"));
                project.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    //Lấy toàn bộ project
    public List<Project> getAllProject() {
        String query = "SELECT * FROM project";
        ResultSet rs = DBHelper.Instance.GetRecords(query);

        List<Project> projectList = new ArrayList<>();
        try {
            while (rs.next()) {
                Project project = new Project();
                project.setIdProject(rs.getInt("idProject"));
                project.setProjectName(rs.getString("projectName"));
                project.setDescription(rs.getString("description"));
                project.setDateCreated(rs.getDate("dateCreated"));
                project.setDateModified(rs.getDate("dateModified"));
                project.setStatus(rs.getBoolean("status"));

                projectList.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projectList;
    }

    //Phần Item

//    public void addNewItem(Item item) {
//        String query = "INSERT INTO item (idItem, itemName, description, dateCreated, dateModified, status) VALUES (?, ?, ?, ?, ?, ?)";
//        SqlParameter[] param = {
//                new SqlParameter(1, item.getIdItem()),
//                new SqlParameter(2, item.getTitle()),
//                new SqlParameter(3, item.getDescription()),
//                new SqlParameter(4, item.getCreatedDate()),
//                new SqlParameter(5, item.getModifiedDate()),
//                new SqlParameter(6, item.getStatus())
//        };
//        DBHelper.Instance.ExecuteDB(query, param);
//    }

//    public void updateItemStatus(Item item){
//        String query = "UPDATE item SET status = ? WHERE idItem = ?";
//        SqlParameter[] param = {
//                new SqlParameter(1, item.getStatus()),
//                new SqlParameter(2, item.getIdItem())
//        };
//        DBHelper.Instance.ExecuteDB(query, param);
//    }

    public void removeItem(int idItem){
        String query = "DELETE FROM item WHERE idItem = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idItem)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

//    public void updateItem(Item item){
//        String query = "UPDATE item SET itemName = ?, description = ?, dateModified = ? WHERE idItem = ?";
//        SqlParameter[] param = {
//                new SqlParameter(1, item.getTitle()),
//                new SqlParameter(2, item.getDescription()),
//                new SqlParameter(3, item.getModifiedDate()),
//                new SqlParameter(4, item.getIdItem())
//        };
//        DBHelper.Instance.ExecuteDB(query, param);
//    }

}
