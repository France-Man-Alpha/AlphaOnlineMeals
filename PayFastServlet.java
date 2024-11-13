package com.example.online.orderings.alphaonlinemeal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/payfast")
public class PayFastServlet extends HttpServlet {

    private static final String PAYFAST_URL = "https://www.payfast.co.za/eng/process";
    private static final String MERCHANT_ID = "24566603";
    private static final String MERCHANT_KEY = "shg9zlui2w712";
    private static final String PASS_PHRASE = "your_passphrase_here"; // This will be ignored

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse cart data from the request
        String cartData = request.getParameter("cartData");

        // Convert JSON data into Java objects using Gson
        Gson gson = new Gson();
        List<CartItem> cartItems = gson.fromJson(cartData, new TypeToken<List<CartItem>>(){}.getType());

        double totalAmount = 0;
        String itemName = "Order";

        // Calculate total amount and prepare item details
        for (CartItem item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
            itemName += " | " + item.getTitle();
        }

        // Prepare payment data without signature
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("merchant_id", MERCHANT_ID);
        paymentData.put("merchant_key", MERCHANT_KEY);
        paymentData.put("return_url", "http://localhost:8080/SubmitOrderServlet");
        paymentData.put("cancel_url", "http://localhost:8080/mycart.html");
        paymentData.put("notify_url", "http://yourdomain.com/payment_notify");
        paymentData.put("amount", String.format("%.2f", totalAmount));
        paymentData.put("item_name", itemName);

        // Generate HTML form
        StringBuilder formHtml = new StringBuilder();
        formHtml.append("<html><body onload='document.forms[0].submit()'>");
        formHtml.append("<form action='").append(PAYFAST_URL).append("' method='POST'>");

        for (Map.Entry<String, String> entry : paymentData.entrySet()) {
            formHtml.append("<input type='hidden' name='").append(entry.getKey()).append("' value='").append(entry.getValue()).append("'>");
        }

        formHtml.append("</form></body></html>");

        // Send the form HTML as a response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(formHtml.toString());
        out.flush();
    }
}
