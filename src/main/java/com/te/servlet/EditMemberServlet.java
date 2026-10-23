
package com.te.servlet;

import java.io.IOException;

import com.te.model.Member;
import com.te.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditMemberServlet")
public class EditMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter = request.getParameter("id");

        if (idParameter == null || idParameter.trim().isEmpty()) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Member ID is required."
            );
            return;
        }

        try {
            Long id = Long.parseLong(idParameter);

            Member member = memberService.getMemberById(id);

            if (member == null) {
                response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Member not found."
                );
                return;
            }

            request.setAttribute("member", member);

            request.getRequestDispatcher("/members/edit-member.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Invalid member ID."
            );
        } catch (Exception e) {
            throw new ServletException(
                "Unable to load member details.", e
            );
        }
    }
}
