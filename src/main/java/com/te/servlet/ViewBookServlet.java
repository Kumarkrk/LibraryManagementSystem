package com.te.servlet;

import java.io.IOException;
import java.util.List;

import com.te.model.Book;
import com.te.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewBooksServlet")
public class ViewBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookService bookService = new BookService();

   
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        List<Book> books;

        boolean noFilters =
                (isbn == null || isbn.trim().isEmpty())
                && (title == null || title.trim().isEmpty())
                && (author == null || author.trim().isEmpty())
                && (category == null || category.trim().isEmpty()
                    || category.equalsIgnoreCase("All category")
                    || category.equalsIgnoreCase("All Categories"));

        if (noFilters) {
            books = bookService.getAllBooks();
        } else {
            books = bookService.searchBooks(
                    isbn, title, author, category);
        }

        request.setAttribute("books", books);

        request.getRequestDispatcher("/books/view-books.jsp")
               .forward(request, response);
    }
    
}