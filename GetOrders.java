package com.example.online.orderings.alphaonlinemeal;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetOrders")
public class GetOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> ordersList = new ArrayList<>();

        // JDBC setup
        String jdbcURL = "jdbc:mysql://localhost:3306/alphaonlinemealsdb"; // Update with your database name
        String dbUser = "root";
        String dbPassword = "alphamn99@0233"; // Update with your database password

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM orders";  // Adjust SQL query as needed
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");
                    double totalPrice = resultSet.getDouble("total_price");

                    Order order = new Order(id, title, price, description, quantity, totalPrice);
                    ordersList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return;
        }

        // Convert list to JSON and send response
        String json = new Gson().toJson(ordersList);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
