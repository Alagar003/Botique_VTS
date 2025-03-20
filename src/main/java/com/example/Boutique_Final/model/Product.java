package com.example.Boutique_Final.model;//package com.example.Boutique_Final.model;
//
//import com.example.Boutique_Final.dto.CommentDTO;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Document(collection = "products")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Product {
//    @Id
//    private ObjectId id;  // MongoDB should auto-generate this if null initially
//    private String name;
//    private String description;
//    private Double price;
//    private Integer quantity; // Available stock
//    private String image;  // Image URL or path
//    private String category;  // Add this field for product categorization
//
//    @DBRef(lazy = true)
//    private List<Comment> comments = new ArrayList<>();
//
//
//
//    public ObjectId getId() {
//        return (ObjectId) id;
//    }
//
//    public void setId(Object id) {
//        this.id = (ObjectId) id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    // Method to reduce stock
//    public void reduceStock(int amount) {
//        if (this.quantity < amount) {
//            throw new RuntimeException("Not enough stock available.");
//        }
//        this.quantity -= amount;
//    }
//
//    // Method to increase stock
//    public void increaseStock(int amount) {
//        this.quantity += amount;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public String setImage(String image) {
//        this.image = image;
//        return image;
//    }
//}

import com.example.Boutique_Final.dto.CommentDTO;
import com.example.Boutique_Final.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private String category;

    @DBRef(lazy = true)
    private List<Comment> comments = new ArrayList<>();


    public Product(ObjectId id, String name, String description, Double price, Integer quantity, String image, String category, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.category = category;
        this.comments = comments;
    }

    // Business logic methods
    public void reduceStock(int amount) {
        if (this.quantity < amount) {
            throw new RuntimeException("Not enough stock available.");
        }
        this.quantity -= amount;
    }

    public void increaseStock(int amount) {
        this.quantity += amount;
    }
}