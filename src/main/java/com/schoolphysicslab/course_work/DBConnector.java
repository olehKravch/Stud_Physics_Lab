package com.schoolphysicslab.course_work;

import java.sql.*;

//у цьому класі реалізується підключення до БД
public class DBConnector {

    private static final String url = "jdbc:mysql:///SchoolPhysicsLab_db?serverTimezone=Europe/Kyiv";
    private static final String user = "root";
    private static final String password = "kravchenko_ead222";

    private Connection con;

    public void makeDBConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Підключено успішно!");
        } catch (SQLException ex) {
            System.out.println("Підключитися не вдалося...");
            ex.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }
}

