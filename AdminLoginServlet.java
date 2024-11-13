package com.example.online.orderings.alphaonlinemeal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin_login")
public class AdminLoginServlet extends HttpServlet {

    private static final String VALID_USERNAME = "AlphaMealsAdmin1";
    private static final String VALID_PASSWORD = "FirstMan99";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            // Login successful
            response.sendRedirect("admin_dashboard.html");
        } else {
            // Login failed
            out.println("<html><body>");
            out.println("<h3>Invalid credentials, please try again.</h3>");
            out.println("</body></html>");
        }
    }
}
