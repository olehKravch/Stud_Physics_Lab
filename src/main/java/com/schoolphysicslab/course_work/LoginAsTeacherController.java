package com.schoolphysicslab.course_work;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAsTeacherController {
    @FXML
    private Pane teachLogInPane, teachRegPane;

    @FXML
    private TextField emailField, surnameReg, nameReg, patronymicReg, emailReg;

    @FXML
    private PasswordField passwordField, passwordFieldReg;

    @FXML
    private Label errorLabel, errorLabelReg;

    @FXML
    private void returnToMainStage() throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();

        if (teachLogInPane != null) {
            Stage studentStage = (Stage) teachLogInPane.getScene().getWindow();
            studentStage.close();
        } else {
            Stage studentStage = (Stage) teachRegPane.getScene().getWindow();
            studentStage.close();
        }

    }

    @FXML
    private void openTeacherRegisterStage() {
        try {
            Stage teachRegStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("teacherRegistration.fxml"));
            Parent root = fxmlLoader.load();

            teachRegStage.setTitle("School Physics Lab: Реєстрація вчителя");
            teachRegStage.setScene(new Scene(root));
            teachRegStage.setMaximized(true);
            teachRegStage.show();
            Stage studentStage = (Stage) teachLogInPane.getScene().getWindow();
            studentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registerTeacher() {
        String surname = surnameReg.getText();
        String name = nameReg.getText();
        String patronymic = patronymicReg.getText();
        String email = emailReg.getText();
        String password = passwordFieldReg.getText();

        if (password.length() < 8) {
            if (errorLabelReg != null) {
                errorLabelReg.setText("Пароль має бути 8 симв. або більше");
            }
        } else {
            try { // Перевірка під час реєстрації, чи такий акаунт вже існує
                DBConnector connector = new DBConnector();
                connector.makeDBConnection();
                Connection con = connector.getCon();
                String sql =
                        "select teacher_surname, teacher_name, teacher_patronymic from teachers where teacher_email = ? && teacher_password = ?;";
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setString(1, email);
                prst.setString(2, password);

                ResultSet result = prst.executeQuery();

                String tSurname = "";
                String tName = "";
                String tPatronymic = "";
                while(result.next()) {
                    tSurname = result.getString(1);
                    tName = result.getString(2);
                    tPatronymic = result.getString(3);
                }
                if (!tSurname.equals("") && !tName.equals("") && !tPatronymic.equals("")) {
                    System.out.println("Такий акаунт вже існує!");
                    if (errorLabelReg != null) {
                        errorLabelReg.setText("Такий акаунт вже існує! Увійдіть у нього");
                    }
                } else { // Якщо такий акаунт ще не існує
                    if (email.equals("") || password.equals("") || surname.equals("") || name.equals("") || patronymic.equals("")) {
                        errorLabelReg.setText("Оберіть свій клас та заповніть всі поля");
                    } else {
                        try {
                            String sql1 =
                                    "insert into teachers (teacher_surname, teacher_name, teacher_patronymic, teacher_email, teacher_password) values (?, ?, ?, ?, ?);";
                            PreparedStatement prst1 = con.prepareStatement(sql1);
                            prst1.setString(1, surname);
                            prst1.setString(2, name);
                            prst1.setString(3, patronymic);
                            prst1.setString(4, email);
                            prst1.setString(5, password);
                            prst1.executeUpdate();

                            CheckLabController.openCheckLabStage(tSurname, tName, tPatronymic, email);
                            Stage studentStage = (Stage) teachRegPane.getScene().getWindow();
                            studentStage.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void loginAsTeacher() {
        String email = emailField.getText();
        String password = passwordField.getText();
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String sql =
                    "select teacher_surname, teacher_name, teacher_patronymic from teachers where teacher_email = ? && teacher_password = ?;";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, email);
            prst.setString(2, password);
            ResultSet result = prst.executeQuery();

            String tSurname = "";
            String tName = "";
            String tPatronymic = "";
            while(result.next()) {
                tSurname = result.getString(1);
                tName = result.getString(2);
                tPatronymic = result.getString(3);
            }
            if (tSurname.equals("") && tName.equals("") && tPatronymic.equals("")) {
                System.out.println("Вчителя з поштою " + email + " та паролем " + password + " не знайдено");
                if (errorLabel != null) {
                    errorLabel.setText("Неправильна пошта або пароль");
                }
            } else {
                if (errorLabel != null) {
                    errorLabel.setText("Вхід здійснено успішно");
                    CheckLabController.openCheckLabStage(tSurname, tName, tPatronymic, email);
                    Stage studentStage = (Stage) teachLogInPane.getScene().getWindow();
                    studentStage.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
