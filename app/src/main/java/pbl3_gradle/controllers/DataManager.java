package pbl3_gradle.controllers;

import pbl3_gradle.models.*;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.*;
import java.time.*;

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

    //Phần Current Project
    //Khi chọn một project, lưu thông tin vào CurrentProject
    public void setCurrentProject(Project project) {
        CurrentProject.Instance.setIdProject(project.getIdProject());
        CurrentProject.Instance.setProjectName(project.getProjectName());
        CurrentProject.Instance.setDescription(project.getDescription());
        CurrentProject.Instance.setDateCreated(project.getDateCreated());
        CurrentProject.Instance.setDateModified(project.getDateModified());
        CurrentProject.Instance.setStatus(project.isStatus());
        CurrentProject.Instance.setProductBacklog(project.getProductBacklog());
        CurrentProject.Instance.setSprintList(project.getSprintList());
    }

    //Kiểm tra trùng item trong Product Backlog, trùng thì trả về true, không trùng thì trả về false
    public Boolean verifyItemTitle(ProductBacklog productBacklog, Item item) {
        String query = "SELECT * FROM item WHERE BINARY title = ? AND idBacklog = ? AND backlogType = ?";
        SqlParameter[] param = {
                new SqlParameter(1, item.getTitle()),
                new SqlParameter(2, productBacklog.getIdProductBacklog()),
                new SqlParameter(3, 0) // backlogType = 0 cho Product Backlog
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Lấy thông tin của Product Backlog hiện tại, trả về ProductBacklog
    public ProductBacklog getCurrentProductBacklog() {
        int idProject = CurrentProject.Instance.getIdProject();
        String query = "SELECT * FROM productBacklog WHERE idProject = ?";
        SqlParameter[] param = { new SqlParameter(1, idProject) };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            if (rs.next()) {
                int idProductBacklog = rs.getInt("idProductBacklog");
                List<Item> items = DataManager.Instance.getAllItemByBacklog(idProductBacklog, 0);
                return new ProductBacklog(idProductBacklog, idProject, items);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Lấy tất cả item trong Product Backlog
    public List<Item> getAllItemByBacklog(int idBacklog, int backlogType) {
        String query = "SELECT * FROM item WHERE idBacklog = ? AND backlogType = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idBacklog),
                new SqlParameter(2, backlogType)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<Item> itemList = new ArrayList<>();
        try {
            while (rs.next()) {
                Item item = new Item();
                item.setIdItem(rs.getInt("idItem"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setDateCreated(rs.getDate("dateCreated"));
                item.setDateModified(rs.getDate("dateModified"));
                item.setStatus(rs.getBoolean("status"));
                item.setIdBacklog(rs.getInt("idBacklog"));
                item.setBacklogType(rs.getBoolean("backlogType"));

                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    //Thêm một item vào Product Backlog, cập nhật lại Product Backlog, trả về Product Backlog đã cập nhật
    public ProductBacklog addNewItemToProductBacklog(ProductBacklog productBacklog, Item item) {
        item.setIdBacklog(productBacklog.getIdProductBacklog());
        item.setBacklogType(true); // Product backlog

        String query = "INSERT INTO item (idBacklog, backlogType, title, description, dateCreated, dateModified, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, item.getIdBacklog()),
                new SqlParameter(2, item.getBacklogType()),
                new SqlParameter(3, item.getTitle()),
                new SqlParameter(4, item.getDescription()),
                new SqlParameter(5, item.getDateCreated()),
                new SqlParameter(6, item.getDateModified()),
                new SqlParameter(7, item.getStatus())
        };
        DBHelper.Instance.ExecuteDB(query, param);

        productBacklog.addItem(item);
        return productBacklog;
    }

    //Xoá một item khỏi Product Backlog, cập nhật lại Product Backlog, trả về Product Backlog đã cập nhật
    public ProductBacklog removeItemFromProductBacklog(ProductBacklog productBacklog, Item item) {
        String query = "DELETE FROM item WHERE idItem = ?";
        SqlParameter[] param = {
                new SqlParameter(1, item.getIdItem())
        };
        DBHelper.Instance.ExecuteDB(query, param);

        productBacklog.removeItem(item);
        return productBacklog;
    }

    //Cập nhật thông tin của một item trong Product Backlog, cập nhật lại Product Backlog, trả về Product Backlog đã cập nhật
    public ProductBacklog updateItemInProductBacklog(ProductBacklog productBacklog, Item item) {
        String query = "UPDATE item SET title = ?, description = ?, dateModified = ? WHERE idItem = ?";
        SqlParameter[] param = {
                new SqlParameter(1, item.getTitle()),
                new SqlParameter(2, item.getDescription()),
                new SqlParameter(3, item.getDateModified()),
                new SqlParameter(4, item.getIdItem())
        };
        DBHelper.Instance.ExecuteDB(query, param);

        productBacklog.updateItem(item);
        return productBacklog;
    }

    //Lấy toàn bộ sprint của project hiện tại, lưu vào SprintList, trả về SprintList
    public SprintList getCurrentSprintList() {
        int idProject = CurrentProject.Instance.getIdProject();
        String query = "SELECT * FROM sprint WHERE idProject = ?";
        SqlParameter[] param = { new SqlParameter(1, idProject) };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        SprintList sprintList = new SprintList();
        try {
            while (rs.next()) {
                Sprint sprint = new Sprint();
                sprint.setIdSprint(rs.getInt("idSprint"));
                sprint.setIdProject(rs.getInt("idProject"));
                sprint.setStartDate(rs.getDate("startDate"));
                sprint.setActualEndDate(rs.getDate("endDate"));
                sprint.setStatus(rs.getBoolean("status"));

                List<Item> items = DataManager.Instance.getAllItemByBacklog(sprint.getIdSprint(), 1);
                sprint.setItems(items);
                sprintList.addSprint(sprint);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sprintList;
    }

    //Lấy danh sách sprint đã hoàn thành, trả về một danh sách sprint
    public List<Sprint> getFinishedSprints() {
        int idProject = CurrentProject.Instance.getIdProject();
        String query = "SELECT * FROM sprint WHERE idProject = ? AND status = true";
        SqlParameter[] param = { new SqlParameter(1, idProject) };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<Sprint> unfinishedSprints = new ArrayList<>();
        try {
            while (rs.next()) {
                Sprint sprint = new Sprint();
                sprint.setIdSprint(rs.getInt("idSprint"));
                sprint.setIdProject(rs.getInt("idProject"));
                sprint.setTitle(rs.getString("title"));
                sprint.setStartDate(rs.getDate("startDate"));
                sprint.setEstimatedEndDate(rs.getDate("estimatedEndDate"));
                sprint.setActualEndDate(rs.getDate("actualEndDate"));
                sprint.setStatus(rs.getBoolean("status"));

                unfinishedSprints.add(sprint);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unfinishedSprints;
    }

    //Lấy danh sách sprint chưa hoàn thành, trả về một danh sách sprint
    public List<Sprint> getUnfinishedSprints() {
        int idProject = CurrentProject.Instance.getIdProject();
        String query = "SELECT * FROM sprint WHERE idProject = ? AND status = false";
        SqlParameter[] param = { new SqlParameter(1, idProject) };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<Sprint> unfinishedSprints = new ArrayList<>();
        try {
            while (rs.next()) {
                Sprint sprint = new Sprint();
                sprint.setIdSprint(rs.getInt("idSprint"));
                sprint.setIdProject(rs.getInt("idProject"));
                sprint.setTitle(rs.getString("title"));
                sprint.setStartDate(rs.getDate("startDate"));
                sprint.setEstimatedEndDate(rs.getDate("estimatedEndDate"));
                sprint.setActualEndDate(rs.getDate("actualEndDate"));
                sprint.setStatus(rs.getBoolean("status"));

                unfinishedSprints.add(sprint);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unfinishedSprints;
    }

    //Thêm một sprint mới vào SprintList, cập nhật lại SprintList, trả về SprintList đã cập nhật
    public SprintList addNewSprintToSprintList(SprintList sprintList, Sprint sprint) {
        sprint.setIdProject(CurrentProject.Instance.getIdProject());
        sprint.setStatus(false); // Mặc định trạng thái là chưa hoàn thành

        String query = "INSERT INTO sprint (idProject, title, startDate, estimatedEndDate, actualEndDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, sprint.getIdProject()),
                new SqlParameter(2, sprint.getTitle()),
                new SqlParameter(3, sprint.getStartDate()),
                new SqlParameter(4, sprint.getEstimatedEndDate()),
                new SqlParameter(5, sprint.getActualEndDate()),
                new SqlParameter(6, sprint.getStatus())
        };
        DBHelper.Instance.ExecuteDB(query, param);
        sprintList.addSprint(sprint);
        return sprintList;
    }

    //Xoá một sprint khỏi SprintList, cập nhật lại SprintList, trả về SprintList đã cập nhật
    public SprintList removeSprintFromSprintList(SprintList sprintList, Sprint sprint) {
        String query = "DELETE FROM sprint WHERE idSprint = ?";
        SqlParameter[] param = {
                new SqlParameter(1, sprint.getIdSprint())
        };
        DBHelper.Instance.ExecuteDB(query, param);

        sprintList.removeSprint(sprint);
        return sprintList;
    }

    //Cập nhật thông tin của một sprint trong SprintList, cập nhật lại SprintList, trả về SprintList đã cập nhật, chỉ có thể sửa title
    public SprintList updateSprintInSprintList(SprintList sprintList, Sprint sprint) {
        String query = "UPDATE sprint SET title = ? WHERE idSprint = ?";
        SqlParameter[] param = {
                new SqlParameter(1, sprint.getTitle()),
        };
        DBHelper.Instance.ExecuteDB(query, param);

        sprintList.updateSprint(sprint);
        return sprintList;
    }

    //Thay đổi trạng thái của một sprint, trả về true nếu thành công, false nếu thất bại
    public boolean changeSprintStatus(Sprint sprint) {
        String query = "UPDATE sprint SET status = ? WHERE idSprint = ?";
        SqlParameter[] param = {
                new SqlParameter(1, !sprint.getStatus()), // Đảo trạng thái hiện tại
                new SqlParameter(2, sprint.getIdSprint())
        };
        try {
            DBHelper.Instance.ExecuteDB(query, param);
            sprint.setStatus(!sprint.getStatus()); // Cập nhật trạng thái trong đối tượng Sprint
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Lấy khoảng thời gian ước tính của một sprint
    public long getEstimatedTimeForSprint(Sprint sprint) {
        String query = "SELECT startDate, estimatedEndDate FROM sprint WHERE idSprint = ?";
        SqlParameter[] param = {
                new SqlParameter(1, sprint.getIdSprint())
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);
        try {
            if (rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate estimatedEndDate = rs.getDate("estimatedEndDate").toLocalDate();
                if (startDate != null && estimatedEndDate != null) {
                    return ChronoUnit.DAYS.between(startDate, estimatedEndDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Lấy khoảng thời gian thực tế của một sprint
    public long getActualTimeForSprint(Sprint sprint) {
        String query = "SELECT startDate, actualEndDate FROM sprint WHERE idSprint = ?";
        SqlParameter[] param = {
                new SqlParameter(1, sprint.getIdSprint())
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);
        try {
            if (rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate actualEndDate = rs.getDate("actualEndDate").toLocalDate();
                if (startDate != null && actualEndDate != null) {
                    return ChronoUnit.DAYS.between(startDate, actualEndDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

