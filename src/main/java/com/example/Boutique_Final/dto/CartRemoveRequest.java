package com.example.Boutique_Final.dto;

import java.util.List;

public class CartRemoveRequest {
    private String userId; // ObjectId as String
    private List<String> productIds; // Product IDs as String (since MongoDB uses ObjectId)

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
