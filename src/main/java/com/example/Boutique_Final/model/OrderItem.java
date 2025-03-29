//package com.example.Boutique_Final.model;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//import java.math.BigDecimal;
//
//@Document(collection = "orderItems")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class OrderItem {
//    @Id
//    private String id;
//
//    @DBRef
//    private Order order;
//
//    @DBRef
//    private Product product;
//
//    private Integer quantity;
//    private BigDecimal price;
//}


//////package com.example.Boutique_Final.model;
//////
//////import lombok.Data;
//////import java.math.BigDecimal;
//////
//////@Data
//////public class OrderItem {
//////    private String id;
//////    private Product product;
//////    private int quantity;
//////    private BigDecimal totalPrice;
//////
//////    // Exclude the `Order` reference to prevent circular dependency in `toString`.
//////    private transient Order order; // Marked as transient if not required in MongoDB.
//////
//////    public OrderItem(Product product, Integer quantity, BigDecimal totalItemPrice) {
//////        this.product = product;
//////        this.quantity = quantity;
//////        this.totalPrice = totalItemPrice;
//////    }
//////
//////    @Override
//////    public String toString() {
//////        return "OrderItem{" +
//////                "product=" + (product != null ? product.getId() : "null") +
//////                ", quantity=" + quantity +
//////                ", totalPrice=" + totalPrice +
//////                '}';
//////    }
//////}
////
////
////
////package com.example.Boutique_Final.model;
////
////import lombok.Data;
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import java.math.BigDecimal;
////
////@Data
////public class OrderItem {
////    private String id;
////    private Product product;
////
////    // Changed from `int` to `Integer` to allow null handling
////    private Integer quantity;
////
////    private BigDecimal totalPrice;
////
////    @JsonIgnore // Prevents serialization of circular reference
////    private transient Order order;
////
////    public OrderItem() {
////        // Default constructor
////    }
////
////    public OrderItem(Product product, Integer quantity, BigDecimal totalItemPrice) {
////        if (product == null) {
////            throw new IllegalArgumentException("Product cannot be null");
////        }
////        if (quantity == null || quantity <= 0) {
////            throw new IllegalArgumentException("Quantity must be greater than zero");
////        }
////        if (totalItemPrice == null || totalItemPrice.compareTo(BigDecimal.ZERO) <= 0) {
////            throw new IllegalArgumentException("Total price must be greater than zero");
////        }
////
////        this.product = product;
////        this.quantity = quantity;
////        this.totalPrice = totalItemPrice;
////    }
////
////    @Override
////    public String toString() {
////        return "OrderItem{" +
////                "product=" + (product != null ? product.getId() : "null") +
////                ", quantity=" + quantity +
////                ", totalPrice=" + totalPrice +
////                '}';
////    }
////}
//
//
//
//package com.example.Boutique_Final.model;
//
//import lombok.Data;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import java.math.BigDecimal;
//
//@Data
//public class OrderItem {
//    private String id;
//    private Product product;
//    private Integer quantity;
//    private BigDecimal totalPrice;
//
//    @JsonIgnore // Prevents circular serialization
//    private transient Order order;
//
//    public OrderItem(Product product, Integer quantity, BigDecimal totalPrice) {
//        if (product == null || product.getId() == null || product.getName() == null) {
//            throw new IllegalArgumentException("Product cannot be null, and must contain an ID and Name");
//        }
//        if (quantity == null || quantity <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Total price must be greater than zero");
//        }
//
//        this.product = product;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }
//
//}

package com.example.Boutique_Final.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    private String id;

    @NotNull(message = "Product cannot be null")
    private Product product;

    @NotNull(message = "Quantity must be specified")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "Total price must be specified")
    @Positive(message = "Total price must be greater than zero")
    private BigDecimal totalPrice;

    @JsonIgnore // Prevents circular references during serialization
    private transient Order order;

    // Default constructor for frameworks like Jackson or Hibernate
    public OrderItem() {
    }

    // Constructor with all fields and validation
    public OrderItem(Product product, Integer quantity, BigDecimal totalPrice) {
        if (product == null || product.getId() == null || product.getName() == null) {
            throw new IllegalArgumentException("Product cannot be null, and must contain an ID and Name");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total price must be greater than zero");
        }

        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
