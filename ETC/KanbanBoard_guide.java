package pbl3_gradle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pbl3_gradle.controllers.Account;
import pbl3_gradle.models.*;
import pbl3_gradle.controllers.*;
import pbl3_gradle.util.NavigationManager;

//import pbl3_gradle.views.LoginPage;
//import pbl3_gradle.views.DeleteAccPage;
import pbl3_gradle.views.EditAcc_ShowAccPage;
import pbl3_gradle.views.LoginPage;
import pbl3_gradle.views.ProfileMemberPage;

import java.util.Date;
import java.util.List;

public class App extends Application {
    public void start(Stage stage) {
        //Đầu tiên phải đăng nhập trước
        if (Account.Instance.login("testingagain", "12345678")){
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }

        /* đây là cách tạo project mới, project mới chỉ cần tên, người tạo sẽ là current user
            Project project = new Project();
            project.setProjectName("Test project 1");
            DataManager.Instance.addNewProject(project);
            System.out.println("New project created.");
         */

        //set project mẫu trong database được tạo bởi testingagain cho CurrentProject
        Project workingProject = DataManager.Instance.getProjectByID(1);
        DataManager.Instance.setCurrentProject(workingProject);
        System.out.println("Current project id is: " + CurrentProject.Instance.getIdProject());

        //Tạo biến board dựa trên CurrentProject, set id và load dữ liệu từ database
        //Lưu ý phải set id trước
        //tương tác với board này sẽ là tương tác với database
        Board board = new Board();
        board.setIdBoard(DataManager.Instance.getBoardIdByProject(CurrentProject.Instance.getIdProject()));
        System.out.println("Board id is: " + board.getIdBoard());
        board.loadDataFromDatabase();
        //in ra thông tin board test
        for (TaskList taskList : board.getTaskLists()) {
            System.out.println("TaskList: " + taskList.getIdTaskList());
            for (Task task : taskList.getTasks()) {
                System.out.println(" - Task: " + task.getTitle());
            }
        }
        /* Đây là cách để thêm TaskList và Task vào Board nếu như cần, sẽ được thêm thẳng vào database
            board.createNewTaskList("To Do");
            board.createNewTaskList("In Progress");
            board.createNewTaskList("Done");
            for (TaskList taskList : board.getTaskLists()) {
                taskList.createNewTask("Task 1");
                taskList.createNewTask("Task 2");
                taskList.createNewTask("Task 3");
            }
         */

        /* nếu muốn update thông tin TaskList hoặc Task, điền đầy đủ thông tin là được
            DataManager.Instance.updateTaskList(taskList);
            DataManager.Instance.updateTask(task);
            board.loadDataFromDatabase(); //sau khi update nhớ load lại dữ liệu từ database để cập nhật
         */
        // ví dụ sửa Task có id là 5
//        Task editTask = DataManager.Instance.getTaskById(5);
//        editTask.setTitle("Updated Task Name");
//        editTask.setDescription("Updated Task Description");
//        //có thể dùng hàm convertIntToDate như dưới để chuyển đổi từ int sang Date
//        //giá trị cho vào là Date
//        editTask.setDateDue(editTask.convertIntToDate(2025, 7, 7, 15, 0));
//        editTask.setStatus(true);
//        DataManager.Instance.updateTask(editTask);
//        System.out.println("Task updated successfully.");

        //Cách thêm comment cho task
        Task taskToComment = DataManager.Instance.getTaskById(5); //ví dụ task có id là 5
        Comment newComment = new Comment();
        newComment.setContent("This is a new comment.");
        newComment.setDateCreated(new Date());
        newComment.setIdUser(CurrentUser.Instance.getUserID()); //người dùng hiện tại là người tạo comment
        taskToComment.addComment(newComment); //kết quả sẽ được lưu vào database tự động
        System.out.println("New comment added."); //Thêm comment thành công

        //Cách thêm member cho task
        //Thêm từ danh sách thành viên của project, nên có thể gọi hàm dưới để lấy danh sách thành viên
        List<User> projectMembers = DataManager.Instance.getMemberByProject(CurrentProject.Instance.getIdProject());
        Task taskToAddMember = DataManager.Instance.getTaskById(5); //ví dụ task có id là 5
        //Giả sử ta muốn thêm thành viên, ví dụ ở đây là currentUser vào task
        taskToAddMember.addMember(CurrentUser.Instance.getUserID()); //vậy là xong
        //danh sách thành viên của task được lưu trữ trong task.getMembers();
        //hoặc có thể gọi DataManager.Instance.getMemberByTask(int idTask) để lấy danh sách thành viên của task
        //Để xóa thành viên, ví dụ xóa currentUser khỏi task
        taskToAddMember.removeMember(CurrentUser.Instance.getUserID()); //vậy là xong

        /* Về hàm delete ta có:
            DataManager.Instance.disableUser(int idUserToDisable);
            DataManager.Instance.disableProject(int idProjectToDisable);
            DataManager.Instance.deleteTaskList(int idTaskListToDelete);
            DataManager.Instance.deleteTask(int idTaskToDelete);
            DataManager.Instance.deleteComment(int idCommentToDelete);
            board.loadDataFromDatabase(); //sau khi delete nhớ load lại dữ liệu từ database để cập nhật
         */


        /* Về di chuyển task và task list ta có: Chưa test, chạy sẽ tự động update trong database
            board.moveTask(int idTask, int idTaskList, int position);
            board.moveTaskList(int idTaskList, int position);
        */
    }
    public static void main(String[] args) {
        launch(args);
    }
}
