package com.library.repository;

import com.library.model.Loan;
import com.library.model.User;
import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Loan entity.
 * Provides methods for database operations related to loans.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    /**
     * Finds all loans for a specific user.
     *
     * @param user The user to search for
     * @return List of loans for the user
     */
    List<Loan> findByUser(User user);

    /**
     * Finds all loans for a specific book.
     *
     * @param book The book to search for
     * @return List of loans for the book
     */
    List<Loan> findByBook(Book book);

    /**
     * Finds all active loans (where return date is null).
     *
     * @return List of active loans
     */
    List<Loan> findByReturnDateIsNull();

    /**
     * Finds all overdue loans (where return date is null and due date is before current time).
     *
     * @param currentTime The current time to check against
     * @return List of overdue loans
     */
    List<Loan> findByReturnDateIsNullAndDueDateBefore(LocalDateTime currentTime);

    /**
     * Finds all loans that were borrowed between two dates.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return List of loans borrowed between the dates
     */
    List<Loan> findByBorrowDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Finds all loans that were returned between two dates.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return List of loans returned between the dates
     */
    List<Loan> findByReturnDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Counts the number of active loans for a specific user.
     *
     * @param user The user to count loans for
     * @return The number of active loans
     */
    long countByUserAndReturnDateIsNull(User user);
} 