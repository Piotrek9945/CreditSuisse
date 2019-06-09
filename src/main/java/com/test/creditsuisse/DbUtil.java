package com.test.creditsuisse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public DbUtil() {
    }

    public Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:c:\\Users\\Piotrek\\TaxusIt\\workspace_backend\\CreditSuisse\\db\\creditsuissedb", "admin", "password");
    }

}
