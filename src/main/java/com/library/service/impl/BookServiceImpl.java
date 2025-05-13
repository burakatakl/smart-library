package com.library.service.impl;

import com.library.model.Book;
import com.library.model.BookStatus;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        List<Book> books = bookRepository.findByIsbn(isbn);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public List<Book> searchBooksByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    @Override
    public List<Book> getBooksByStatus(BookStatus status) {
        return bookRepository.findByStatus(status);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    book.setGenre(updatedBook.getGenre());
                    book.setStatus(updatedBook.getStatus());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
} 