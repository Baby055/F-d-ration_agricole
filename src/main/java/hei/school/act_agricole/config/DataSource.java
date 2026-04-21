package hei.school.act_agricole.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class DataSource {
    private static final String URL = "jdbc:h2:mem:federationdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "meilleur";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
