package com.te.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "book",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_book_isbn",
            columnNames = "isbn"
        )
    }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ISBN cannot be empty")
    @Size(max = 20, message = "ISBN cannot exceed 20 characters")
    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @NotBlank(message = "Book title cannot be empty")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(max = 100, message = "Author cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String author;

    @NotNull(message = "Category cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Category category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Published year is required")
    @Min(value = 1, message = "Published year must be positive")
    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(String category) {

        if (category == null || category.trim().isEmpty()) {
            this.category = null;
            return;
        }

        this.category = Category.valueOf(
            category.trim().toUpperCase()
        );
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
