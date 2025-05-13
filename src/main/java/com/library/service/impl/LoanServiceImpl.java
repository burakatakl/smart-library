package com.library.service.impl;

import com.library.model.Loan;
import com.library.model.User;
import com.library.model.Book;
import com.library.repository.LoanRepository;
import com.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getLoansByUser(User user) {
        return loanRepository.findByUser(user);
    }

    @Override
    public List<Loan> getLoansByBook(Book book) {
        return loanRepository.findByBook(book);
    }

    @Override
    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    @Override
    public List<Loan> getOverdueLoans() {
        return loanRepository.findByReturnDateIsNullAndDueDateBefore(LocalDateTime.now());
    }

    @Override
    public List<Loan> getLoansBorrowedBetween(LocalDateTime start, LocalDateTime end) {
        return loanRepository.findByBorrowDateBetween(start, end);
    }

    @Override
    public List<Loan> getLoansReturnedBetween(LocalDateTime start, LocalDateTime end) {
        return loanRepository.findByReturnDateBetween(start, end);
    }

    @Override
    public void returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + loanId));
        loan.returnBook();
        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
} 