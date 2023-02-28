package fr.epsi.b3;


import java.sql.*;
import java.util.ResourceBundle;

public class Main {
    private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PWD;

    static {
        System.out.println("Class  in loading");
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        DB_URL = bundle.getString("db.url");
        DB_LOGIN = bundle.getString("db.login");
        DB_PWD= bundle.getString("db.password");
    }

    public static void tp1() {
        try (Connection connection= DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
             Statement statement = connection.createStatement();) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

    }
}