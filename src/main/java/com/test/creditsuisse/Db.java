package com.test.creditsuisse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private Connection connection = null;

    public Db() {
    }

    public Connection getDbConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:hsqldb:file:c:\\Users\\Piotrek\\TaxusIt\\workspace_backend\\CreditSuisse\\db\\creditsuissedb", "admin", "password");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return connection;
        }
        return connection;
    }

    public void closeDbConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
