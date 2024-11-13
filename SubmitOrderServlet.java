package com.example.online.orderings.alphaonlinemeal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submit_order")
public class SubmitOrderServlet extends HttpServlet {

    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize the database connection
            conn = DatabaseConnection.initializeDatabase();
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to the database", e);
        }
    }

    @Override
    public void destroy() {
        // Close the connection when the servlet is destroyed
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse the JSON cart data sent from the client
        String cartData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Convert JSON data into Java objects using Gson
        Gson gson = new Gson();
        List<CartItem> cartItems = gson.fromJson(cartData, new TypeToken<List<CartItem>>(){}.getType());

        // Save each item to the database
        for (CartItem item : cartItems) {
            saveToDatabase(item);
        }

        // Send a success response in JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"success\"}");
    }

    private void saveToDatabase(CartItem item) {
        String sql = "INSERT INTO orders (product_name, price, quantity, order_date) VALUES (?, ?, ?, NOW())";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Insert product details into the order table
            stmt.setString(1, item.getTitle());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
