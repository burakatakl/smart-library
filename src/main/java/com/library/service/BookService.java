package com.library.service;

import com.library.model.Book;
import com.library.model.BookStatus;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(Book book);
    Optional<Book> getBookById(Long id);
    Optional<Book> getBookByIsbn(String isbn);
    List<Book> getAllBooks();
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByGenre(String genre);
    List<Book> getBooksByStatus(BookStatus status);
    void deleteBook(Long id);
    Book updateBook(Long id, Book updatedBook);
} 