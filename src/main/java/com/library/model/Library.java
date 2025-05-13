package com.library.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The main class representing a library in the system.
 * This class manages all library operations including book management,
 * user management, and loan operations.
 */
public class Library {
    private String name;
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;
    private static final int MAX_LOANS_PER_USER = 3;
    private static final int LOAN_DURATION_DAYS = 14;
    private static final double FINE_PER_DAY = 1.0;

    /**
     * Constructs a new Library with the specified name.
     *
     * @param name The name of the library
     */
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Adds a new book to the library.
     *
     * @param book The book to be added
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the library.
     *
     * @param book The book to be removed
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Adds a new user to the library system.
     *
     * @param user The user to be added
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the library system.
     *
     * @param user The user to be removed
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Gets all books in the library.
     *
     * @return A list of all books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Gets all users registered in the library.
     *
     * @return A list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Gets all loans in the library system.
     *
     * @return A list of all loans
     */
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    /**
     * Searches for books by title.
     *
     * @param title The title to search for
     * @return A list of books matching the search criteria
     */
    public List<Book> searchBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for books by author.
     *
     * @param author The author to search for
     * @return A list of books matching the search criteria
     */
    public List<Book> searchBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Gets all books currently available for borrowing.
     *
     * @return A list of available books
     */
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Gets all books currently borrowed.
     *
     * @return A list of borrowed books
     */
    public List<Book> getBorrowedBooks() {
        return books.stream()
                .filter(book -> book.getStatus() == BookStatus.BORROWED)
                .collect(Collectors.toList());
    }

    /**
     * Gets all loans for a specific user.
     *
     * @param user The user whose loans are to be retrieved
     * @return A list of the user's loans
     */
    public List<Loan> getUserLoans(User user) {
        return loans.stream()
                .filter(loan -> loan.getUser().equals(user))
                .collect(Collectors.toList());
    }

    /**
     * Gets all active loans for a specific user.
     *
     * @param user The user whose active loans are to be retrieved
     * @return A list of the user's active loans
     */
    public List<Loan> getUserActiveLoans(User user) {
        return loans.stream()
                .filter(loan -> loan.getUser().equals(user) && !loan.isReturned())
                .collect(Collectors.toList());
    }

    /**
     * Gets all overdue loans for a specific user.
     *
     * @param user The user whose overdue loans are to be retrieved
     * @return A list of the user's overdue loans
     */
    public List<Loan> getUserOverdueLoans(User user) {
        return loans.stream()
                .filter(loan -> loan.getUser().equals(user) && loan.isOverdue())
                .collect(Collectors.toList());
    }

    /**
     * Borrows a book for a user.
     *
     * @param user The user borrowing the book
     * @param book The book to be borrowed
     * @return The loan object created for this borrowing
     * @throws IllegalStateException if the book cannot be borrowed
     */
    public Loan borrowBook(User user, Book book) {
        if (!user.isActive()) {
            throw new IllegalStateException("User is not active");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        if (getUserActiveLoans(user).size() >= MAX_LOANS_PER_USER) {
            throw new IllegalStateException("User has reached maximum number of loans");
        }

        if (user.hasOverdueLoans()) {
            throw new IllegalStateException("User has overdue loans");
        }

        Loan loan = new Loan(user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(LOAN_DURATION_DAYS));
        loans.add(loan);
        book.borrow();
        user.addLoan(loan);

        return loan;
    }

    /**
     * Returns a book that was borrowed.
     *
     * @param user The user returning the book
     * @param book The book to be returned
     * @throws IllegalStateException if the book cannot be returned
     */
    public void returnBook(User user, Book book) {
        Loan loan = loans.stream()
                .filter(l -> l.getUser().equals(user) && l.getBook().equals(book) && !l.isReturned())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active loan found for this book"));

        loan.returnBook();
        book.returnBook();
        user.returnLoan(loan);
    }

    /**
     * Gets the total number of books in the library.
     *
     * @return The total number of books
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Gets the number of available books.
     *
     * @return The number of available books
     */
    public int getAvailableBooksCount() {
        return (int) books.stream().filter(Book::isAvailable).count();
    }

    /**
     * Gets the number of borrowed books.
     *
     * @return The number of borrowed books
     */
    public int getBorrowedBooksCount() {
        return (int) books.stream().filter(book -> book.getStatus() == BookStatus.BORROWED).count();
    }

    /**
     * Gets the total number of users.
     *
     * @return The total number of users
     */
    public int getTotalUsers() {
        return users.size();
    }

    /**
     * Gets the number of active users.
     *
     * @return The number of active users
     */
    public int getActiveUsers() {
        return (int) users.stream().filter(User::isActive).count();
    }

    /**
     * Gets the total number of loans.
     *
     * @return The total number of loans
     */
    public int getTotalLoans() {
        return loans.size();
    }

    /**
     * Gets the number of active loans.
     *
     * @return The number of active loans
     */
    public int getActiveLoansCount() {
        return (int) loans.stream().filter(loan -> !loan.isReturned()).count();
    }

    /**
     * Gets the number of overdue loans.
     *
     * @return The number of overdue loans
     */
    public int getOverdueLoansCount() {
        return (int) loans.stream().filter(Loan::isOverdue).count();
    }

    /**
     * Calculates the total fines for all overdue loans.
     *
     * @return The total amount of fines
     */
    public double getTotalFines() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .mapToDouble(Loan::calculateFine)
                .sum();
    }
} 