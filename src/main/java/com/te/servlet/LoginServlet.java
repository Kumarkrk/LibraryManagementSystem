
package com.te.servlet;

import java.io.IOException;

import com.te.model.User;
import com.te.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username == null || username.trim().isEmpty()
                    || password == null || password.isEmpty()) {

                request.setAttribute("errorMessage",
                        "Enter username and password.");

                request.getRequestDispatcher("/Login.jsp")
                       .forward(request, response);
                return;
            }

            User user = userService.getUserByUsername(username.trim());

            if (user != null && password.equals(user.getPasswordHash())) {

                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());


                response.sendRedirect(
                        request.getContextPath() + "/DashBoard.jsp");

            } else {
                request.setAttribute("errorMessage",
                        "Invalid username or password.");

                request.getRequestDispatcher("/Login.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Unable to process login.", e);
        }
    }
}
