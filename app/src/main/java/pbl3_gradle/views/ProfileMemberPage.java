package pbl3_gradle.views;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pbl3_gradle.common.*;

public class ProfileMemberPage {
        public Pane getView() {
                // Tao menu bar
                Pane menuBar = MenuBarStyle_Layer2(
                        "file:src/main/resources/image/ImageAvatar.png", "Nguyễn Thị Huyền Trang",
                        "Scrum Master",
                        "ProfileMemberPage");
                // Tao Avatar iamge
                Image image1 = new Image(
                        "file:src/main/resources/image/ImageAvatar.png");
                AvatarViewClass avatar1 = new AvatarViewClass(image1, 205.7, 4);
                avatar1.setLayoutX(732.6);
                avatar1.setLayoutY(18.2);
                // Tao button doi avatar
                Image image2 = new Image(
                        "file:src/main/resources/image/ChangeAvatar.png");
                AvatarViewClass avatar2 = new AvatarViewClass(image2, 46.1, 0);
                ImageButtonClass btnChange = new ImageButtonClass(avatar2, 46.1, 46.1, 879.3, 178.3);
                // Tao label ten
                Label nameLb = new Label("Nguyễn Thị Huyền Trang");
                nameLb.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 20 px; -fx-alignment: center; -fx-font-family: :'Helvetica';");
                nameLb.setPrefSize(443.1, 31.7);
                nameLb.setLayoutX(613.9);
                nameLb.setLayoutY(245);
                // Tao hinh chu nhat bao quanh bang thong tin
                RoundedRect rect = new RoundedRect(377, 297.6, 918.2, 434.6, "#c4dff8", "#ffffff", 0, 15);
                // Tao bang thong tin
                GridPane gridPane = BangThongTin("HTrangg", "Scrum Master");
                gridPane.setLayoutX(403);
                gridPane.setLayoutY(315);
                // Tao pane
                Pane pane = new Pane();
                pane.getChildren().addAll(menuBar, avatar1, btnChange, nameLb, rect, gridPane);
                pane.setStyle(
                        "-fx-background-color: #ffffff; -fx-background-sdize: cover; ");
                pane.setPrefSize(1366, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public static Pane MenuBarStyle_Layer2(String image, String text, String role, String page) {
                // Tạo hình chữ nhật cho menu
                RoundedRect rect = new RoundedRect();
                // Tạo hình avatar
                Image imageA = new Image(image);

                AvatarViewClass avatar = new AvatarViewClass(imageA, 120.8, 2);
                avatar.setLayoutX(87.7);
                avatar.setLayoutY(41.2);
                // Tao Label cho avatar
                Label avarLb = new Label(text);
                avarLb.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 15 px; -fx-alignment: center; -fx-font-family: :'Helvetica';");
                avarLb.setPrefSize(276.8, 24);
                avarLb.setLayoutX(9.7);
                avarLb.setLayoutY(167.6);
                // Tao Label cho role
                Label roleLb = new Label(role);
                roleLb.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 12 px; -fx-alignment: center; -fx-font-family: :'Helvetica';");
                roleLb.setPrefSize(148.7, 18.8);
                roleLb.setLayoutX(73.7);
                roleLb.setLayoutY(197.6);
                // Tao MenuBar
                MenuBarClass menuBar = new MenuBarClass(2, page);
                menuBar.setLayoutX(15.1);
                menuBar.setLayoutY(347.1);
                Pane pane = new Pane();
                pane.getChildren().addAll(rect, avatar, avarLb, roleLb, menuBar);
                pane.setStyle(
                        "-fx-background-color: #ffffff; -fx-background-size: cover;");
                pane.setPrefSize(296.1, 768);
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                return pane;
        }

        public static GridPane BangThongTin(String nameText, String roleText) {
                GridPane gridPane = new GridPane();
                Label lb1 = new Label("Username");
                Label lb2 = new Label("Full Name");
                Label lb3 = new Label("English Name");
                Label lb4 = new Label("Date of Birth");
                Label lb5 = new Label("Address");
                Label lb6 = new Label("Role");
                Label lb7 = new Label("Phone Number");
                Label lb8 = new Label(nameText);
                Label lb9 = new Label(roleText);

                lb1.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb2.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb3.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb4.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");

                lb5.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb6.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb7.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb8.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb9.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;");
                lb1.setPrefSize(273.2, 57.2);
                lb2.setPrefSize(273.2, 57.2);
                lb3.setPrefSize(273.2, 57.2);
                lb4.setPrefSize(273.2, 57.2);
                lb5.setPrefSize(273.2, 57.2);
                lb6.setPrefSize(273.2, 57.2);
                lb7.setPrefSize(273.2, 57.2);

                lb8.setPrefSize(593.9, 57.2);
                lb9.setPrefSize(593.9, 57.2);

                gridPane.add(lb1, 0, 0);
                gridPane.add(lb2, 0, 1);
                gridPane.add(lb3, 0, 2);
                gridPane.add(lb4, 0, 3);
                gridPane.add(lb5, 0, 4);
                gridPane.add(lb6, 0, 5);
                gridPane.add(lb7, 0, 6);
                gridPane.add(lb8, 1, 0);
                gridPane.add(lb9, 1, 5);

                TextField tf1 = new TextField();
                tf1.setPromptText("Enter your full name here");
                tf1.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px; -fx-background-color: transparent;");
                tf1.setPrefSize(593.9, 57.2);

                TextField tf2 = new TextField();
                tf2.setPromptText("Enter your English name here");
                tf2.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;-fx-background-color: transparent;");
                tf2.setPrefSize(593.9, 57.2);

                TextField tf3 = new TextField();
                tf3.setPromptText("Enter your date of birth here");
                tf3.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;-fx-background-color: transparent;");
                tf3.setPrefSize(593.9, 57.2);
                TextField tf4 = new TextField();
                tf4.setPromptText("Enter your address here");
                tf4.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;-fx-background-color: transparent;");
                tf4.setPrefSize(593.9, 57.2);
                TextField tf5 = new TextField();
                tf5.setPromptText("Enter your phone number here");
                tf5.setStyle(
                        "-fx-text-fill: #2f74eb; -fx-font-size: 14 px; -fx-alignment: center; -fx-font-family: :'Helvetica'; -fx-border-color: #ffffff; -fx-border-width:1px;-fx-background-color: transparent;");
                tf5.setPrefSize(593.9, 57.2);
                gridPane.add(tf1, 1, 1);
                gridPane.add(tf2, 1, 2);
                gridPane.add(tf3, 1, 3);
                gridPane.add(tf4, 1, 4);
                gridPane.add(tf5, 1, 6);
                return gridPane;
        }

}