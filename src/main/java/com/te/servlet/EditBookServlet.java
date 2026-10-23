package com.te.servlet;

import java.io.IOException;

import com.te.model.Book;
import com.te.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Book book = bookService.getBookById(id);

            if (book == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Book not found");
                return;
            }

            request.setAttribute("book", book);
            request.getRequestDispatcher("/editBook.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid book ID");
        }
    }
}