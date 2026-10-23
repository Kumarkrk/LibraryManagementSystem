package com.te.servlet;

import java.io.IOException;

import com.te.service.IssueService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IssueService issueService = new IssueService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("issue/issue-book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int memberId = Integer.parseInt(request.getParameter("memberId"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));

            issueService.issueBook(memberId, bookId);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Book issued successfully.");

            response.sendRedirect(request.getContextPath() + "/IssueHistoryServlet");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Select a valid member and book.");
            request.getRequestDispatcher("/issue/issue-book.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Unable to issue book: " + e.getMessage());
            request.getRequestDispatcher("/issue/issue-book.jsp").forward(request, response);
        }
    }
}