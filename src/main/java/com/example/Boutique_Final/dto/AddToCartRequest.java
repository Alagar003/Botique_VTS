package com.example.Boutique_Final.dto;

public class AddToCartRequest {
    private String productId;  // Change this to String if it's an ObjectId or any string type
    private int quantity;

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
