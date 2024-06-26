package com.schoolphysicslab.course_work;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}