package pbl3_gradle.controllers;

import pbl3_gradle.models.*;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.time.*;

public class DataManager {
    public static final DataManager Instance = new DataManager();

    private DataManager() {
        // Private constructor to prevent instantiation
    }
    //------------------Phần User-------------------
    //kiểm tra xem username đã tồn tại trong DB chưa, và phải không bị disabled mới được
    public boolean verifyUsername(String username) {
        String query = "SELECT * FROM user WHERE BINARY username = ? AND isDisabled = false";
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
                CurrentUser.Instance.setBirthday(rs.getDate("birthday"));
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

    public void disableUser(int idUser) {
        String query = "UPDATE user SET isDisabled = true WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idUser)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void enableUser(int idUser) {
        String query = "UPDATE user SET isDisabled = false WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idUser)
        };
        DBHelper.Instance.ExecuteDB(query, param);
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
//                user.setBirthday(rs.getDate("birthday"));
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

    //-----------------Phần Project-----------------
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

    //Thêm mới một project
    public void addNewProject(Project project){
        String query = "INSERT INTO project (projectName, description, dateCreated, dateModified, status) VALUES (?, ?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, project.getProjectName()),
                new SqlParameter(2, project.getDescription()),
                new SqlParameter(3, project.getDateCreated()),
                new SqlParameter(4, project.getDateModified()),
                new SqlParameter(5, project.isStatus())
        };
        int newProjectId = DBHelper.Instance.ExecuteInsertAndGetId(query, param);
        project.setIdProject(newProjectId);

        //thêm người tạo project vào bảng user_project
        //relationship có giá trị 0 hoặc 1, 0 là creator, 1 là member
        String query2 = "INSERT INTO user_project (idUser, idProject, relationship) VALUES (?, ?, ?)";
        SqlParameter[] param2 = {
                new SqlParameter(1, CurrentUser.Instance.getUserID()),
                new SqlParameter(2, project.getIdProject()),
                new SqlParameter(3, 0) //0 là creator
        };
        DBHelper.Instance.ExecuteDB(query2, param2);

        //sau khi tạo project, thêm cho project đó 1 kanban board
        addNewBoard(project);
    }

