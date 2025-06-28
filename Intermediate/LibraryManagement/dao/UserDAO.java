package com.library.dao;

import com.library.Database;
import com.library.models.User;

import java.sql.*;

public class UserDAO {

    public void register(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }
    }

    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("username"), rs.getString("password"));
                u.setId(rs.getInt("id"));
                return u;
            }
        }
        return null;
    }
}
