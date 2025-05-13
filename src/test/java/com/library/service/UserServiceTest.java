package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals("John", savedUser.getFirstName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("John", foundUser.get().getFirstName());
    }

    @Test
    public void testSearchUsersByFirstName() {
        User user1 = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        User user2 = new User("U002", "John", "Doe", "john.doe@example.com", "555-5678");
        when(userRepository.findByFirstNameContainingIgnoreCase("John")).thenReturn(Arrays.asList(user1, user2));
        List<User> users = userService.searchUsersByFirstName("John");
        assertEquals(2, users.size());
    }

    @Test
    public void testGetActiveUsers() {
        User user = new User("U001", "John", "Smith", "john.smith@example.com", "555-1234");
        user.setActive(true);
        when(userRepository.findByIsActive(true)).thenReturn(Arrays.asList(user));
        List<User> users = userService.getActiveUsers();
        assertEquals(1, users.size());
        assertTrue(users.get(0).isActive());
    }
} 