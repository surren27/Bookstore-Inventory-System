package bookstore.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bookstore_db";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connectDB() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully");

            return conn;

        } catch (Exception e) {

            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }

        return null;
    }
}
