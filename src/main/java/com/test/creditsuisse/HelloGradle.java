package com.test.creditsuisse;

import org.hsqldb.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloGradle {

    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:hsqldb:file:c:\\Users\\Piotrek\\TaxusIt\\workspace_backend\\CreditSuisse\\db\\creditsuissedb", "admin", "password");
        ResultSet aaa = c.createStatement().executeQuery("SELECT * FROM aaa");
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