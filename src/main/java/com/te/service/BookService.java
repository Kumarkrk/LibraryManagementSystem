
package com.te.service;

import java.util.List;
import java.util.Set;

import com.te.dao.BookDao;
import com.te.model.Book;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class BookService {

    private final BookDao bookDao = new BookDao();

    private final Validator validator =
        Validation.buildDefaultValidatorFactory().getValidator();

    private void validate(Book book) {
        Set<ConstraintViolation<Book>> errors =
            validator.validate(book);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(
                errors.iterator().next().getMessage());
        }
    }

    public Book addBook(Book book) {
        validate(book);

        String isbn = book.getIsbn().trim();
        book.setIsbn(isbn);

        long count = bookDao.countByIsbn(isbn);

        System.out.println("ISBN being checked: [" + isbn + "]");
        System.out.println("Matching ISBN count: " + count);

        if (count > 0) {
            throw new IllegalArgumentException(
                "ISBN already exists. Enter a different ISBN."
            );
        }

        return bookDao.save(book);
    }
    public Book getBookById(Long id) {
        return bookDao.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public List<Book> searchBooks(
            String isbn, String title,
            String author, String category) {

        return bookDao.searchBooks(isbn, title, author, category);
    }

    public Book updateBook(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("Book ID is required");
        }

        validate(book);

        Book existing = bookDao.findById(book.getId());

        if (existing == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (bookDao.countByIsbnExcludingId(
                book.getIsbn().trim(), book.getId()) > 0) {
            throw new IllegalArgumentException("ISBN already exists");
        }

        existing.setIsbn(book.getIsbn());
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setCategory(book.getCategory().name());
        existing.setPrice(book.getPrice());
        existing.setQuantity(book.getQuantity());
        existing.setPublishedYear(book.getPublishedYear());

        return bookDao.update(existing);
    }

    public boolean deleteBook(Long id) {
        Book existing = bookDao.findById(id);

        if (existing == null) {
            return false;
        }

        if (bookDao.hasIssueHistory(id)) {
            throw new IllegalStateException(
                "Cannot delete a book with issue history");
        }

        return bookDao.delete(id);
    }
}
