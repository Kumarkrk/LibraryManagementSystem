package com.te.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.te.model.Member;
import com.te.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/members/add-member.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Member member = new Member();
            member.setName(request.getParameter("name"));
            member.setEmail(request.getParameter("email"));
            member.setPhone(request.getParameter("phone"));
            member.setAddress(request.getParameter("address"));
            member.setMembershipDate(LocalDate.parse(request.getParameter("membershipDate")));

            memberService.registerMember(member);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Member added successfully.");

            response.sendRedirect(request.getContextPath() + "/ViewMemberServlet");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Unable to add member: " + e.getMessage());
            request.getRequestDispatcher("/members/add-member.jsp")
                   .forward(request, response);
        }
    }
}