package com.example.Boutique_Final.Controller;

import com.example.Boutique_Final.dto.CartRemoveRequest;
import com.example.Boutique_Final.dto.OrderDTO;
import com.example.Boutique_Final.dto.ProductListDTO;
import com.example.Boutique_Final.model.Order;
import com.example.Boutique_Final.service.CartService;
import com.example.Boutique_Final.service.OrderService;
import com.example.Boutique_Final.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    private final CartService cartService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);




    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        System.out.println("Order" + order);
        try {
            // Validate the incoming Order object
            if (order.getUser() == null || order.getUser().getId() == null) {
                return ResponseEntity.badRequest().body("User ID is required.");
            }
            if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Order items cannot be empty.");
            }
            if (order.getTotalPrice() <= 0) {
                return ResponseEntity.badRequest().body("Total price must be greater than zero.");
            }

            // Delegate to service for order creation
            OrderDTO createdOrder = orderService.createOrder(order);
            return ResponseEntity.ok(createdOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Handle validation errors
        } catch (Exception e) {
            e.printStackTrace(); // Log unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order.");
        }
    }


//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable String userId) {
//        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
//    }


    @GetMapping("/user")
    public ResponseEntity<?> getOrdersByUserId(@RequestParam(required = false) String userId) {
        System.out.println("Received userId: " + userId);

        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().body("User ID is required to fetch orders.");
        }

        try {
            List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching orders.");
        }
    }








    @GetMapping
    public ResponseEntity<Page<ProductListDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Principal principal) {

        // Log authenticated user
        logger.info("Authenticated request by: {}", principal.getName());

        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<ProductListDTO> products = productService.findAllWithoutComments((org.springframework.data.domain.Pageable) pageable);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItemsFromCart(@RequestBody CartRemoveRequest request) {
        // Log the incoming request
        System.out.println("Request to remove items from cart for user: " + request.getUserId());
        System.out.println("Product IDs to remove: " + request.getProductIds());

        try {
            // Validate the payload
            if (request.getUserId() == null || request.getProductIds() == null || request.getProductIds().isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid request payload");
            }

            // Delegate to the service layer
            cartService.removeItems(request.getUserId(), request.getProductIds());
            return ResponseEntity.ok("Items removed successfully");
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to remove items from cart");
        }

    }


}
