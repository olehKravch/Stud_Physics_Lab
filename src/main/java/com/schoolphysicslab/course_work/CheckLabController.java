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

public class CheckLabController {

    @FXML
    private ScrollPane checkLabPane;

    @FXML
    private Label teacherName, studName, labName;

    private static String tEmail;

    private static String getTEmail() {
        return tEmail;
    }

    @FXML
    private ChoiceBox<String> classChoiceBox;

    @FXML
    private TextField surnameStud, nameStud;

    @FXML
    private Label errorLabel, errorLabel1;

    @FXML
    private Label stAns1, stAns2, stAns3, stAns4, stAns5, stAns6;

    @FXML
    private Label ans1, ans2, ans3, ans4, ans5, ans6, studMark, markSave;

    @FXML
    private TextField ans1Mark, ans2Mark, ans3Mark, ans4Mark, ans5Mark, ans6Mark;

    private int idStudent;

    private int getIdStudent() {
        return idStudent;
    }

    private int idLabWork;

    private int getIdLabWork() {
        return idLabWork;
    }

    private int totMark;

    private int getTotMark() {
        return totMark;
    }

    public static void openCheckLabStage(String surname, String name, String patronymic, String email) {
        try {
            Stage chooseLabStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(CheckLabController.class.getResource("checkLab.fxml"));
            Parent root = fxmlLoader.load();

            chooseLabStage.setTitle("School Physics Lab: Перевірка лабораторних робіт");
            chooseLabStage.setScene(new Scene(root));
            chooseLabStage.setMaximized(true);
            chooseLabStage.show();
            tEmail = email;
            CheckLabController controller = fxmlLoader.getController();
            String teacherEmail = getTEmail();
            controller.setTeacherName(teacherEmail);
        } catch (Exception e) {
            e.printStackTrace();
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

        Stage checkLabStage = (Stage) checkLabPane.getScene().getWindow();
        checkLabStage.close();
    }

    private void setTeacherName(String email) {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "select teacher_surname, teacher_name, teacher_patronymic from teachers where teacher_email = ?;";
            PreparedStatement prSt = con.prepareStatement(sql);
            prSt.setString(1, email);
            ResultSet result = prSt.executeQuery();

            String tSurname = "";
            String tName = "";
            String tPatronymic = "";
            while(result.next()) {
                tSurname = result.getString(1);
                tName = result.getString(2);
                tPatronymic = result.getString(3);
            }
            String fullName = tSurname + " " + tName + " " + tPatronymic;
            teacherName.setText(fullName);
            /*if (teacherName != null) {
                teacherName.setText(fullName);
            }*/
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void findStudLabs() {
        String stClass = classChoiceBox.getValue();
        String stSurname = surnameStud.getText();
        String stName = nameStud.getText();

        if (stClass == null || stSurname.equals("") || stName.equals("")) {
            errorLabel.setText("");
            errorLabel.setText("Ви не заповнили поля або не обрали клас");
        } else {
            try {
                DBConnector connector = new DBConnector();
                connector.makeDBConnection();
                Connection con = connector.getCon();
                String sql =
                        "select id_student from students where student_surname = ? && student_name = ?";
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setString(1, stSurname);
                prst.setString(2, stName);
                ResultSet result = prst.executeQuery();

                int idStud = 0;
                while (result.next()) {
                    idStud = result.getInt(1);
                }
                if (idStud == 0) {
                    errorLabel.setText("");
                    errorLabel.setText("Такого учня не знайдено");
                } else {
                    try {
                        String sql1 =
                                "select student_surname, student_name, student_patronymic from students where id_student = ?";
                        PreparedStatement prSt = con.prepareStatement(sql1);
                        prSt.setInt(1, idStud);
                        ResultSet result1 = prSt.executeQuery();

                        String sSurname = "";
                        String sName = "";
                        String sPatronymic = "";
                        while(result1.next()) {
                            sSurname = result1.getString(1);
                            sName = result1.getString(2);
                            sPatronymic = result1.getString(3);
                        }
                        String fullName = sSurname + " " + sName + " " + sPatronymic;
                        studName.setText(fullName);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        String sql2 =
                                "select id_lab from results where id_student = ?";
                        PreparedStatement prSt = con.prepareStatement(sql2);
                        prSt.setInt(1, idStud);
                        ResultSet result1 = prSt.executeQuery();

                        int idLab = 0;
                        while(result1.next()) {
                            idLab = result1.getInt(1);
                        }
                        if (idLab == 0) {
                            labName.setText("");
                            labName.setText("Цей учень не зробив жодної роботи");
                        } else if (idLab == 71) {
                            labName.setText("");
                            labName.setText("'Сила тертя, пружності, тяжіння'");
                            getStudAnswers(idStud, idLab);
                        } else if (idLab == 81) {
                            labName.setText("");
                            labName.setText("'Теплові явища'");
                            getStudAnswers(idStud, idLab);
                        } else {
                            labName.setText("");
                            labName.setText("'Світло. Хвилі. Сила всесвітнього тяжіння'");
                            getStudAnswers(idStud, idLab);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void getStudAnswers(int idStud, int idLab) {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "select task1_stAnswear, task2_stAnswear, task3_stAnswear, task4_stAnswear, task5_stAnswear, task6_stAnswear from results where id_student = ? && id_lab = ?";
            PreparedStatement prSt = con.prepareStatement(sql);
            prSt.setInt(1, idStud);
            prSt.setInt(2, idLab);
            ResultSet result = prSt.executeQuery();

            String studAns1 = "", studAns2 = "", studAns3  = "", studAns4 = "", studAns5 = "", studAns6 = "";
            while (result.next()) {
                studAns1 = result.getString(1);
                studAns2 = result.getString(2);
                studAns3 = result.getString(3);
                studAns4 = result.getString(4);
                studAns5 = result.getString(5);
                studAns6 = result.getString(6);
            }
            stAns1.setText(studAns1); stAns2.setText(studAns2); stAns3.setText(studAns3);
            stAns4.setText(studAns4); stAns5.setText(studAns5); stAns6.setText(studAns6);
            idStudent = idStud;
            getRightAnswers(idLab);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void getRightAnswers(int idLab) {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "select task1_correctAnswear, task2_correctAnswear, task3_correctAnswear, task4_correctAnswear, task5_correctAnswear, task6_correctAnswear from labs where id_lab = ?";
            PreparedStatement prSt = con.prepareStatement(sql);
            prSt.setInt(1, idLab);
            ResultSet result = prSt.executeQuery();
            String corrAns1 = "", corrAns2 = "", corrAns3  = "", corrAns4 = "", corrAns5 = "", corrAns6 = "";
            while (result.next()) {
                corrAns1 = result.getString(1);
                corrAns2 = result.getString(2);
                corrAns3 = result.getString(3);
                corrAns4 = result.getString(4);
                corrAns5 = result.getString(5);
                corrAns6 = result.getString(6);
            }
            ans1.setText(corrAns1);  ans2.setText(corrAns2);  ans3.setText(corrAns3);
            ans4.setText(corrAns4);  ans5.setText(corrAns5);  ans6.setText(corrAns6);
            idLabWork = idLab;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void calcMark() {
        int mark1 = -1, mark2 = -1, mark3 = -1, mark4 = -1, mark5 = -1, mark6 = -1;
        try {
            mark1 = Integer.parseInt(ans1Mark.getText());
            mark2 = Integer.parseInt(ans2Mark.getText());
            mark3 = Integer.parseInt(ans3Mark.getText());
            mark4 = Integer.parseInt(ans4Mark.getText());
            mark5 = Integer.parseInt(ans5Mark.getText());
            mark6 = Integer.parseInt(ans6Mark.getText());
        } catch (Exception e) {
            errorLabel1.setText("");
            errorLabel1.setText("Ви заповнили поля з оцінками неправильними даними");
        }

        if (mark1 <= -1 || mark2 <= -1 || mark3 <= -1 || mark4 <= -1 || mark5 <= -1 || mark6 <= -1) {
            errorLabel1.setText("");
            errorLabel1.setText("Ви заповнили поля з оцінками неправильними даними");
        } else if (mark1 > 1 || mark2 > 1 || mark3 > 2 || mark4 > 2 || mark5 > 3 || mark6 > 3) {
            errorLabel1.setText("");
            errorLabel1.setText("Ви поставили занадто великий бал на одну або декілька задач");
        } else {
            errorLabel1.setText("");
            int totalMark = mark1 + mark2 + mark3 + mark4 + mark5 + mark6;
            totMark = totalMark;
            studMark.setText("");
            studMark.setText(String.valueOf(totalMark));
        }
    }

    @FXML
    private void saveMark() {
        int idStud = getIdStudent();
        int idLab = getIdLabWork();
        int totalMark = getTotMark();
        System.out.println("ID Student = "+ idStud);
        System.out.println("ID Lab = "+ idLab);
        System.out.println("Total Mark = "+ totalMark);

        String stSurname = surnameStud.getText();
        String stName = nameStud.getText();

        String nameLab = "";
        if (idLab == 71) {
            nameLab = "Сила тертя, пружності, тяжіння";
        } else if (idLab == 81) {
            nameLab = "Теплові явища";
        } else {
            nameLab = "Світло. Хвилі. Сила всесвітнього тяжіння";
        }
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "insert into marks (id_student, student_surname, student_name, id_lab, lab_name, mark) values (?, ?, ?, ?, ?, ?);";
            PreparedStatement prSt = con.prepareStatement(sql);
            prSt.setInt(1, idStud);
            prSt.setString(2, stSurname);
            prSt.setString(3, stName);
            prSt.setInt(4, idLab);
            prSt.setString(5, nameLab);
            prSt.setInt(6, totalMark);
            prSt.executeUpdate();

            markSave.setText("");
            markSave.setText("Оцінку збережено до бази даних");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
