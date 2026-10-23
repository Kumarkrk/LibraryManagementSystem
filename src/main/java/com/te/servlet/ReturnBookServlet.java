package com.te.servlet;

import java.io.IOException;

import com.te.service.IssueService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IssueService issueService = new IssueService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            String issueIdParam = request.getParameter("issueId");

            if (issueIdParam == null || issueIdParam.trim().isEmpty()) {
                session.setAttribute(
                    "errorMessage",
                    "Issue ID is required."
                );
            } else {
                int issueId = Integer.parseInt(issueIdParam);

                issueService.returnBook(issueId);

                session.setAttribute(
                    "successMessage",
                    "Book returned successfully."
                );
            }

        } catch (NumberFormatException e) {
            session.setAttribute(
                "errorMessage",
                "Invalid issue ID."
            );

        } catch (Exception e) {
            throw new ServletException(
                "Unable to return book.", e
            );
        }

        response.sendRedirect(
            request.getContextPath() + "/ReturnBookPageServlet"
        );
    }
}