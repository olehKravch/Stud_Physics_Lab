package com.schoolphysicslab.course_work;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private GridPane mainGridPane;

    @FXML
    private Label welcomeText;

    @FXML
    private void loginAsTeacher() {
        try {
            Stage studentStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(HelloController.class.getResource("teacherLogIn.fxml"));
            studentStage.setTitle("School Physics Lab: Вхід вчителя");
            studentStage.setScene(new Scene(fxmlLoader.load()));

            studentStage.setMaximized(true);
            studentStage.show();

            Stage mainStage = (Stage) mainGridPane.getScene().getWindow();
            mainStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Вхід як вчитель");
    }

    @FXML
    private void loginAsStudent() {
        try {
            Stage studentStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(HelloController.class.getResource("student.fxml"));
            studentStage.setTitle("School Physics Lab: Вибір класу");
            studentStage.setScene(new Scene(fxmlLoader.load()));

            studentStage.setMaximized(true);
            studentStage.show();

            Stage mainStage = (Stage) mainGridPane.getScene().getWindow();
            mainStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}