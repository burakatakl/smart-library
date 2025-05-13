package com.library.service;

import com.library.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUserId(String userId);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> searchUsersByFirstName(String firstName);
    List<User> searchUsersByLastName(String lastName);
    List<User> getActiveUsers();
    void deleteUser(Long id);
    User updateUser(Long id, User updatedUser);
} 