package bookstore.controller;

import bookstore.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {

    public String loginUser(String username,
                            String password) {

        String role = null;

        String sql =
                "SELECT role FROM users WHERE email=? AND password=?";

        try {

            Connection conn =
                    DatabaseManager.connectDB();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1, username);

            pst.setString(2, password);

            ResultSet rs =
                    pst.executeQuery();

            if(rs.next()) {

                role = rs.getString("role");
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return role;
    }
}