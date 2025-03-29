package com.example.Boutique_Final.Controller;

import com.example.Boutique_Final.Mapper.CartMapper;
import com.example.Boutique_Final.dto.*;
import com.example.Boutique_Final.exception.ErrorResponse;
import com.example.Boutique_Final.exception.InvalidRequestException;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import com.example.Boutique_Final.exception.UserNotFoundException;
import com.example.Boutique_Final.model.Cart;
import com.example.Boutique_Final.model.CartItem;
import com.example.Boutique_Final.model.Product;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.CartRepository;
import com.example.Boutique_Final.repositories.ProductRepository;
import com.example.Boutique_Final.service.CartService;
import com.example.Boutique_Final.service.JwtService;
import com.example.Boutique_Final.service.ProductService;
import com.example.Boutique_Final.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.BSONException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3001")
@RequiredArgsConstructor
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private final CartService cartService;
    private final JwtService jwtService;
    private final CartMapper cartMapper;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    Logger log = LoggerFactory.getLogger(CartController.class);

    // Method to extract the token from the Authorization header
    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        throw new IllegalArgumentException("Invalid Authorization header format");
    }

    // Method to extract user ID from the token
    private String extractUserIdFromToken(String token) {
        String username = jwtService.extractUsername(extractToken(token));
        User user = userService.getUserByEmail(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user.getId().toString();
    }

    @PostMapping("/add")
    public synchronized ResponseEntity<?> addToCart(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody AddToCartRequest request) {

        String userId;
        try {
            // Extract userId from token
            userId = extractUserIdFromToken(token);
        } catch (Exception e) {
            logger.error("Invalid token: {}", token, e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
        }

        ObjectId userObjectId = new ObjectId(userId);

        // Ensure only one cart exists
        try {
            List<Cart> existingCarts = cartRepository.findAllByUserId(userObjectId);

            // Clean duplicates if found
            if (existingCarts.size() > 1) {
                for (int i = 1; i < existingCarts.size(); i++) {
                    cartRepository.delete(existingCarts.get(i));
                }
            }
        } catch (Exception ex) {
            logger.error("Error during cart cleanup for userId: {}", userId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Cart cleanup failed"));
        }

        // Add to cart logic via service
        try {
            CartDTO updatedCart = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Unexpected error while adding to cart for userId: {}", userId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to add item to cart"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable String userId) {
        Optional<Cart> cart = cartService.getCartByUserId(userId);

        if (cart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        }

        List<CartItemResponse> items = cart.get().getItems().stream().map(item ->
                new CartItemResponse(
                        item.getProduct().getId().toHexString(), // Convert ObjectId to String
                        item.getProduct().getName(),
                        item.getProduct().getImage(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("items", items));
    }

    @PutMapping("/{userId}/items/{productId}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable String userId, @PathVariable String productId, @RequestParam String action) {
        logger.info("🔄 Received request to update cart item - User ID: {}, Product ID: {}, Action: {}", userId, productId, action);

        // Validate action parameter
        if (!action.equals("increase") && !action.equals("decrease")) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid action. Must be 'increase' or 'decrease'.", null));
        }

        try {
            // Call the service method to update the cart
            CartDTO updatedCart = cartService.updateItemQuantity(userId, productId, action);
            return ResponseEntity.ok(new ApiResponse(true, "Cart updated successfully", updatedCart));
        } catch (IllegalArgumentException e) {
            logger.error("❌ Invalid request: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            logger.error("🛑 Resource not found: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), null));
        } catch (Exception e) {
            logger.error("⚠️ Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "An unexpected error occurred. Please try again.", null));
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable String productId, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            logger.info("🗑 Request to remove product - ID: {}", productId);

            // Validate ObjectId format
            if (!ObjectId.isValid(productId)) {
                logger.error("❌ Invalid ObjectId format: {}", productId);
                return ResponseEntity.badRequest().body("Invalid ObjectId format.");
            }

            String userEmail = userDetails.getUsername();
            logger.info("👤 Authenticated user: {}", userEmail);

            // Call service to remove product
            boolean removed = cartService.removeProductFromCart(userEmail, productId);

            if (removed) {
                logger.info("✅ Product removed successfully.");
                return ResponseEntity.ok("Product removed successfully.");
            } else {
                logger.warn("⚠️ Product not found in cart.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart.");
            }
        } catch (UserNotFoundException e) {
            logger.error("❌ User not found: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (ResourceNotFoundException e) {
            logger.error("❌ Cart not found for user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found.");
        } catch (Exception e) {
            logger.error("❌ Exception while removing product {}: {}", productId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing product: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeItemsFromCart(
            @PathVariable String userId,
            @RequestBody Map<String, List<String>> requestBody) {

        List<String> productIds = requestBody.get("productIds");

        if (productIds == null || productIds.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ Product IDs are required");
        }

        cartService.removeCartItems(userId, productIds);
        return ResponseEntity.ok().body("✅ Cart items removed successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItemsFromCart(@RequestBody CartRemoveRequest request) {
        // Log the incoming request for debugging
        System.out.println("Request to remove items for user: " + request.getUserId());
        System.out.println("Product IDs to remove: " + request.getProductIds());

        try {
            // Validate the payload
            if (request.getUserId() == null || request.getProductIds() == null || request.getProductIds().isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid request payload");
            }

            // Delegate to the service layer
            cartService.removeItems(request.getUserId(), request.getProductIds());
            return ResponseEntity.ok("Cart items removed successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to remove items from cart");
        }
    }
}


