package com.library.config;

import com.library.model.Book;
import com.library.model.User;
import com.library.model.Loan;
import com.library.model.BookStatus;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.repository.LoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample Books
        Book book1 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "9780140449136", 1866, "Novel");
        Book book2 = new Book("1984", "George Orwell", "9780451524935", 1949, "Dystopian");
        Book book3 = new Book("Les Misérables", "Victor Hugo", "9780451419439", 1862, "Historical");
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // Sample Users
        User user1 = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        User user2 = new User("U002", "Jane", "Doe", "jane.doe@example.com", "555-5678");
        userRepository.save(user1);
        userRepository.save(user2);

        // Sample Loans
        Loan loan1 = new Loan(user1, book1, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(12));
        Loan loan2 = new Loan(user2, book2, LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(9));
        loanRepository.save(loan1);
        loanRepository.save(loan2);

        // Update book status
        book1.setStatus(BookStatus.BORROWED);
        book2.setStatus(BookStatus.BORROWED);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
} 