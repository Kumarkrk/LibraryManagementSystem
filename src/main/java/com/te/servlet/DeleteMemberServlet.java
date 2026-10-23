package com.te.servlet;

import java.io.IOException;

import com.te.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            memberService.deleteMember(id);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Member deleted successfully.");

            response.sendRedirect(request.getContextPath() + "/ViewMembersServlet");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID.");
        } catch (Exception e) {
            throw new ServletException(
                    "Unable to delete member. Check whether the member has issue records.", e);
        }
    }
}