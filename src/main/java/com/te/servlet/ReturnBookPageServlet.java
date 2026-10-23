package com.te.servlet;

import java.io.IOException;

import com.te.service.IssueService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReturnBookPageServlet")
public class ReturnBookPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IssueService issueService = new IssueService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute(
                "activeIssues",
                issueService.getActiveIssues()
            );

            request.getRequestDispatcher("/issue/return-book.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                "Unable to load currently issued books.", e
            );
        }
    }
}