package com.library;

import com.library.model.*;

/**
 * Main class that demonstrates the core functionality of the Library Management System
 * through a comprehensive test scenario.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new library
        Library library = new Library("Central Library");
        System.out.println("Library created: " + library.getName());

        // Add some books
        Book book1 = new Book("Crime and Punishment", "Fyodor Dostoyevski", "9780140449136", 1866, "Novel");
        Book book2 = new Book("1984", "George Orwell", "9780451524935", 1949, "Dystopian");
        Book book3 = new Book("Les Misérables", "Victor Hugo", "9780140444308", 1862, "Historical Fiction");
        Book book4 = new Book("Don Quixote", "Miguel de Cervantes", "9780060934347", 1605, "Novel");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        System.out.println("\nBooks added to the library:");

        // Add some users
        User user1 = new User("U001", "John", "Smith", "john.smith@email.com", "555-0101");
        User user2 = new User("U002", "Jane", "Doe", "jane.doe@email.com", "555-0102");
        User user3 = new User("U003", "Michael", "Johnson", "michael.johnson@email.com", "555-0103");

        library.addUser(user1);
        library.addUser(user2);
        library.addUser(user3);
        System.out.println("\nUsers registered:");

        // Test book borrowing
        System.out.println("\nTesting book borrowing:");
        try {
            Loan loan1 = library.borrowBook(user1, book1);
            System.out.println(user1.getFullName() + " borrowed " + book1.getTitle());
            
            Loan loan2 = library.borrowBook(user1, book2);
            System.out.println(user1.getFullName() + " borrowed " + book2.getTitle());
            
            Loan loan3 = library.borrowBook(user2, book3);
            System.out.println(user2.getFullName() + " borrowed " + book3.getTitle());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test book search
        System.out.println("\nSearching for books with 'Novel' in title:");
        library.searchBooksByTitle("Novel").forEach(book -> 
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor())
        );

        // List borrowed books for a user
        System.out.println("\nBooks borrowed by " + user1.getFullName() + ":");
        library.getUserActiveLoans(user1).forEach(loan ->
            System.out.println("- " + loan.getBook().getTitle())
        );

        // Test book return
        System.out.println("\nTesting book return:");
        try {
            library.returnBook(user1, book1);
            System.out.println(user1.getFullName() + " returned " + book1.getTitle());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display library statistics
        System.out.println("\nLibrary Statistics:");
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Available Books: " + library.getAvailableBooksCount());
        System.out.println("Borrowed Books: " + library.getBorrowedBooksCount());
        System.out.println("Total Users: " + library.getTotalUsers());
        System.out.println("Active Users: " + library.getActiveUsers());
        System.out.println("Total Loans: " + library.getTotalLoans());
        System.out.println("Active Loans: " + library.getActiveLoansCount());
        System.out.println("Overdue Loans: " + library.getOverdueLoansCount());
        System.out.println("Total Fines: $" + library.getTotalFines());
    }
} 