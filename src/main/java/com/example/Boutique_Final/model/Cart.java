package com.example.Boutique_Final.model;//package com.example.Boutique_Final.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.ArrayList;
//import java.util.List;a
//
//@Document(collection = "carts")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Cart {
//    @Id
//    private ObjectId id; // MongoDB ObjectId
//    @Indexed(unique = true)
//    private ObjectId userId; // Ensure it's unique per user
//
//
//    @DBRef
//    private User user;
//
//    private List<CartItem> items = new ArrayList<>(); // Initialize items list
//    private double totalPrice; // Total price of the cart
//
//    private
//
//    // Constructor to initialize userId and items
//    public Cart(ObjectId userId, List<CartItem> items) {
//        this.id = new ObjectId(); // MongoDB will generate a new ID
//        this.userId = userId;
//        this.items = items != null ? items : new ArrayList<>(); // Ensure items is not null
//        calculateTotalPrice(); // Calculate total price upon initialization
//    }
//
//    public Cart(User user) {
//        this.user = user;
//        this.items = new ArrayList<>();
//    }
//
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public void addItem(Product product, int quantity) {
//        // Check if the product is already in the cart
//        for (CartItem item : items) {
//            if (item.getProduct().getId().equals(product.getId())) {
//                // If the product is already in the cart, update the quantity
//                item.setQuantity(item.getQuantity() + quantity);
//                calculateTotalPrice(); // Recalculate total price
//                return; // Exit the method after updating
//            }
//        }
//
//        // If the product is not in the cart, create a new CartItem and add it
//        CartItem newItem = new CartItem();
//        newItem.setProduct(product);
//        newItem.setQuantity(quantity);
//        items.add(newItem);
//        calculateTotalPrice(); // Recalculate total price
//    }
//
//    // Method to calculate the total price of the cart
//    public void calculateTotalPrice() {
//        totalPrice = items.stream()
//                .mapToDouble(item -> {
//                    Double price = item.getProduct().getPrice(); // Get the price of the product
//                    return (price != null ? price : 0.0) * item.getQuantity(); // Handle null price
//                })
//                .sum();
//    }
//}


//package com.example.Boutique_Final.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Document(collection = "cart")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Cart {
//    @Id
//    private ObjectId id; // MongoDB ObjectId
//    @Indexed(unique = true)
//    private ObjectId userId; // Ensure it's unique per user
//
//    @DBRef
//    private User user;
//
//    private List<CartItem> items = new ArrayList<>(); // Initialize items list
//    private double totalPrice; // Total price of the cart
//
//    // Constructor to initialize userId
//    public Cart(ObjectId userId) {
//        this.userId = userId;
//        this.items = new ArrayList<>();
//    }
//
//    public Cart(User user) {
//        this.user = user;
//        this.items = new ArrayList<>();
//    }
//
//    public void addItem(Product product, int quantity) {
//        for (CartItem item : items) {
//            if (item.getProduct().getId().equals(product.getId())) {
//                item.setQuantity(item.getQuantity() + quantity);
//                calculateTotalPrice();
//                return;
//            }
//        }
//        CartItem newItem = new CartItem(product, quantity);
//        items.add(newItem);
//        calculateTotalPrice();
//    }
//
//    public void calculateTotalPrice() {
//        totalPrice = items.stream()
//                .mapToDouble(item -> {
//                    Double price = item.getProduct().getPrice();
//                    return (price != null ? price : 0.0) * item.getQuantity();
//                })
//                .sum();
//    }
//
//    public String getUserEmail() {
//        return user != null ? user.getEmail() : null;
//    }
//}


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private ObjectId id; // MongoDB ObjectId
    @Indexed(unique = true)
    private ObjectId userId; // Ensure it's unique per user

    @DBRef
    private User user;

    private List<CartItem> items = new ArrayList<>(); // Initialize items list
    private double totalPrice; // Total price of the cart

    // Constructor to initialize userId
    public Cart(ObjectId userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    // ✅ Add this constructor (Fixes the issue)
    public Cart(ObjectId userId, List<CartItem> items) {
        this.userId = userId;
        this.items = items;
        this.totalPrice = 0.0; // Initialize total price
    }

    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotalPrice();
                return;
            }
        }
        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = items.stream()
                .mapToDouble(item -> {
                    Double price = item.getProduct().getPrice();
                    return (price != null ? price : 0.0) * item.getQuantity();
                })
                .sum();
    }

    public String getUserEmail() {
        return user != null ? user.getEmail() : null;
    }
}