    public void addMemberToProject(int idUser, int idProject){
        String query = "INSERT INTO user_project (idUser, idProject, relationship) VALUES (?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, idUser),
                new SqlParameter(2, idProject),
                new SqlParameter(3, 1) //1 là member
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
    public void deleteProject(int idProject) {
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
        String query = "SELECT * FROM project WHERE idProject = ? AND isDisabled = false"; // Chỉ lấy project không bị vô hiệu hóa
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

    //vô hiệu hóa Project, một khi đã bị vô hiệu hóa là không thể sử dụng được nữa
    public void disableProject(int idProject) {
        String query = "UPDATE project SET isDisabled = true WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idProject)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //Lấy tất cả project có liên quan tới CurrentUser
    public List<Project> getProjectsByUser(int idUser) {
        //Tìm list idProject mà CurrentUser đang tham gia
        String query1 = "SELECT idProject FROM user_project WHERE idUser = ?";
        SqlParameter[] param1 = {
                new SqlParameter(1, CurrentUser.Instance.getUserID())
        };
        ResultSet rs1 = DBHelper.Instance.GetRecords(query1, param1);
        List<Integer> projectIds = new ArrayList<>();
        try {
            while (rs1.next()) {
                projectIds.add(rs1.getInt("idProject"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Lấy thông tin của các project theo idProject
        List<Project> projects = new ArrayList<>();
        for (int idProject : projectIds) {
            Project project = getProjectByID(idProject);
            if (project != null) {
                projects.add(project);
            }
        }
        return projects;
    }

    //Lấy toàn bộ project
    public List<Project> getAllProject() {
        String query = "SELECT * FROM project WHERE isDisabled = false"; // Chỉ lấy project không bị vô hiệu hóa
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

    //Tìm thành viên của một project theo idProject
    public List<User> getMemberByProject(int idProject) {
        String query = "SELECT u.idUser FROM user u " +
                       "JOIN user_project up ON u.idUser = up.idUser " +
                       "WHERE up.idProject = ?"; // all member included creator
        SqlParameter[] param = {
                new SqlParameter(1, idProject)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<User> members = new ArrayList<>();
        try {
            while (rs.next()) {
                int userId = rs.getInt("idUser");
                User user = getUserInfoByID(userId); // Lấy thông tin người dùng từ idUser
                members.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
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
        String query = "SELECT * FROM product_backlog WHERE idProject = ?";
        SqlParameter[] param = { new SqlParameter(1, idProject) };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        try {
            if (rs.next()) {
                int idProductBacklog = rs.getInt("idProductBacklog");
                List<Item> items = DataManager.Instance.getAllItemByBacklog(idProductBacklog, 1);
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
                new SqlParameter(5, new Date()),
                new SqlParameter(6,new Date()),
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

                List<Item> items = DataManager.Instance.getAllItemByBacklog(sprint.getIdSprint(), 0);
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
                sprint.setStartDate(rs.getDate("dateStart"));
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
                sprint.setStartDate(rs.getDate("dateStart"));
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

        String query = "INSERT INTO sprint (idProject, title, dateStart, estimatedEndDate, actualEndDate, status) VALUES (?, ?, ?, ?, ?, ?)";
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
                new SqlParameter(2, sprint.getIdSprint())
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
        String query = "SELECT dateStart, estimatedEndDate FROM sprint WHERE idSprint = ?";
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

    //----------------Phần Board--------------------
    //Hàm thêm một bảng mới cho project sau khi project đó được tạo
    private void addNewBoard(Project project){
        String query = "INSERT INTO board (idProject, dateCreated, dateModified) VALUES (?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, project.getIdProject()),
                new SqlParameter(2, project.getDateCreated()),
                new SqlParameter(3, project.getDateModified())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //Lấy id của board theo idProject
    public int getBoardIdByProject(int idProject) {
        String query = "SELECT * FROM board WHERE idProject = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idProject)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);
        int boardId = -1; // Trả về -1 nếu không tìm thấy
        try {
            if (rs.next()) {
                boardId = rs.getInt("idBoard");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boardId;
    }

    public void deleteBoardData(int idBoard){
        List<TaskList> taskListsToDelete = getTaskListByBoardId(idBoard);
        for (TaskList taskList : taskListsToDelete) {
            deleteTaskListData(taskList.getIdTaskList());
        }

        // Xoá board
        String query = "DELETE FROM board WHERE idBoard = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idBoard)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    //-----------------Phần List--------------------
    public List<TaskList> getTaskListByBoardId(int idBoard) {
        String query = "SELECT * FROM task_list WHERE idBoard = ? ORDER BY position";
        SqlParameter[] param = {
                new SqlParameter(1, idBoard)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<TaskList> resTaskList = new ArrayList<>();
        try {
            while (rs.next()) {
                TaskList taskList = new TaskList();
                taskList.setIdTaskList(rs.getInt("idTaskList"));
                taskList.setName(rs.getString("title"));
                taskList.setPosition(rs.getInt("position"));
                resTaskList.add(taskList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resTaskList;
    }

    //Tạo task list mới trong database thuộc về board có idBoard
    public TaskList createTaskList(TaskList taskList, int idBoard) {
        String query = "INSERT INTO task_list (idBoard, title, position, dateCreated) VALUES (?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, idBoard),
                new SqlParameter(2, taskList.getName()),
                new SqlParameter(3, taskList.getPosition()),
                new SqlParameter(4, new Date())
        };
        int newTaskListId = DBHelper.Instance.ExecuteInsertAndGetId(query, param);
        taskList.setIdTaskList(newTaskListId);
        return taskList;
    }

    // Xoá task list trong database
    public void deleteTaskList(TaskList taskList) {
        String query = "DELETE FROM task_list WHERE idTaskList = ?";
        SqlParameter[] param = {
                new SqlParameter(1, taskList.getIdTaskList())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void updateTaskListPosition(TaskList taskList) {
        String query = "UPDATE task_list SET position = ? WHERE idTaskList = ?";
        SqlParameter[] param = {
                new SqlParameter(1, taskList.getPosition()),
                new SqlParameter(2, taskList.getIdTaskList())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void updateTaskList(TaskList taskList) {
        String query = "UPDATE task_list SET title = ? WHERE idTaskList = ?";
        SqlParameter[] param = {
                new SqlParameter(1, taskList.getName()),
                new SqlParameter(2, taskList.getIdTaskList())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void deleteTaskListData(int idTaskList) {
        // Xóa tất cả data của task thuộc tasklist trước
        String getTasksQuery = "SELECT idTask FROM task WHERE idTaskList = ?";
        SqlParameter[] getTasksParam = {
                new SqlParameter(1, idTaskList)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(getTasksQuery, getTasksParam);
        try {
            while (rs.next()) {
                int idTask = rs.getInt("idTask");
                deleteTaskData(idTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Xoá task list
        String deleteTaskListQuery = "DELETE FROM task_list WHERE idTaskList = ?";
        SqlParameter[] deleteTaskListParam = {
                new SqlParameter(1, idTaskList)
        };
        DBHelper.Instance.ExecuteDB(deleteTaskListQuery, deleteTaskListParam);
    }

    //-----------------Phần Task--------------------
    public Task createTask(Task task, int idTaskList) {
        String query = "INSERT INTO task (idTaskList, title, description, status, position, dateCreated, dateModified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, idTaskList),
                new SqlParameter(2, task.getTitle()),
                new SqlParameter(3, task.getDescription()),
                new SqlParameter(4, task.getStatus()),
                new SqlParameter(5, task.getPosition()),
                new SqlParameter(6, new Date()),
                new SqlParameter(7, new Date())
        };
        int newTaskId = DBHelper.Instance.ExecuteInsertAndGetId(query, param);
        task.setIdTask(newTaskId);
        return task;
    }

    public void addMemberToTask(User user, int idTask) {
        String query = "INSERT INTO user_task (idUser, idTask) VALUES (?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, user.getUserID()),
                new SqlParameter(2, idTask)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void removeMemberFromTask(User user, int idTask) {
        String query = "DELETE FROM user_task WHERE idUser = ? AND idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, user.getUserID()),
                new SqlParameter(2, idTask)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public List<Task> getTaskByTaskListId(int idTaskList) {
        String query = "SELECT * FROM task WHERE idTaskList = ? ORDER BY position";
        SqlParameter[] param = {
                new SqlParameter(1, idTaskList)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<Task> resTaskList = new ArrayList<>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setIdTask(rs.getInt("idTask"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getBoolean("status"));
                task.setPosition(rs.getInt("position"));
                task.setDateCreated(rs.getDate("dateCreated"));
                task.setDateModified(rs.getDate("dateModified"));
                task.setDateDue(rs.getDate("dateDue"));
                task.setComments(getCommentsByTaskId(rs.getInt("idTask")));
                task.setMembers(getMemberByTaskId(rs.getInt("idTask")));
                resTaskList.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resTaskList;
    }

    public List<User> getMemberByTaskId(int idTask) {
        String query = "SELECT u.idUser FROM user u " +
                       "JOIN user_task ut ON u.idUser = ut.idUser " +
                       "WHERE ut.idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idTask)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<User> members = new ArrayList<User>();
        try {
            while (rs.next()) {
                int userId = rs.getInt("idUser");
                members.add(getUserInfoInTask(userId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    public void updateTaskDueDate(int idTask, Date dateDue) {
        String query = "UPDATE task SET dateDue = ? WHERE idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, dateDue),
                new SqlParameter(2, idTask)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void updateTaskPosition(Task task) {
        String query = "UPDATE task SET position = ?, dateModified = ? WHERE idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, task.getPosition()),
                new SqlParameter(2, new Date()),
                new SqlParameter(3, task.getIdTask())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public void updateTask(Task task) {
        String query = "UPDATE task SET title = ?, description = ?, status = ?, dateModified = ?, dateDue = ? WHERE idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, task.getTitle()),
                new SqlParameter(2, task.getDescription()),
                new SqlParameter(3, task.getStatus()),
                new SqlParameter(4, new Date()),
                new SqlParameter(5, task.getDateDue()),
                new SqlParameter(6, task.getIdTask())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }

    public Task getTaskById(int idTask) {
        String query = "SELECT * FROM task WHERE idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idTask)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        Task task = new Task();
        try {
            if (rs.next()) {
                task.setIdTask(rs.getInt("idTask"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getBoolean("status"));
                task.setPosition(rs.getInt("position"));
                task.setDateCreated(rs.getDate("dateCreated"));
                task.setDateModified(rs.getDate("dateModified"));
                task.setDateDue(rs.getDate("dateDue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    public User getUserInfoInTask(int idUser) {
        String query = "SELECT * FROM user WHERE idUser = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idUser)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        User user = new User();
        try {
            if (rs.next()) {
                user.setUserID(rs.getInt("idUser"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getInt("role"));
                user.setEnglishName(rs.getString("englishName"));
                user.setPhone(rs.getString("phone"));
                user.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void deleteTaskData(int idTask) {
        //delete task appearance in table comment and user_task
        String query = "DELETE FROM comment WHERE idTask = ?";
        String query2 = "DELETE FROM user_task WHERE idTask = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idTask)
        };
        DBHelper.Instance.ExecuteDB(query, param);
        DBHelper.Instance.ExecuteDB(query2, param);

        //delete task in table task
        String query3 = "DELETE FROM task WHERE idTask = ?";
        DBHelper.Instance.ExecuteDB(query3, param);
    }

    //----------------Phần Comment------------------
    public Comment createComment(Comment comment, int idTask) {
        String query = "INSERT INTO comment (idTask, content, dateCreated, idUser) VALUES (?, ?, ?, ?)";
        SqlParameter[] param = {
                new SqlParameter(1, idTask),
                new SqlParameter(2, comment.getContent()),
                new SqlParameter(3, comment.getDateCreated()),
                new SqlParameter(4, comment.getIdUser())
        };
        int newCommentId = DBHelper.Instance.ExecuteInsertAndGetId(query, param);
        comment.setIdComment(newCommentId);
        return comment;
    }

    public List<Comment> getCommentsByTaskId(int idTask) {
        String query = "SELECT * FROM comment WHERE idTask = ? ORDER BY dateCreated DESC";
        SqlParameter[] param = {
                new SqlParameter(1, idTask)
        };
        ResultSet rs = DBHelper.Instance.GetRecords(query, param);

        List<Comment> comments = new ArrayList<>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt("idComment"));
                comment.setIdUser(rs.getInt("idUser"));
                comment.setContent(rs.getString("content"));
                comment.setDateCreated(rs.getDate("dateCreated"));

                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    public void deleteComment(int idComment) {
        String query = "DELETE FROM comment WHERE idComment = ?";
        SqlParameter[] param = {
                new SqlParameter(1, idComment)
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }
    public void updateComment(Comment comment) {
        String query = "UPDATE comment SET content = ? WHERE idComment = ?";
        SqlParameter[] param = {
                new SqlParameter(1, comment.getContent()),
                new SqlParameter(2, comment.getIdComment())
        };
        DBHelper.Instance.ExecuteDB(query, param);
    }
}
