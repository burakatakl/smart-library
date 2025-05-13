package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Represents a loan transaction in the library system.
 * This class tracks the borrowing and returning of books, including due dates and fine calculations.
 */
@Data
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "fine_per_day", nullable = false)
    private static final double FINE_PER_DAY = 1.0;

    /**
     * Default constructor required by JPA.
     */
    public Loan() {
    }

    /**
     * Constructs a new Loan with the specified details.
     *
     * @param user The user borrowing the book
     * @param book The book being borrowed
     * @param borrowDate The date the book was borrowed
     * @param dueDate The date the book is due to be returned
     */
    public Loan(User user, Book book, LocalDateTime borrowDate, LocalDateTime dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    /**
     * Checks if the book has been returned.
     *
     * @return true if the book has been returned, false otherwise
     */
    public boolean isReturned() {
        return returnDate != null;
    }

    /**
     * Marks the book as returned and sets the return date.
     */
    public void returnBook() {
        this.returnDate = LocalDateTime.now();
    }

    /**
     * Checks if the loan is overdue.
     *
     * @return true if the loan is overdue, false otherwise
     */
    public boolean isOverdue() {
        LocalDateTime checkDate = returnDate != null ? returnDate : LocalDateTime.now();
        return checkDate.isAfter(dueDate);
    }

    /**
     * Calculates the fine for an overdue loan.
     *
     * @return The amount of the fine
     */
    public double calculateFine() {
        if (!isOverdue()) {
            return 0.0;
        }
        LocalDateTime checkDate = returnDate != null ? returnDate : LocalDateTime.now();
        long daysOverdue = java.time.Duration.between(dueDate, checkDate).toDays();
        return daysOverdue * FINE_PER_DAY;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
} 