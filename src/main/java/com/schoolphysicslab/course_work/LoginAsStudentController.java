package com.schoolphysicslab.course_work;

import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;

public class LoginAsStudentController {
    @FXML
    private Pane studentPane;

    public String className;

   public String getClassName() {
       return className;
   }

    @FXML
    public Label classNameLabel;

    @FXML
    public Pane stLogInPane;

    @FXML
    public Pane stRegPane;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailFieldReg;

    @FXML
    private PasswordField passwordFieldReg;

    @FXML
    private TextField surnameReg;

    @FXML
    private TextField nameReg;

    @FXML
    private TextField patronymicReg;

    @FXML
    public Label errorLabel;

    @FXML
    public Label errorLabelReg;

    @FXML
    public ChoiceBox<String> classChoiceBox;

    @FXML
    private void returnToMainStage() throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();

        Stage studentStage = (Stage) studentPane.getScene().getWindow();
        studentStage.close();
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

        Stage studentStage = (Stage) stRegPane.getScene().getWindow();
        studentStage.close();
    }

    @FXML
    private void returnToMainStage2() throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        mainStage.setMaximized(true);
        mainStage.setTitle("School Physics Lab");
        mainStage.setScene(scene);
        mainStage.show();

        Stage studentStage = (Stage) stLogInPane.getScene().getWindow();
        studentStage.close();
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

            Stage studentLogInStage = (Stage) stLogInPane.getScene().getWindow();
            studentLogInStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void chooseClass7A() {
        className = "7 - A";
        studLogIn();
    }
    @FXML
    private void chooseClass7B() {
        className = "7 - Б";
        studLogIn();
    }
    @FXML
    private void chooseClass7C() {
        className = "7 - В";
        studLogIn();
    }
    @FXML
    private void chooseClass7D() {
        className = "7 - Г";
        studLogIn();
    }

    @FXML
    private void chooseClass8A() {
        className = "8 - A";
        studLogIn();
    }
    @FXML
    private void chooseClass8B() {
        className = "8 - Б";
        studLogIn();
    }
    @FXML
    private void chooseClass8C() {
        className = "8 - В";
        studLogIn();
    }
    @FXML
    private void chooseClass8D() {
        className = "8 - Г";
        studLogIn();
    }

    @FXML
    private void chooseClass9A() {
        className = "9 - A";
        studLogIn();
    }
    @FXML
    private void chooseClass9B() {
        className = "9 - Б";
        studLogIn();
    }
    @FXML
    private void chooseClass9C() {
        className = "9 - В";
        studLogIn();
    }
    @FXML
    private void chooseClass9D() {
        className = "9 - Г";
        studLogIn();
    }

    private void studLogIn() {
        try {
            Stage studLogInStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(LoginAsStudentController.class.getResource("studLogIn.fxml"));
            Parent root = fxmlLoader.load();
            LoginAsStudentController controller = fxmlLoader.getController();
            String classN = getClassName();
            controller.setClassNameLabel(classN);

            studLogInStage.setTitle("School Physics Lab: Вхід учня");
            studLogInStage.setScene(new Scene(root));
            studLogInStage.setMaximized(true);
            studLogInStage.show();

            Stage studentStage = (Stage) studentPane.getScene().getWindow();
            studentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setClassNameLabel(String className) {
        if (classNameLabel != null) {
            classNameLabel.setText(className);
        }
    }

    @FXML
    private void register() {
        try {
            Stage studRegStage = new Stage();
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("studRegistration.fxml"));
            //Parent root = FXMLLoader.load(LoginAsStudentController.class.getResource("studRegistration.fxml"));
            //LoginAsStudentController controller = fxmlLoader.getController();
            Parent root = fxmlLoader.load();

            studRegStage.setTitle("School Physics Lab: Реєстрація учня");
            studRegStage.setScene(new Scene(root));
            studRegStage.setMaximized(true);
            studRegStage.show();
            Stage studentStage = (Stage) stLogInPane.getScene().getWindow();
            studentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registerStudent() {
        String selectedClass = classChoiceBox.getValue();
        String email = emailFieldReg.getText();
        String password = passwordFieldReg.getText();
        String surname = surnameReg.getText();
        String name = nameReg.getText();
        String patronymic = patronymicReg.getText();

        if (password.length() < 8) {
            if (errorLabelReg != null) {
                errorLabelReg.setText("Пароль має бути 8 симв. або більше");
            }
        } else {
            System.out.println("Selected Class: " + selectedClass);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Surname: " + surname);
            System.out.println("Name: " + name);
            System.out.println("Patronymic: " + patronymic);

            // Перевірка під час реєстрації, чи такий акаунт вже існує
            try {
                DBConnector connector = new DBConnector();
                connector.makeDBConnection();
                Connection con = connector.getCon();
                String sql =
                        "select student_surname, student_name, student_patronymic from students where student_email = ? && student_password = ? && student_class = ?;";
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setString(1, email);
                prst.setString(2, password);
                prst.setString(3, selectedClass);
                ResultSet result = prst.executeQuery();

                String stSurname = "";
                String stName = "";
                String stPatronymic = "";
                while(result.next()) {
                    stSurname = result.getString(1);
                    stName = result.getString(2);
                    stPatronymic = result.getString(3);
                }
                if (!stSurname.equals("") && !stName.equals("") && !stPatronymic.equals("")) {
                    System.out.println("Такий акаунт вже існує!");
                    if (errorLabelReg != null) {
                        errorLabelReg.setText("Такий акаунт вже існує! Увійдіть у нього");
                    }
                } else { // Якщо такий акаунт ще не існує
                    if (selectedClass.equals("") || email.equals("") || password.equals("") || surname.equals("") || name.equals("") || patronymic.equals("")) {
                        errorLabelReg.setText("Оберіть свій клас та заповніть всі поля");
                    } else {
                        try {
                            String sql1 =
                                    "insert into students (student_surname, student_name, student_patronymic, student_class, student_email, student_password) values (?, ?, ?, ?, ?, ?)";
                            PreparedStatement prst1 = con.prepareStatement(sql1);
                            prst1.setString(1, surname);
                            prst1.setString(2, name);
                            prst1.setString(3, patronymic);
                            prst1.setString(4, selectedClass);
                            prst1.setString(5, email);
                            prst1.setString(6, password);
                            prst1.executeUpdate();
                            LabController.openChooseLabStage(selectedClass, email);
                            if (stLogInPane != null) {
                                Stage studentStage = (Stage) stLogInPane.getScene().getWindow();
                                studentStage.close();
                            }
                            if (stRegPane != null) {
                                Stage studentStage = (Stage) stRegPane.getScene().getWindow();
                                studentStage.close();
                            }
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
    private void logIn() {
        try {
            DBConnector connector = new DBConnector();
            connector.makeDBConnection();
            Connection con = connector.getCon();
            String email = emailField.getText();
            String password = passwordField.getText();
            String classN = classNameLabel.getText();
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Class: " + classN);

            String sql =
                    "select student_surname, student_name, student_patronymic from students where student_email = ? && student_password = ? && student_class = ?;";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, email);
            prst.setString(2, password);
            prst.setString(3, classN);
            ResultSet result = prst.executeQuery();

            String stEmail = "";
            String stPassword = "";
            String stClass = "";
            while(result.next()) {
                stEmail = result.getString(1);
                stPassword = result.getString(2);
                stClass = result.getString(3);
            }
            if (stEmail == "" && stPassword == "") {
                System.out.println("Студента з поштою " + email + " та паролем " + password + " не знайдено");
                if (errorLabel != null) {
                    errorLabel.setText("Неправильна пошта, пароль або клас");
                }
            }
            else {
                errorLabel.setText("Вхід здійснено успішно!");
                LabController.openChooseLabStage(classN, email);
                if (stLogInPane != null) {
                    Stage studentStage = (Stage) stLogInPane.getScene().getWindow();
                    studentStage.close();
                }
                if (stRegPane != null) {
                    Stage studentStage = (Stage) stRegPane.getScene().getWindow();
                    studentStage.close();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
