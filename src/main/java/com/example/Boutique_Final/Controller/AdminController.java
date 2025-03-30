package com.example.Boutique_Final.Controller;

import com.example.Boutique_Final.dto.OrderDTO;
import com.example.Boutique_Final.dto.ProductDTO;
import com.example.Boutique_Final.dto.ProductListDTO;
import com.example.Boutique_Final.dto.UserDTO;
import com.example.Boutique_Final.model.Product;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.UserRepository;
import com.example.Boutique_Final.service.OrderService;
import com.example.Boutique_Final.service.ProductService;
import com.example.Boutique_Final.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3005", allowCredentials = "true")
public class AdminController {
    @Autowired
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(Product.class);
    private final UserRepository userRepository;


    public AdminController(ProductService productService, UserService userService, OrderService orderService, UserRepository userRepository) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category, Principal principal) {
        String email = principal.getName(); // Retrieve authenticated user's email
        logger.info("Authenticated request by: {}", email);

        List<ProductListDTO> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductListDTO>> searchProducts(@RequestParam String query) {
        List<ProductListDTO> products = productService.searchProducts(query);
        return ResponseEntity.ok(products);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productService.createProduct(productDTO).getBody();
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception (can be replaced with proper logging framework)
            System.err.println("Error occurred: " + e.getMessage());

            // Return a bad request response with error details
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    // Delete a product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (productService.getProductById(id) != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            logger.error("Error updating product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





//    @GetMapping("/users")
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }


    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId().toHexString(), user.getUsername(), user.getEmail(), user.getRole().toString()))
                .collect(Collectors.toList());
    }



    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Principal principal) {

        // Log authenticated user
        logger.info("Authenticated request by: {}", principal.getName());

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<ProductListDTO> products = productService.findAllWithoutComments(pageable);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders(); // Fetch all orders
        return new ResponseEntity<>(orders, HttpStatus.OK); // Return orders with HTTP 200 status
    }
}


