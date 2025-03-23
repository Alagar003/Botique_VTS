//package com.example.Boutique_Final.dto;
//
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductDTO {
//    @NotNull(message = "Product ID cannot be null")
//    private String id;
//
//    @NotNull(message = "Product name cannot be null")
//    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
//    private String name;
//
//    @NotNull(message = "Description cannot be null")
//    private String description;
//
//    @NotNull(message = "Price cannot be null")
//    private Double price;
//
//    @NotNull(message = "Quantity cannot be null")
//    private Integer quantity;
//
//    private String image;
//    private String category;
//
//    private List<CommentDTO> comments;
//
//    // No-args constructor (needed for deserialization)
//    public ProductDTO() {
//        this.comments = new ArrayList<>();  // Ensures comments are never null
//    }
//
//
//    // All-args constructor
//    public ProductDTO(String id, String name, String description, Double price, Integer quantity, String image, String category, List<CommentDTO> comments) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.quantity = quantity;
//        this.image = image;
//        this.category = category;
//        this.comments = comments;
//    }
//
//    // Getters and Setters
//    public String getId() {
//        return id;
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
//    public List<CommentDTO> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentDTO> comments) {
//        this.comments = comments;
//    }
//
//    public void setId(String id) {
//        this.id = id;
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
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//
//}


package com.example.Boutique_Final.dto;

import com.example.Boutique_Final.model.Product;

import java.util.List;

public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private String category;
    private List<CommentDTO> comments;


    // All-args constructor
    public ProductDTO(String id, String name, String description, Double price, Integer quantity, String image, String category, List<CommentDTO> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.category = category;
        this.comments = comments;
    }


    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getId().toString(), // Convert ObjectId to String
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImage(),
                product.getCategory(),
                null // Set comments if needed
        );
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}