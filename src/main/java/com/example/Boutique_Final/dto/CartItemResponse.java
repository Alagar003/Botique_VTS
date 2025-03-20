package com.example.Boutique_Final.dto; // Change this to your actual package

public class CartItemResponse {
    private String productId;
    private String productName;
    private String imageUrl;
    private double price;
    private int quantity;

    // Constructor
    public CartItemResponse(String productId, String productName, String imageUrl, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
