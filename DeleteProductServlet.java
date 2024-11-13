package com.example.online.orderings.alphaonlinemeal;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            // Parse JSON request
            String requestData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            Map<String, Integer> data = gson.fromJson(requestData, HashMap.class);
            int productId = data.get("productId");

            // Delete product from the database
            try (Connection conn = DatabaseConnection.initializeDatabase()) {
                String sql = "DELETE FROM menu_items WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, productId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"status\":\"error\",\"message\":\"Database error\"}");
                return;
            }

            // Send success response
            out.write("{\"status\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"status\":\"error\",\"message\":\"Invalid input\"}");
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to delete menu items from the database";
    }
}
