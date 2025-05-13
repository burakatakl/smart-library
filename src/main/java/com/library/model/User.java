package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the library system.
 * This class manages user information and their borrowing history.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> currentLoans = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loanHistory = new ArrayList<>();

    /**
     * Default constructor required by JPA.
     */
    public User() {
    }

    /**
     * Constructs a new User with the specified details.
     *
     * @param userId The unique identifier for the user
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email address
     * @param phone The user's phone number
     */
    public User(String userId, String firstName, String lastName, String email, String phone) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.isActive = true;
        this.registrationDate = LocalDateTime.now();
    }

    /**
     * Gets the user's full name.
     *
     * @return The user's full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Adds a new loan to the user's current loans.
     *
     * @param loan The loan to be added
     */
    public void addLoan(Loan loan) {
        currentLoans.add(loan);
    }

    /**
     * Returns a loan and moves it to the loan history.
     *
     * @param loan The loan to be returned
     */
    public void returnLoan(Loan loan) {
        currentLoans.remove(loan);
        loanHistory.add(loan);
    }

    /**
     * Checks if the user has any overdue loans.
     *
     * @return true if the user has overdue loans, false otherwise
     */
    public boolean hasOverdueLoans() {
        return currentLoans.stream().anyMatch(Loan::isOverdue);
    }

    /**
     * Calculates the total fines for all overdue loans.
     *
     * @return The total amount of fines
     */
    public double calculateTotalFines() {
        return currentLoans.stream()
                .filter(Loan::isOverdue)
                .mapToDouble(Loan::calculateFine)
                .sum();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Loan> getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(List<Loan> currentLoans) {
        this.currentLoans = currentLoans;
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    public void setLoanHistory(List<Loan> loanHistory) {
        this.loanHistory = loanHistory;
    }
} 