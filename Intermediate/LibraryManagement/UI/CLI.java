package com.library.ui;

import com.library.dao.BookDAO;
import com.library.dao.LoanDAO;
import com.library.dao.UserDAO;
import com.library.models.Book;
import com.library.models.User;
import com.library.service.RecommendationService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);
    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final LoanDAO loanDAO = new LoanDAO();
    private final RecommendationService recService = new RecommendationService();

    private User currentUser;

    public void start() {
        System.out.println("=== Library Management System ===");
        while (true) {
            if (currentUser == null) {
                authMenu();
            } else {
                mainMenu();
            }
        }
    }

    private void authMenu() {
        System.out.println("\n1. Register 2. Login 0. Exit");
        switch (scanner.nextLine()) {
            case "1" -> register();
            case "2" -> login();
            case "0" -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }

    private void register() {
        try {
            System.out.print("Username: ");
            String u = scanner.nextLine();
            System.out.print("Password: ");
            String p = scanner.nextLine();
            userDAO.register(new com.library.models.User(u, p));
            System.out.println("Registered! You can login now.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.print("Username: ");
            String u = scanner.nextLine();
            System.out.print("Password: ");
            String p = scanner.nextLine();
            currentUser = userDAO.login(u, p);
            if (currentUser == null) {
                System.out.println("Invalid credentials");
            } else {
                System.out.println("Welcome, " + currentUser.getUsername());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mainMenu() {
        System.out.println("\n1. List Books 2. Add Book 3. Borrow Book 4. Return Book 5. My Loans 6. Recommendations 7. Logout");
        switch (scanner.nextLine()) {
            case "1" -> listBooks();
            case "2" -> addBook();
            case "3" -> borrowBook();
            case "4" -> returnBook();
            case "5" -> myLoans();
            case "6" -> recommend();
            case "7" -> currentUser = null;
            default -> System.out.println("Invalid option");
        }
    }

    private void listBooks() {
        try {
            List<Book> books = bookDAO.getAll();
            books.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addBook() {
        try {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            Book b = new Book(title, author, isbn, desc);
            bookDAO.add(b);
            System.out.println("Added!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void borrowBook() {
        try {
            System.out.print("ISBN to borrow: ");
            String isbn = scanner.nextLine();
            Book b = bookDAO.findByIsbn(isbn);
            if (b == null) {
                System.out.println("Not found");
                return;
            }
            loanDAO.borrow(currentUser, b, 14);
            System.out.println("Borrowed!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        try {
            System.out.print("ISBN to return: ");
            String isbn = scanner.nextLine();
            Book b = bookDAO.findByIsbn(isbn);
            if (b == null) {
                System.out.println("Not found");
                return;
            }
            loanDAO.returnBook(currentUser, b);
            System.out.println("Returned!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void myLoans() {
        try {
            loanDAO.currentLoans(currentUser).forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void recommend() {
        try {
            recService.recommend(5).forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
