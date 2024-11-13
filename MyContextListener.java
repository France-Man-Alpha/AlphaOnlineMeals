package com.example.online.orderings.alphaonlinemeal;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;



@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Shutdown cleanup thread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        System.out.println("AbandonedConnectionCleanupThread shut down.");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No initialization needed in this case
    }
}
