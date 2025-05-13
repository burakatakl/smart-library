package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Represents a book in the library system.
 * This class encapsulates all the essential information about a book
 * and provides methods to manage its status and availability.
 */
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    @Column(name = "last_borrowed_date")
    private LocalDateTime lastBorrowedDate;

    @Column(name = "last_returned_date")
    private LocalDateTime lastReturnedDate;

    /**
     * Default constructor required by JPA.
     */
    public Book() {
    }

    /**
     * Constructs a new Book with the specified details.
     *
     * @param title The title of the book
     * @param author The author of the book
     * @param isbn The ISBN of the book
     * @param publicationYear The year the book was published
     * @param genre The genre of the book
     */
    public Book(String title, String author, String isbn, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.status = BookStatus.AVAILABLE;
    }

    /**
     * Checks if the book is available for borrowing.
     *
     * @return true if the book is available, false otherwise
     */
    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    /**
     * Marks the book as borrowed.
     * Updates the last borrowed date to the current time.
     */
    public void borrow() {
        if (!isAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }
        status = BookStatus.BORROWED;
        lastBorrowedDate = LocalDateTime.now();
    }

    /**
     * Marks the book as returned.
     * Updates the last returned date to the current time.
     */
    public void returnBook() {
        if (status != BookStatus.BORROWED) {
            throw new IllegalStateException("Book is not currently borrowed");
        }
        status = BookStatus.AVAILABLE;
        lastReturnedDate = LocalDateTime.now();
    }

    /**
     * Marks the book as reserved.
     */
    public void reserve() {
        if (!isAvailable()) {
            throw new IllegalStateException("Book is not available for reservation");
        }
        status = BookStatus.RESERVED;
    }

    /**
     * Marks the book as lost.
     */
    public void markAsLost() {
        status = BookStatus.LOST;
    }

    /**
     * Marks the book as under maintenance.
     */
    public void markAsUnderMaintenance() {
        status = BookStatus.UNDER_MAINTENANCE;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                '}';
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastBorrowedDate() {
        return lastBorrowedDate;
    }

    public void setLastBorrowedDate(LocalDateTime lastBorrowedDate) {
        this.lastBorrowedDate = lastBorrowedDate;
    }

    public LocalDateTime getLastReturnedDate() {
        return lastReturnedDate;
    }

    public void setLastReturnedDate(LocalDateTime lastReturnedDate) {
        this.lastReturnedDate = lastReturnedDate;
    }
} 