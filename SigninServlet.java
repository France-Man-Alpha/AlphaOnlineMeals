package com.example.online.orderings.alphaonlinemeal;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "SigninServlet", urlPatterns = {"/SigninServlet"})
public class SigninServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward the request to the signup.jsp page
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseConnection.initializeDatabase();  // Use your custom method here
             PreparedStatement statement = connection.prepareStatement(
                "SELECT password FROM users WHERE email = ?")) {
            
            statement.setString(1, email);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        // Create a session and set an attribute
                        HttpSession session = request.getSession();
                        session.setAttribute("userEmail", email);
                        response.sendRedirect("home.jsp");
                    } else {
                        response.sendRedirect("signin.jsp?error=invalid");
                    }
                } else {
                    response.sendRedirect("signin.jsp?error=invalid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
