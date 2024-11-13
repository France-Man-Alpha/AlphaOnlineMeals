package com.example.online.orderings.alphaonlinemeal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Parse form data
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String category = request.getParameter("category");

            // Handle file upload
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String imageUrl = saveFile(filePart);

            // Insert product into the database
            try (Connection conn = DatabaseConnection.initializeDatabase()) {
                String sql = "INSERT INTO menu_items (title, description, price, imageUrl, category) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, title);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setString(4, imageUrl);
                stmt.setString(5, category);
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Failed to insert item, no rows affected.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"status\":\"error\",\"message\":\"Database error: " + e.getMessage() + "\"}");
                return;
            }

            // Send success response
            out.write("{\"status\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"status\":\"error\",\"message\":\"Invalid input: " + e.getMessage() + "\"}");
        } finally {
            out.close();
        }
    }

    private String saveFile(Part filePart) throws IOException {
        // Directory where the file will be saved
        String uploadDir = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDirFile = new File(uploadDir);

        // Create directory if it does not exist
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }

        // Get the file name and create a unique name for the file
        String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
        File file = new File(uploadDirFile, fileName);

        // Save the file to the disk
        filePart.write(file.getAbsolutePath());

        // Return the relative path to the file
        return UPLOAD_DIRECTORY + "/" + fileName;
    }

    @Override
    public String getServletInfo() {
        return "Servlet to add menu items to the database with file upload support";
    }
}
