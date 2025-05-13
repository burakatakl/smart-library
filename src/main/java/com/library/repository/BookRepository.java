package com.library.repository;

import com.library.model.Book;
import com.library.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Book entity.
 * Provides methods for database operations related to books.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Finds books by their title containing the given string (case-insensitive).
     *
     * @param title The title to search for
     * @return List of books matching the search criteria
     */
    List<Book> findByTitleContainingIgnoreCase(String title);

    /**
     * Finds books by their author containing the given string (case-insensitive).
     *
     * @param author The author to search for
     * @return List of books matching the search criteria
     */
    List<Book> findByAuthorContainingIgnoreCase(String author);

    /**
     * Finds books by their ISBN.
     *
     * @param isbn The ISBN to search for
     * @return List of books matching the search criteria
     */
    List<Book> findByIsbn(String isbn);

    /**
     * Finds books by their status.
     *
     * @param status The status to search for
     * @return List of books matching the search criteria
     */
    List<Book> findByStatus(BookStatus status);

    /**
     * Finds books by their genre containing the given string (case-insensitive).
     *
     * @param genre The genre to search for
     * @return List of books matching the search criteria
     */
    List<Book> findByGenreContainingIgnoreCase(String genre);
} 