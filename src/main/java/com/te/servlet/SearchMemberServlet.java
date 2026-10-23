package com.te.servlet;

import java.io.IOException;

import com.te.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchMemberServlet")
public class SearchMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                request.setAttribute("members", memberService.getAllMembers());
            } else {
                request.setAttribute("members", memberService.searchMembers(keyword.trim()));
            }

            request.getRequestDispatcher("/members/search-member.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Unable to search members.");
            request.getRequestDispatcher("/members/search-member.jsp")
                   .forward(request, response);
        }
    }
}