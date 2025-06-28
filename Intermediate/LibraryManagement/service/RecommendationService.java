package com.library.service;

import com.library.Database;
import com.library.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendationService {

    public List<Book> recommend(int limit) throws SQLException {
        List<Book> all = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement st = Database.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("description"));
                b.setId(rs.getInt("id"));
                all.add(b);
            }
        }
        // simple random pick
        List<Book> out = new ArrayList<>();
        Random rng = new Random();
        while (out.size() < limit && !all.isEmpty()) {
            out.add(all.remove(rng.nextInt(all.size())));
        }
        return out;
    }
}
