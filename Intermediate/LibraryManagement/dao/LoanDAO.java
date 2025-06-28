package com.library.dao;

import com.library.Database;
import com.library.models.Book;
import com.library.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public void borrow(User user, Book book, int days) throws SQLException {
        String sql = "INSERT INTO loans(user_id, book_id, loan_date, due_date) VALUES(?,?,?,?)";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, book.getId());
            LocalDate now = LocalDate.now();
            ps.setString(3, now.toString());
            ps.setString(4, now.plusDays(days).toString());
            ps.executeUpdate();
        }
    }

    public List<Book> currentLoans(User user) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.* FROM books b JOIN loans l ON b.id = l.book_id WHERE l.user_id = ? AND l.returned = 0";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book b = new Book(rs.getString("title"), rs.getString("author"),
                        rs.getString("isbn"), rs.getString("description"));
                b.setId(rs.getInt("id"));
                list.add(b);
            }
        }
        return list;
    }

    public void returnBook(User user, Book book) throws SQLException {
        String sql = "UPDATE loans SET returned = 1 WHERE user_id = ? AND book_id = ? AND returned = 0";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, book.getId());
            ps.executeUpdate();
        }
    }
}
