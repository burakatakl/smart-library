package com.library.service;

import com.library.model.Loan;
import com.library.model.User;
import com.library.model.Book;
import com.library.repository.LoanRepository;
import com.library.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveLoan() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        Loan loan = new Loan(user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(14));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);
        Loan savedLoan = loanService.saveLoan(loan);
        assertEquals(user, savedLoan.getUser());
        assertEquals(book, savedLoan.getBook());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    public void testGetLoanById() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        Loan loan = new Loan(user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(14));
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        Optional<Loan> foundLoan = loanService.getLoanById(1L);
        assertTrue(foundLoan.isPresent());
        assertEquals(user, foundLoan.get().getUser());
    }

    @Test
    public void testGetActiveLoans() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        Loan loan = new Loan(user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(14));
        when(loanRepository.findByReturnDateIsNull()).thenReturn(Arrays.asList(loan));
        List<Loan> loans = loanService.getActiveLoans();
        assertEquals(1, loans.size());
    }

    @Test
    public void testReturnLoan() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        Loan loan = new Loan(user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(14));
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        loanService.returnLoan(1L);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }
} 