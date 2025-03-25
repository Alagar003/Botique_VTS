package com.example.Boutique_Final.service;

import com.example.Boutique_Final.dto.ProductListDTO;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import com.example.Boutique_Final.model.Product;


import java.util.List;
import java.util.stream.Collectors;


//package com.example.Boutique_Final.service;
//
//import com.example.Boutique_Final.dto.CommentDTO;
//import com.example.Boutique_Final.dto.ProductDTO;
//import com.example.Boutique_Final.dto.ProductListDTO;
//import com.example.Boutique_Final.model.Product;
//import com.example.Boutique_Final.repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    // Get all products without comments
//    public Page<ProductListDTO> findAllWithoutComments(Pageable pageable) {
//        Page<Product> products = productRepository.findAll(pageable);
//        return products.map(product -> new ProductListDTO(
//                product.getId().toHexString(),
//                product.getName(),
//                product.getDescription(),
//                product.getPrice().doubleValue(),
//                product.getQuantity(),
//                product.getImage()
//        ));
//    }
//
//    // Get product by ID
//    public ProductDTO getProductById(String id) {
//        Optional<Product> product = productRepository.findById(id);
//
//        return product.map(value -> new ProductDTO(
//                value.getId().toHexString(),               // Convert ObjectId to String
//                value.getName(),
//                value.getDescription(),
//                value.getPrice(),
//                value.getQuantity(),
//                value.getImage(),
//                value.getCategory(),
//                new ArrayList<>()            // comments (empty for now)
//        )).orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
//    }
//
//
//
//    // Get products by category
//    public List<ProductListDTO> getProductsByCategory(String category) {
//        List<Product> products = productRepository.findByCategoryIgnoreCase(category);
//        return products.stream()
//                .map(product -> new ProductListDTO(
//                        product.getId().toHexString(),
//                        product.getName(),
//                        product.getDescription(),
//                        product.getPrice().doubleValue(),
//                        product.getQuantity(),
//                        product.getImage()
//                ))
//                .collect(Collectors.toList());
//    }
//
//
//
//    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
//        // Validate fields if necessary
//        validateProduct(productDTO);
//
//        // Construct a Product entity from the incoming DTO
//        Product product = new Product(
//                null, // ID is null for new product; MongoDB will generate it.
//                productDTO.getName(),
//                productDTO.getDescription(),
//                productDTO.getPrice(),
//                productDTO.getQuantity(),
//                productDTO.getImage(),
//                productDTO.getCategory(), // Add category here
//                null // comments can be null or empty initially.
//        );
//
//        // Save the product in the repository
//        Product savedProduct = productRepository.save(product);
//
//        // Construct a ProductDTO to return as the response
//        ProductDTO savedProductDTO = new ProductDTO(
//                savedProduct.getId().toHexString(),
//                savedProduct.getName(),
//                savedProduct.getDescription(),
//                savedProduct.getPrice(),
//                savedProduct.getQuantity(),
//                savedProduct.getImage(),
//                savedProduct.getCategory(), // Return the saved category
//                null
//        );
//
//        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
//    }
//
//
//
//
//    // Delete a product
//    public ResponseEntity<Void> deleteProduct(String id) {
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
    // Search products by name or description
//    public List<ProductListDTO> searchProducts(String keyword) {
//        List<Product> products = productRepository.searchByNameOrDescription(keyword, keyword);
//        return products.stream()
//                .map(product -> new ProductListDTO(
//                        product.getId().toHexString(),
//                        product.getName(),
//                        product.getDescription(),
//                        product.getPrice().doubleValue(),
//                        product.getQuantity(),
//                        product.getImage()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Validate a product
//    private void validateProduct(ProductDTO productDTO) {
//        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
//            throw new IllegalArgumentException("Product name is required.");
//        }
//        if (productDTO.getPrice() == null || BigDecimal.valueOf(productDTO.getPrice()).compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Price must be greater than 0.");
//        }
//
//        if (productDTO.getQuantity() == null || productDTO.getQuantity() < 1) {
//            throw new IllegalArgumentException("Quantity must be at least 1.");
//        }
//    }
//
//    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
//        Optional<Product> existingProductOptional = productRepository.findById(id);
//        if (existingProductOptional.isPresent()) {
//            Product existingProduct = existingProductOptional.get();
//            existingProduct.setName(productDTO.getName());
//            existingProduct.setDescription(productDTO.getDescription());
//            existingProduct.setPrice(productDTO.getPrice());
//            existingProduct.setQuantity(productDTO.getQuantity());
//            existingProduct.setCategory(productDTO.getCategory());
//            existingProduct.setImage(productDTO.getImage());
//
//            // Save updated product
//            Product updatedProduct = productRepository.save(existingProduct);
//            return new ProductDTO(updatedProduct);
//        } else {
//            throw new RuntimeException("Product not found with ID: " + id);
//        }
//    }
//
//
//}




import com.example.Boutique_Final.dto.ProductDTO;
import com.example.Boutique_Final.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ Create a new product
    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(ProductDTO.fromEntity(savedProduct));
    }

    // ✅ Get all products
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }
    public ProductDTO getProductById(String id) {
        return productRepository.findById(id)
                .map(ProductDTO::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }


    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());

        return ProductDTO.fromEntity(productRepository.save(product));

    }

    public List<ProductListDTO> searchProducts(String keyword) {
        List<Product> products = productRepository.searchByNameOrDescription(keyword, keyword);
        return products.stream()
                .map(product -> new ProductListDTO(
                        product.getId().toHexString(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice().doubleValue(),
                        product.getQuantity(),
                        product.getImage(),
                        product.getCategory()
                ))
                .collect(Collectors.toList());
    }

    public List<ProductListDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategoryIgnoreCase(category);
        return products.stream()
                .map(product -> new ProductListDTO(
                        product.getId().toHexString(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice().doubleValue(),
                        product.getQuantity(),
                        product.getImage(),
                        product.getCategory()
                ))
                .collect(Collectors.toList());
    }


        // Delete a product
    public ResponseEntity<Void> deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Get all products without comments
    public Page<ProductListDTO> findAllWithoutComments(Pageable pageable) {
        Page<Product> products = productRepository.findAllWithoutComments(pageable);

        // 🔴 Debugging: Print the number of products fetched
        System.out.println("Fetched products: " + products.getTotalElements());

        return products.map(product -> new ProductListDTO(
                product.getId().toHexString(),
                product.getName(),
                product.getDescription(),
                product.getPrice() != null ? product.getPrice() : 0.0,  // Default to 0.0 if null
                product.getQuantity(),
                product.getImage(),
                product.getCategory()
        ));

    }


    }


