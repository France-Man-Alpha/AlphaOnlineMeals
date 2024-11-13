package com.example.online.orderings.alphaonlinemeal;

public class MenuItem {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imagePath; // Keep this to reflect the file system path
    private String category;

    // Constructor
    public MenuItem(int id, String title, String description, double price, String imagePath, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
