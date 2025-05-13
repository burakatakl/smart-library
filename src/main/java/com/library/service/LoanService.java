package com.library.service;

import com.library.model.Loan;
import com.library.model.User;
import com.library.model.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan saveLoan(Loan loan);
    Optional<Loan> getLoanById(Long id);
    List<Loan> getAllLoans();
    List<Loan> getLoansByUser(User user);
    List<Loan> getLoansByBook(Book book);
    List<Loan> getActiveLoans();
    List<Loan> getOverdueLoans();
    List<Loan> getLoansBorrowedBetween(LocalDateTime start, LocalDateTime end);
    List<Loan> getLoansReturnedBetween(LocalDateTime start, LocalDateTime end);
    void returnLoan(Long loanId);
    void deleteLoan(Long id);
} 