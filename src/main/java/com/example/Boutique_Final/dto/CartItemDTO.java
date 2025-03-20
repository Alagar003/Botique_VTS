package com.example.Boutique_Final.dto;

import com.example.Boutique_Final.model.CartItem;
import com.example.Boutique_Final.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private String productId; // Represents product.id
    private String productName; // Represents product.name
    private Double price; // Represents product.price
    private Integer quantity; // Represents CartItem.quantity
    private String imageUrl; // Represents product.image

    // Method to map CartItem to CartItemDTO
    public static CartItemDTO fromCartItem(CartItem cartItem) {
        if (cartItem == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }

        Product product = cartItem.getProduct(); // This should be populated if @DBRef is used
        if (product == null) {
            throw new IllegalArgumentException("Product in CartItem cannot be null");
        }

        return new CartItemDTO(
                product.getId() != null ? product.getId().toHexString() : null, // Convert ObjectId to String
                product.getName(), // Get product name
                product.getPrice(), // Convert BigDecimal to Double
                cartItem.getQuantity(), // Get quantity from CartItem
                product.getImage() // Assuming Product has an image field
        );
    }

    // Utility method to safely convert BigDecimal to Double
    public static Double convertBigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null; // Handle null values safely
    }
}