package com.test.creditsuisse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloGradle {

    public static void main(String[] args) throws SQLException {
        Db db = new Db();
        Connection connection = db.getDbConnection();
        ResultSet aaa = connection.createStatement().executeQuery("SELECT * FROM aaa");
        while (aaa.next()) {
            int id = aaa.getInt("id");
            String name = aaa.getString("name");
            System.out.println(id + " " + name);
        }
    }

    public int tescik() throws SQLException {

        return 3;
    }
}