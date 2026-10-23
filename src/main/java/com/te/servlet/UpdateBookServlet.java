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

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            Book book = new Book();
            book.setId(Long.parseLong(request.getParameter("id")));
            book.setIsbn(request.getParameter("isbn").trim());
            book.setTitle(request.getParameter("title").trim());
            book.setAuthor(request.getParameter("author").trim());
            book.setCategory(request.getParameter("category").trim());
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setQuantity(Integer.parseInt(
                    request.getParameter("quantity")));
            book.setPublishedYear(Integer.parseInt(
                    request.getParameter("publishedYear")));

            bookService.updateBook(book);

            session.setAttribute("successMessage",
                    "Book updated successfully.");

            response.sendRedirect(
                    request.getContextPath() + "/ViewBooksServlet");

        } catch (Exception e) {
            request.setAttribute("errorMessage",
                    e.getMessage() == null
                            ? "Unable to update book."
                            : e.getMessage());

            request.getRequestDispatcher("/editBook.jsp")
                   .forward(request, response);
        }
    }
}