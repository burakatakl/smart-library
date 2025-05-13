package com.library.service;

import com.library.model.Book;
import com.library.model.BookStatus;
import com.library.repository.BookRepository;
import com.library.service.impl.BookServiceImpl;
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

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book savedBook = bookService.saveBook(book);
        assertEquals("Test Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testGetBookById() {
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> foundBook = bookService.getBookById(1L);
        assertTrue(foundBook.isPresent());
        assertEquals("Test Book", foundBook.get().getTitle());
    }

    @Test
    public void testSearchBooksByTitle() {
        Book book1 = new Book("Test Book 1", "Author 1", "1234567890", 2023, "Genre 1");
        Book book2 = new Book("Test Book 2", "Author 2", "0987654321", 2023, "Genre 2");
        when(bookRepository.findByTitleContainingIgnoreCase("Test")).thenReturn(Arrays.asList(book1, book2));
        List<Book> books = bookService.searchBooksByTitle("Test");
        assertEquals(2, books.size());
    }

    @Test
    public void testGetBooksByStatus() {
        Book book = new Book("Test Book", "Test Author", "1234567890", 2023, "Test Genre");
        book.setStatus(BookStatus.AVAILABLE);
        when(bookRepository.findByStatus(BookStatus.AVAILABLE)).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getBooksByStatus(BookStatus.AVAILABLE);
        assertEquals(1, books.size());
        assertEquals(BookStatus.AVAILABLE, books.get(0).getStatus());
    }
} 