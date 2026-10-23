
package com.te.servlet;

import java.io.IOException;
import java.util.List;

import com.te.model.Member;
import com.te.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewMemberServlet")
public class ViewMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Member> members = memberService.getAllMembers();

            request.setAttribute("members", members);

            request.getRequestDispatcher("/members/view-member.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Unable to retrieve members.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
