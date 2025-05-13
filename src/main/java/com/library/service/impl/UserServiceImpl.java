package com.library.service.impl;

import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsersByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public List<User> searchUsersByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByIsActive(true);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setActive(updatedUser.isActive());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
} 