package com.library.dao;

import com.library.Database;
import com.library.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void add(Book book) throws SQLException {
        String sql = "INSERT INTO books(title, author, isbn, description) VALUES(?,?,?,?)";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getDescription());
            ps.executeUpdate();
        }
    }

    public List<Book> getAll() throws SQLException {
        List<Book> list = new ArrayList<>();
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
                list.add(b);
            }
        }
        return list;
    }

    public Book findByIsbn(String isbn) throws SQLException {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("description"));
                b.setId(rs.getInt("id"));
                return b;
            }
        }
        return null;
    }
}
