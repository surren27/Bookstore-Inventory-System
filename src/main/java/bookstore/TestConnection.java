package bookstore;

import bookstore.database.DatabaseManager;

public class TestConnection {

    public static void main(String[] args) {

        DatabaseManager.connectDB();
    }
}
