package com.te.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import com.te.model.Book;
import com.te.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/books/add-books.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            // Read form values
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            String publishedYear = request.getParameter("publishedYear");

            // Validate required fields
            if (isBlank(isbn) || isBlank(title)
                    || isBlank(author) || isBlank(category)
                    || isBlank(price) || isBlank(quantity)
                    || isBlank(publishedYear)) {

                throw new IllegalArgumentException(
                        "Please fill in all fields and select a category.");
            }

            // Debug: verify category received from JSP
            System.out.println("Category received: [" + category + "]");

            // Create Book object
            Book book = new Book();

            book.setIsbn(isbn.trim());
            book.setTitle(title.trim());
            book.setAuthor(author.trim());
            book.setCategory(category.trim());
            book.setPrice(new BigDecimal(price.trim()));
            book.setQuantity(Integer.parseInt(quantity.trim()));
            book.setPublishedYear(
                    Integer.parseInt(publishedYear.trim()));

            // Save using service
            bookService.addBook(book);

            session.setAttribute(
                    "successMessage",
                    "Book added successfully.");

            response.sendRedirect(
                    request.getContextPath() + "/ViewBooksServlet");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    getMessage(e));

            request.getRequestDispatcher("/books/add-books.jsp")
                   .forward(request, response);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String getMessage(Exception e) {
        return e.getMessage() == null
                ? "Unable to add book. Please check the entered values."
                : e.getMessage();
    }
}
