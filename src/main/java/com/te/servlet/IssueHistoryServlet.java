package com.te.servlet;

import java.io.IOException;

import com.te.service.IssueService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IssueHistoryServlet")
public class IssueHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IssueService issueService = new IssueService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute("issues", issueService.getAllIssues());
            request.getRequestDispatcher("issue/issue-history.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Unable to load issue history.", e);
        }
    }
}