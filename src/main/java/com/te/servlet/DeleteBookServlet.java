package com.te.servlet;

import java.io.IOException;

import com.te.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            Long id = Long.parseLong(request.getParameter("id"));

            boolean deleted = bookService.deleteBook(id);

            if (deleted) {
                session.setAttribute("successMessage",
                        "Book deleted successfully.");
            } else {
                session.setAttribute("errorMessage",
                        "Book not found.");
            }

        } catch (Exception e) {
            session.setAttribute("errorMessage",
                    e.getMessage() == null
                            ? "Unable to delete book."
                            : e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath() + "/ViewBooksServlet");
    }
}