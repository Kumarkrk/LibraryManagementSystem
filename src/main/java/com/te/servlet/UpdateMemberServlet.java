package com.te.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.te.model.Member;
import com.te.service.MemberService;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateMemberServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phno=request.getParameter("phone");
		String address=request.getParameter("address");
		LocalDate date=LocalDate.parse(request.getParameter("membershipDate"));
		MemberService ms=new MemberService();
		
			
		Member member=new Member();
		member.setId((long) id);
		member.setEmail(email);
		member.setAddress(address);
		member.setName(name);
		member.setMembershipDate(date);
		member.setPhone(phno);
	   Member person=ms.updateMember(member);
	   if(person!=null)
	   {
		   session.setAttribute("successMessage",
                   "member updated successfully.");
		   response.sendRedirect(
                   request.getContextPath() + "/ViewMemberServlet");
	   }
		
		
		
	}

}
