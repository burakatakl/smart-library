package com.library.model;

/**
 * Enum representing the possible statuses of a book in the library system.
 * Each status indicates a different state of the book's availability and condition.
 */
public enum BookStatus {
    /**
     * The book is available and can be borrowed
     */
    AVAILABLE,

    /**
     * The book is currently borrowed by a user
     */
    BORROWED,

    /**
     * The book has been reserved by a user and is waiting to be borrowed
     */
    RESERVED,

    /**
     * The book has been reported as lost
     */
    LOST,

    /**
     * The book is currently under maintenance or repair
     */
    UNDER_MAINTENANCE
} 