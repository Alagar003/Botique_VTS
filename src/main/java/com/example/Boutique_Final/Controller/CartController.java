////package com.example.Boutique_Final.Controller;
////
////import com.example.Boutique_Final.Mapper.CartMapper;
////import com.example.Boutique_Final.dto.CartDTO;
////import com.example.Boutique_Final.dto.AddToCartRequest;
////import com.example.Boutique_Final.dto.UpdateQuantityRequest;
////import com.example.Boutique_Final.model.Cart;
////import com.example.Boutique_Final.service.CartService;
////import com.example.Boutique_Final.service.JwtService;
////import lombok.RequiredArgsConstructor;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.Optional;
////
////@RestController
////@RequestMapping("/api/cart")
////@CrossOrigin(origins = "http://localhost:3003")
////@RequiredArgsConstructor
////public class CartController {
////
////    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
////    private final CartService cartService;
////    private final JwtService jwtService;
////    private final CartMapper cartMapper;
////
////    private String extractToken(String token) {
////        if (token != null && token.startsWith("Bearer ")) {
////            return token.substring(7);
////        }
////        throw new IllegalArgumentException("Invalid Authorization header format");
////    }
////
////    @PostMapping("/add")
////    public ResponseEntity<CartDTO> addToCart(@RequestHeader(value = "Authorization", required = false) String token,
////                                             @RequestBody AddToCartRequest request) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////
////        // Validate request body
////        if (request.getProductId() == null || request.getQuantity() <= 0) {
////            logger.error("Invalid product ID or quantity.");
////            return ResponseEntity.badRequest().body(null);
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            logger.info("Extracted User ID: {}", userId);
////            CartDTO cartDTO = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
////            return ResponseEntity.ok(cartDTO);
////        } catch (Exception ex) {
////            logger.error("Error adding to cart: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
////        }
////    }
////
////    @GetMapping()
////    public ResponseEntity<CartDTO> getCart(@RequestHeader(value = "Authorization", required = false) String token) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            logger.info("Fetching cart for user: {}", userId);
////
////            Optional<Cart> cart = cartService.getCartByUserId(userId);
////            return cart.map(value -> ResponseEntity.ok(cartMapper.toDTO(value)))
////                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
////        } catch (Exception ex) {
////            logger.error("Error fetching cart: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
////        }
////    }
////
////    @GetMapping("/{userId}")
////    public ResponseEntity<CartDTO> getCartById(@PathVariable String userId) {
////        Optional<Cart> cart = cartService.getCartByUserId(userId);
////        return cart.map(value -> ResponseEntity.ok(cartMapper.toDTO(value)))
////                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
////    }
////
////    @DeleteMapping("/clear")
////    public ResponseEntity<Void> clearCart(@RequestHeader(value = "Authorization", required = false) String token) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            cartService.clearCart(userId);
////            return ResponseEntity.ok().build();
////        } catch (Exception ex) {
////            logger.error("Error clearing cart: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////        }
////    }
////}
//
//
//package com.example.Boutique_Final.Controller;
//
//import com.example.Boutique_Final.Mapper.CartMapper;
//import com.example.Boutique_Final.dto.*;
//import com.example.Boutique_Final.exception.InvalidRequestException;
//import com.example.Boutique_Final.exception.ResourceNotFoundException;
//import com.example.Boutique_Final.exception.UserNotFoundException;
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.User;
//import com.example.Boutique_Final.repositories.CartRepository;
//import com.example.Boutique_Final.service.CartService;
//import com.example.Boutique_Final.service.JwtService;
//import com.example.Boutique_Final.service.ProductService;
//import com.example.Boutique_Final.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/cart")
//@CrossOrigin(origins = "http://localhost:3001")
//@RequiredArgsConstructor
//public class CartController {
//
//    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
//    private final CartService cartService;
//    private final JwtService jwtService;
//    private final CartMapper cartMapper;
//    private final UserService userService;
//    private final CartRepository cartRepository;
//    private final ProductService productService;
//
//    private String extractToken(String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            return token.substring(7);
//        }
//        throw new IllegalArgumentException("Invalid Authorization header format");
//    }
//
////    @PostMapping("/add")
////    public ResponseEntity<?> addToCart(
////            @RequestHeader(value = "Authorization") String token,
////            @RequestBody AddToCartRequest request) {
////
////        // Extract userId from the token
////        String userId;
////        try {
////            userId = extractUserIdFromToken(token);
////        } catch (Exception e) {
////            logger.error("Failed to extract user ID from token: {}", e.getMessage());
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
////        }
////
////        try {
////            // Validate request parameters
////            if (request.getProductId() == null || request.getQuantity() <= 0) {
////                return ResponseEntity.badRequest().body(Map.of("message", "Invalid product ID or quantity"));
////            }
////
////            // Log the values for debugging
////            logger.info("User  ID: {}, Product ID: {}, Quantity: {}", userId, request.getProductId(), request.getQuantity());
////
////            // Call the service to add the item to the cart
////            CartDTO updatedCart = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
////            return ResponseEntity.ok(updatedCart);
////        } catch (ResourceNotFoundException ex) {
////            logger.error("Resource not found: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Product not found"));
////        } catch (IllegalArgumentException ex) {
////            logger.error("Invalid argument: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
////        } catch (Exception ex) {
////            logger.error("Error adding item to cart: {}", ex.getMessage(), ex);
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to add item to cart"));
////        }
////    }
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addToCart(
//            @RequestHeader(value = "Authorization") String token,
//            @RequestBody AddToCartRequest request) {
//        logger.info("Received request to add to cart: productId={}, quantity={}", request.getProductId(), request.getQuantity());
//
//        // Extract userId from the token
//        String userId;
//        try {
//            userId = extractUserIdFromToken(token);
//        } catch (Exception e) {
//            logger.error("Failed to extract user ID from token: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
//        }
//
//        try {
//            // Validate request parameters
//            if (request.getProductId() == null || request.getQuantity() <= 0) {
//                logger.warn("Invalid request parameters: productId={}, quantity={}", request.getProductId(), request.getQuantity());
//                return ResponseEntity.badRequest().body(Map.of("message", "Invalid product ID or quantity"));
//            }
//
//            // Log the values for debugging
//            logger.info("User ID: {}, Product ID: {}, Quantity: {}", userId, request.getProductId(), request.getQuantity());
//
//            // Call the service to add the item to the cart
//            CartDTO updatedCart = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
//            logger.info("Cart updated successfully: {}", updatedCart);
//            return ResponseEntity.ok(updatedCart);
//        } catch (ResourceNotFoundException ex) {
//            logger.error("Resource not found: {}", ex.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Product not found"));
//        } catch (IllegalArgumentException ex) {
//            logger.error("Invalid argument: {}", ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
//        } catch (Exception ex) {
//            logger.error("Error adding item to cart: {}", ex.getMessage(), ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to add item to cart"));
//        }
//    }
//
//
//
//
//
////    @PostMapping("/add")
////    public ResponseEntity<CartDTO> addToCart(
////            @RequestParam String userId,
////            @RequestParam String productId,
////            @RequestParam int quantity) {
////        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
////    }
//
//
//
//
//
////    @PostMapping("/add")
////    public ResponseEntity<CartDTO> addToCart(@RequestBody AddToCartRequest request) {
////        System.out.println("Received: userId=" + request.getUserId() +
////                ", productId=" + request.getProductId() +
////                ", quantity=" + request.getQuantity());
////
////        CartDTO updatedCart = cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity());
////        return ResponseEntity.ok(updatedCart);
////    }
//
//
////    @PostMapping("/add")
////    public ResponseEntity<CartDTO> addToCart(@PathVariable String userId,
////                                             @PathVariable String productId,
////                                             @RequestParam("quantity") int quantity) {
////        try {
////            CartDTO cart = cartService.addToCart(userId, productId, quantity);
////            return ResponseEntity.ok(cart);
////        } catch (UserNotFoundException | ResourceNotFoundException e) {
////            return ResponseEntity.status(404).body(null); // Handle errors with appropriate status and response
////        }
////    }
//
//
////    @PostMapping("/{user/add")
////    public CartDTO addToCart(@PathVariable String userId,
////                             @PathVariable String productId,
////                             @RequestParam int quantity) {
////        try {
////            ProductDTO product = productService.getProductById(productId);
////
////            if (product == null) {
////                throw new ResourceNotFoundException("Product not found");
////            }
////
////            if (product.getQuantity() < quantity) {
////                throw new InvalidRequestException("Not enough stock available");
////            }
////
////                return cartService.addToCart(userId, productId, quantity);
////
////        } catch (Exception e) {
////            throw new RuntimeException("Failed to add item to cart: " + e.getMessage());
////        }
////    }
//
//
//
//
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable String userId) {
//        System.out.println("Fetching cart for userId: " + userId);
//
//        if (!ObjectId.isValid(userId)) {
//            return ResponseEntity.badRequest().build(); // Return 400 Bad Request with no body
//        }
//
//        Optional<Cart> cartOptional = cartService.getCartByUserId(userId);
//
//        return cartOptional
//                .map(cart -> ResponseEntity.ok(new CartDTO(cart))) // Returns ResponseEntity<CartDTO>
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Returns 404 with no body
//    }
//
//
//
//
//
//
//
//
//    @DeleteMapping("/clear/{userId}")
//    public ResponseEntity<String> clearCart(@PathVariable String userId) {
//        cartService.clearCart(userId);
//        return ResponseEntity.ok("Cart cleared successfully");
//    }
//
//
//
//    // Example method to extract userId from the token
//    private String extractUserIdFromToken(String token) {
//        // Extract the username (email) from the token
//        String username = jwtService.extractUsername(extractToken(token));
//
//        // Fetch the user from the database using the email
//        User user = userService.getUserByEmail(username);
//        if (user == null) {
//            throw new ResourceNotFoundException("User not found");
//        }
//
//        // Assuming your user model has a field 'id' (or '_id' for MongoDB ObjectId)
//        // Return the user's ID as a string (can be ObjectId in MongoDB)
//        return user.getId().toString();  // Adjust based on how your 'User' model stores the ID
//    }
//
////    @PostMapping("/add")
////    public ResponseEntity<?> addToCart(@RequestHeader(value = "Authorization", required = false) String token,
////                                       @RequestBody AddToCartRequest request) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Authorization token is required"));
////        }
////
////        if (request.getProductId() == null || request.getQuantity() <= 0) {
////            logger.error("Invalid product ID or quantity.");
////            return ResponseEntity.badRequest().body(Map.of("message", "Product ID and quantity must be valid"));
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            logger.info("Extracted User ID: {}", userId);
////            CartDTO cartDTO = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
////            return ResponseEntity.ok(cartDTO);
////        } catch (Exception ex) {
////            logger.error("Error adding to cart: {}", ex.getMessage(), ex); // Log the full exception
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to add item to cart"));
////        }
////    }
//
////    @GetMapping()
////    public ResponseEntity<CartDTO> getCart(@RequestHeader(value = "Authorization", required = false) String token) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            logger.info("Fetching cart for user: {}", userId);
////
////            Optional<Cart> cart = cartService.getCartByUserId(userId);
////            return cart.map(value -> ResponseEntity.ok(cartMapper.toDTO(value)))
////                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
////        } catch (Exception ex) {
////            logger.error("Error fetching cart: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
////        }
////    }
////    @GetMapping("/{userId}")
////    public ResponseEntity<CartDTO> getCartById(@PathVariable String userId) {
////        Optional<Cart> cart = cartService.getCartByUserId(userId);
////
////        if (cart.isPresent()) {
////            CartDTO cartDTO = cartMapper.toDTO(cart.get());
////            // Log the retrieved cart data
////            System.out.println("Retrieved Cart: " + cartDTO);
////            return ResponseEntity.ok(cartDTO);
////        } else {
////            // Log the error
////            System.err.println("Cart not found for userId: " + userId);
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 without a body
////        }
////    }
////    @DeleteMapping("/clear")
////    public ResponseEntity<Void> clearCart(@RequestHeader(value = "Authorization", required = false) String token) {
////        if (token == null) {
////            logger.error("Authorization token is missing");
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////
////        try {
////            String userId = jwtService.extractUsername(extractToken(token));
////            cartService.clearCart(userId);
////            return ResponseEntity.ok().build();
////        } catch (Exception ex) {
////            logger.error("Error clearing cart: {}", ex.getMessage());
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        }
////    }
//
//    @PostMapping("/updateQuantity")
//    public ResponseEntity<CartDTO> updateQuantity(@RequestHeader(value = "Authorization", required = false) String token,
//                                                  @RequestBody UpdateQuantityRequest request) {
//        if (token == null) {
//            logger.error("Authorization token is missing");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        if (request.getProductId() == null || request.getAction() == null) {
//            logger.error("Invalid product ID or action.");
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        try {
//            String userId = jwtService.extractUsername(extractToken(token));
//            logger.info("Updating quantity for product ID: {} for user: {}", request.getProductId(), userId);
//
//            CartDTO updatedCart = cartService.updateItemQuantity(userId, request.getProductId(), request.getAction());
//            return ResponseEntity.ok(updatedCart);
//        } catch (Exception ex) {
//            logger.error("Error updating quantity: {}", ex.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//}



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


//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getCart(@PathVariable String userId) {
//        Optional<Cart> optionalCart = cartService.getCartByUserId(userId);
//        Cart cart = optionalCart.orElse(null);
//
//
//        if (cart == null) {
//            return ResponseEntity.ok(Collections.singletonMap("cartItems", new ArrayList<>()));
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", cart.getId());
//        response.put("userId", cart.getUserId());
//        response.put("cartItems", cart.getItems()); // Ensure frontend gets `cartItems`
//        response.put("totalPrice", cart.getTotalPrice());
//
//        return ResponseEntity.ok(response);
//    }


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








//    @DeleteMapping("/clear/{userId}")
//    public ResponseEntity<String> clearCart(@PathVariable String userId) {
//        logger.info("Clearing cart for userId: {}", userId);
//        cartService.clearCart(userId);
//        return ResponseEntity.ok("Cart cleared successfully");
//    }
//@PutMapping("/updateQuantity")
//public ResponseEntity<?> updateCart(
//        @RequestBody Map<String, String> request,
//        @AuthenticationPrincipal UserDetails userDetails
//) {
//    String userId = request.get("userId");
//
//    // 🔍 Debug: Check if authentication is working
//    if (userDetails == null) {
//        System.out.println("❌ DEBUG: userDetails is NULL. Authentication might not be working.");
//    } else {
//        System.out.println("✅ DEBUG: Extracted user from JWT: " + userDetails.getUsername());
//    }
//
//    // If userId is null, get it from userDetails
//    if (userId == null && userDetails != null) {
//        userId = userDetails.getUsername();
//    }
//
//    String productId = request.get("productId");
//    String action = request.get("action");
//
//    System.out.println("📝 Received request - User ID: " + userId + ", Product ID: " + productId + ", Action: " + action);
//
//    // ✅ Validate input fields
//    if (userId == null || productId == null || action == null) {
//        return ResponseEntity.badRequest().body(Map.of("message", "Invalid request data"));
//    }
//
//    // ✅ Convert userId to ObjectId if stored as ObjectId in MongoDB
//    ObjectId userObjectId;
//    try {
//        userObjectId = new ObjectId(userId);
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(Map.of("message", "Invalid User ID format"));
//    }
//
//    // ✅ Convert productId to ObjectId if needed
//    if (!ObjectId.isValid(productId)) {
//        return ResponseEntity.badRequest().body(Map.of("message", "Invalid Product ID format"));
//    }
//    ObjectId productObjectId = new ObjectId(productId);
//
//    try {
//        CartDTO updatedCart = cartService.updateItemQuantity(userObjectId.toString(), productObjectId.toString(), action);
//        return ResponseEntity.ok(updatedCart);
//    } catch (ResourceNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An unexpected error occurred"));
//    }
//}





    @PutMapping("/updateQuantity")
    public ResponseEntity<?> updateCart(@RequestBody Map<String, Object> requestBody,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        String userIdStr = (String) requestBody.get("userId");
        String productIdStr = (String) requestBody.get("productId");
        String action = (String) requestBody.get("action");

        logger.debug("📝 Received request - User ID: {}, Product ID: {}, Action: {}", userIdStr, productIdStr, action);

        ObjectId userId;
        try {
            userId = new ObjectId(userIdStr); // Convert String to ObjectId
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid User ID format");
        }

        // Fetch or create a new cart
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            logger.debug("🛑 No cart found for userId: {}, creating a new cart.", userId);
            return new Cart(userId); // Use the correct constructor
        });

        // Fetch product from the database
        // Fetch product from the database (assuming product ID is stored as String)
        Product product = productRepository.findById(productIdStr)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productIdStr));

        // Find if the product already exists in the cart
        Optional<CartItem> cartItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            if ("increase".equals(action)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else if ("decrease".equals(action) && cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            }
        } else {
            logger.debug("🆕 Adding new product to cart.");
            cart.getItems().add(new CartItem(product, 1)); // Now pass a Product object
        }

        // Recalculate total price
        cart.calculateTotalPrice();

        // Save updated cart
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
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

    }


