package com.library.repository;

import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides methods for database operations related to users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their unique user ID.
     *
     * @param userId The user ID to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByUserId(String userId);

    /**
     * Finds users by their email address.
     *
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds users by their first name containing the given string (case-insensitive).
     *
     * @param firstName The first name to search for
     * @return List of users matching the search criteria
     */
    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    /**
     * Finds users by their last name containing the given string (case-insensitive).
     *
     * @param lastName The last name to search for
     * @return List of users matching the search criteria
     */
    List<User> findByLastNameContainingIgnoreCase(String lastName);

    /**
     * Finds users by their active status.
     *
     * @param isActive The active status to search for
     * @return List of users matching the search criteria
     */
    List<User> findByIsActive(boolean isActive);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email The email to check
     * @return true if a user with the email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a user with the given user ID exists.
     *
     * @param userId The user ID to check
     * @return true if a user with the ID exists, false otherwise
     */
    boolean existsByUserId(String userId);
} 