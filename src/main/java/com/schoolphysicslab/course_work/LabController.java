package com.schoolphysicslab.course_work;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LabController {

    @FXML
    private Pane chooseLab7Pane, chooseLab8Pane, chooseLab9Pane, finalPane;

    @FXML
    private ScrollPane lab7Pane, lab8Pane, lab9Pane;

    @FXML
    private Label nameLabel, classLabel;

    private static String className, emailName;

    private static String getClassName() {
        return className;
    }

    private static String getEmailName() {
        return emailName;
    }

    @FXML
    private ChoiceBox<String> teacherChoiceBox;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField answer1Lab9, answer2_1Lab9, answer2_2Lab9, answer3_1Lab9, answer3_2Lab9, answer4Lab9, answer5Lab9,
    answer6Lab9;

    @FXML
    private TextField answer1Lab8, answer2Lab8, answer3_1Lab8, answer3_2Lab8, answer4_1Lab8, answer4_2Lab8, answer5_1Lab8,
    answer5_2Lab8, answer5_3Lab8, answer6Lab8;

    @FXML
    private TextField answer2Lab7, answer3Lab7, answer4Lab7;
    @FXML
    private ChoiceBox<String> ans1Lab7ChoiceBox, ans5Lab7ChoiceBox, ans6Lab7ChoiceBox;

    public static void openChooseLabStage(String selectedClass, String email) {
        if (selectedClass.equals("7 - А") || selectedClass.equals("7 - Б") || selectedClass.equals("7 - В") || selectedClass.equals("7 - Г")) {
            try {
                Stage chooseLabStage = new Stage();
                FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("chooseLab7.fxml"));
                Parent root = fxmlLoader.load();

                chooseLabStage.setTitle("School Physics Lab: Вибір лабораторної роботи - 7 клас");
                chooseLabStage.setScene(new Scene(root));
                chooseLabStage.setMaximized(true);
                chooseLabStage.show();

                className = selectedClass;
                emailName = email;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (selectedClass.equals("8 - А") || selectedClass.equals("8 - Б") || selectedClass.equals("8 - В") || selectedClass.equals("8 - Г")) {
            try {
                Stage chooseLabStage = new Stage();
                FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("chooseLab8.fxml"));
                Parent root = fxmlLoader.load();

                chooseLabStage.setTitle("School Physics Lab: Вибір лабораторної роботи - 8 клас");
                chooseLabStage.setScene(new Scene(root));
                chooseLabStage.setMaximized(true);
                chooseLabStage.show();

                className = selectedClass;
                emailName = email;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Stage chooseLabStage = new Stage();
                FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("chooseLab9.fxml"));
                Parent root = fxmlLoader.load();

                chooseLabStage.setTitle("School Physics Lab: Вибір лабораторної роботи - 9 клас");
                chooseLabStage.setScene(new Scene(root));
                chooseLabStage.setMaximized(true);
                chooseLabStage.show();

                className = selectedClass;
                emailName = email;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void returnToMainStage() throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();

        if (chooseLab7Pane != null) {
            Stage chooseLabStage = (Stage) chooseLab7Pane.getScene().getWindow();
            chooseLabStage.close();
        } else if (chooseLab8Pane != null) {
            Stage chooseLabStage = (Stage) chooseLab8Pane.getScene().getWindow();
            chooseLabStage.close();
        } else {
            Stage chooseLabStage = (Stage) chooseLab9Pane.getScene().getWindow();
            chooseLabStage.close();
        }

    }

    @FXML
    private void returnToMainStage1() throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();

        if (lab7Pane != null) {
            Stage chooseLabStage = (Stage) lab7Pane.getScene().getWindow();
            chooseLabStage.close();
        } else if (lab8Pane != null) {
            Stage chooseLabStage = (Stage) lab8Pane.getScene().getWindow();
            chooseLabStage.close();
        } else if (lab9Pane != null)  {
            Stage chooseLabStage = (Stage) lab9Pane.getScene().getWindow();
            chooseLabStage.close();
        } else {
            Stage finalLabStage = (Stage) finalPane.getScene().getWindow();
            finalLabStage.close();
        }
    }

    @FXML
    private void openLab1Class7() {
        try {
            Stage labStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("lab1Class7.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            labStage.setMaximized(true);
            labStage.setTitle("School Physics Lab: 7 клас, Лабораторна Робота №1");
            labStage.setScene(scene);
            labStage.show();

            Stage chooseLabStage = (Stage) chooseLab7Pane.getScene().getWindow();
            chooseLabStage.close();

            LabController controller = fxmlLoader.getController();
            String classN = getClassName();
            controller.setClassLabel(classN);

            String emailN = getEmailName();
            controller.setNameLabel(emailN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLab1Class8() {
        try {
            Stage labStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("lab1Class8.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            labStage.setMaximized(true);
            labStage.setTitle("School Physics Lab: 8 клас, Лабораторна Робота №1");
            labStage.setScene(scene);
            labStage.show();

            Stage chooseLabStage = (Stage) chooseLab8Pane.getScene().getWindow();
            chooseLabStage.close();

            LabController controller = fxmlLoader.getController();
            String classN = getClassName();
            controller.setClassLabel(classN);

            String emailN = getEmailName();
            controller.setNameLabel(emailN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLab1Class9() {
        try {
            Stage labStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("lab1Class9.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            labStage.setMaximized(true);
            labStage.setTitle("School Physics Lab: 9 клас, Лабораторна Робота №1");
            labStage.setScene(scene);
            labStage.show();

            Stage chooseLabStage = (Stage) chooseLab9Pane.getScene().getWindow();
            chooseLabStage.close();

            LabController controller = fxmlLoader.getController();
            String classN = getClassName();
            controller.setClassLabel(classN);

            String emailN = getEmailName();
            controller.setNameLabel(emailN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setClassLabel(String className) {
        if (classLabel != null) {
            classLabel.setText(className);
        }
    }

    private void setNameLabel(String email) {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "select student_surname, student_name, student_patronymic from students where student_email = ?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, email);
            ResultSet result = prst.executeQuery();

            String name = "", surname="", patronymic="";
            while(result.next()) {
                surname = result.getString(1);
                name = result.getString(2);
                patronymic = result.getString(3);
            }
            String fullName = surname + " " + name + " " + patronymic;
            if (nameLabel != null) {
                nameLabel.setText(fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveAnswers() {
        int stId = 0;
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String email = getEmailName();
            String sql = "select id_student from students where student_email = ?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, email);
            ResultSet result = prst.executeQuery();

            while(result.next()) {
                stId = result.getInt(1);
            }
            if (stId == 0) {
                System.out.println("Такого студента не знайдено");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String teacherName = teacherChoiceBox.getValue();
        int teacherChoice = 0;

        if (teacherName == null) {
            System.out.println("Ви не обрали вчителя ");
            errorLabel.setText("Ви не обрали вчителя ");
        } else {
            if (teacherName.equals("Даниленко Дмитро Іванович")) {
                System.out.println("Було обрано вчителя " + teacherName);
                teacherChoice = 1;
            } else if (teacherName.equals("Морозова Тетяна Олексіївна")) {
                System.out.println("Було обрано вчителя " + teacherName);
                teacherChoice = 2;
            } else if (teacherName.equals("Петренко Іван Сергійович")) {
                System.out.println("Було обрано вчителя " + teacherName);
                teacherChoice = 3;
            } else {
                System.out.println("Ви не обрали вчителя ");
            }
            String stClass = classLabel.getText();
            if (stClass.equals("7 - А") || stClass.equals("7 - Б") || stClass.equals("7 - В") || stClass.equals("7 - Г")) {
                saveAnswersLab1_7(stId, teacherChoice);
            } else if (stClass.equals("8 - А") || stClass.equals("8 - Б") || stClass.equals("8 - В") || stClass.equals("8 - Г")) {
                saveAnswersLab1_8(stId, teacherChoice);
            } else {
                saveAnswersLab1_9(stId, teacherChoice);
            }
        }
    }

    private void saveAnswersLab1_9(int stId, int teacherChoice) {
        int labId = 91;

        String ans1Lab9 = answer1Lab9.getText();
        String ans2Lab9 = answer2_1Lab9.getText() + " " + answer2_2Lab9.getText();
        String ans3Lab9 = answer3_1Lab9.getText() + " " + answer3_2Lab9.getText();
        String ans4Lab9 = answer4Lab9.getText();
        String ans5Lab9 = answer5Lab9.getText();
        String ans6Lab9 = answer6Lab9.getText();
        System.out.println("номер студента: " + stId);
        System.out.println("Номер вчителя: " + teacherChoice);
        System.out.println("номер роботи: " + labId);

        System.out.println("Відповідь 1: " + ans1Lab9);
        System.out.println("Відповідь 2: " + ans2Lab9);
        System.out.println("Відповідь 3: " + ans3Lab9);
        System.out.println("Відповідь 4: " + ans4Lab9);
        System.out.println("Відповідь 5: " + ans5Lab9);
        System.out.println("Відповідь 6: " + ans6Lab9);

        saveAnswersToDB(stId, teacherChoice, labId, ans1Lab9, ans2Lab9, ans3Lab9, ans4Lab9, ans5Lab9, ans6Lab9);
    }

    private void saveAnswersLab1_8(int stId, int teacherChoice) {
        int labId = 81;

        String ans1Lab8 = answer1Lab8.getText();
        String ans2Lab8 = answer2Lab8.getText();
        String ans3Lab8 = answer3_1Lab8.getText() + " " + answer3_2Lab8.getText();
        String ans4Lab8 = answer4_1Lab8.getText() + " " + answer4_2Lab8.getText();
        String ans5Lab8 = answer5_1Lab8.getText() + " " + answer5_2Lab8.getText() + " " + answer5_3Lab8.getText();
        String ans6Lab8 = answer6Lab8.getText();

        System.out.println("Лаб. 8");
        System.out.println("номер студента: " + stId);
        System.out.println("Номер вчителя: " + teacherChoice);
        System.out.println("номер роботи: " + labId);

        System.out.println("Відповідь 1: " + ans1Lab8);
        System.out.println("Відповідь 2: " + ans2Lab8);
        System.out.println("Відповідь 3: " + ans3Lab8);
        System.out.println("Відповідь 4: " + ans4Lab8);
        System.out.println("Відповідь 5: " + ans5Lab8);
        System.out.println("Відповідь 6: " + ans6Lab8);

        saveAnswersToDB(stId, teacherChoice, labId, ans1Lab8, ans2Lab8, ans3Lab8, ans4Lab8, ans5Lab8, ans6Lab8);
    }

    private void saveAnswersLab1_7(int stId, int teacherChoice) {
        int labId = 71;

        String ans1Lab7 = ans1Lab7ChoiceBox.getValue();
        String ans2Lab7 = answer2Lab7.getText();
        String ans3Lab7 = answer3Lab7.getText();
        String ans4Lab7 = answer4Lab7.getText();
        String ans5Lab7 = ans5Lab7ChoiceBox.getValue();
        String ans6Lab7 = ans6Lab7ChoiceBox.getValue();

        System.out.println("номер студента: " + stId);
        System.out.println("Номер вчителя: " + teacherChoice);

        saveAnswersToDB(stId, teacherChoice, labId, ans1Lab7, ans2Lab7, ans3Lab7, ans4Lab7, ans5Lab7, ans6Lab7);
        openFinalLabWindow();
    }

    private void saveAnswersToDB(int stId, int teacherChoice, int labId, String ans1, String ans2, String ans3, String ans4, String ans5, String ans6) {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "insert into results (id_student, id_teacher, id_lab, task1_stAnswear, task2_stAnswear, task3_stAnswear, task4_stAnswear, task5_stAnswear, task6_stAnswear) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prSt = con.prepareStatement(sql);
            prSt.setInt(1, stId);
            prSt.setInt(2, teacherChoice);
            prSt.setInt(3, labId);
            prSt.setString(4, ans1);
            prSt.setString(5, ans2);
            prSt.setString(6, ans3);
            prSt.setString(7, ans4);
            prSt.setString(8, ans5);
            prSt.setString(9, ans6);
            prSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        openFinalLabWindow();
    }

    private void openFinalLabWindow() {
        try {
            Stage finalLabStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(LabController.class.getResource("finalLabStage.fxml"));
            Parent root = fxmlLoader.load();

            finalLabStage.setTitle("School Physics Lab: Вибір лабораторної роботи - 7 клас");
            finalLabStage.setScene(new Scene(root));
            finalLabStage.setMaximized(true);
            finalLabStage.show();

            if (lab7Pane != null) {
                Stage chooseLabStage = (Stage) lab7Pane.getScene().getWindow();
                chooseLabStage.close();
            } else if (lab8Pane != null) {
                Stage chooseLabStage = (Stage) lab8Pane.getScene().getWindow();
                chooseLabStage.close();
            } else if (lab9Pane != null)  {
                Stage chooseLabStage = (Stage) lab9Pane.getScene().getWindow();
                chooseLabStage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }


}
