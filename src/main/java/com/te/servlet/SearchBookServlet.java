
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

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

//        String keyword = request.getParameter("keyword");
    	String isbn=request.getParameter("isbn");
    	String title=request.getParameter("title");
    	String author=request.getParameter("author");
    	String category=request.getParameter("category");
    	
    	
        try {
            List<Book> books;

            if (isbn==null && title==null && author==null && category=="All category") {
                books = bookService.getAllBooks();
            } else {
                books = bookService.searchBooks(isbn,title,author,category);
            }

            request.setAttribute("books", books);


            request.getRequestDispatcher("/books/search-book.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Unable to search books.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
