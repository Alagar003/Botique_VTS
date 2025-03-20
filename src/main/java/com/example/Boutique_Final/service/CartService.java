//package com.example.Boutique_Final.service;
//
//import com.example.Boutique_Final.Mapper.CartMapper;
//import com.example.Boutique_Final.dto.CartDTO;
//import com.example.Boutique_Final.dto.CartItemDTO;
//import com.example.Boutique_Final.exception.ResourceNotFoundException;
//import com.example.Boutique_Final.exception.UserNotFoundException;
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import com.example.Boutique_Final.model.User;
//import com.example.Boutique_Final.repositories.CartRepository;
//import com.example.Boutique_Final.repositories.ProductRepository;
//import com.example.Boutique_Final.repositories.UserRepository;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class CartService {
//    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
//
//    private final CartRepository cartRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final CartMapper cartMapper;
//
//    public CartService(CartRepository cartRepository, UserRepository userRepository,
//                       ProductRepository productRepository, CartMapper cartMapper) {
//        this.cartRepository = cartRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.cartMapper = cartMapper;
//    }
//
//    public CartDTO getOrCreateCart(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
//        return getOrCreateCart(user);
//    }
//
//    private CartDTO getOrCreateCart(User user) {
//        Cart cart = cartRepository.findByUserId(user.getId())
//                .orElseGet(() -> {
//                    Cart newCart = new Cart();
//                    newCart.setUserId(user.getId());
//                    newCart.setItems(List.of());
//                    return cartRepository.save(newCart);
//                });
//
//        return cartMapper.toDTO(cart);
//    }
//
//
////    public CartDTO addToCart(String email, String productId, int quantity) {
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
////
////        ObjectId userObjectId = user.getId(); // Direct assignment, assuming user.getId() is already an ObjectId.
////
////        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
////            Cart newCart = new Cart();
////            newCart.setUserId(userObjectId);
////            newCart.setItems(new ArrayList<>());
////            return cartRepository.save(newCart);
////        });
////
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
////
////        cart.getItems().stream()
////                .filter(item -> item.getProduct().getId().equals(product.getId()))
////                .findFirst()
////                .ifPresentOrElse(
////                        item -> item.setQuantity(item.getQuantity() + quantity),
////                        () -> cart.getItems().add(new CartItem(product, quantity))
////                );
////
////        return cartMapper.toDTO(cartRepository.save(cart));
////    }
////
////
////
////    public CartDTO updateItemQuantity(String email, String productId, String action) {
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
////
////        Cart cart = cartRepository.findByUserId(user.getId())
////                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user ID: " + user.getId()));
////
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
////
////        cart.getItems().removeIf(item -> {
////            if (item.getProduct().getId().equals(product.getId())) {
////                if ("decrease".equals(action) && item.getQuantity() > 1) {
////                    item.setQuantity(item.getQuantity() - 1);
////                    return false;
////                }
////                return true;
////            }
////            return false;
////        });
////
////        return cartMapper.toDTO(cartRepository.save(cart));
////    }
//
//
////    public CartDTO addToCart(String email, String productId, int quantity) {
////        // Retrieve the user by email
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UserNotFoundException("User  not found for email: " + email));
////
////        ObjectId userObjectId = user.getId(); // Assuming user.getId() is already an ObjectId.
////
////        // Retrieve or create the user's cart
////        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
////            Cart newCart = new Cart();
////            newCart.setUserId(userObjectId);
////            newCart.setItems(new ArrayList<>());
////            return cartRepository.save(newCart);
////        });
////
////        // Retrieve the product by ID
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
////
////        // Log the current state of the cart before adding the item
////        logger.info("Current cart items before adding: {}", cart.getItems());
////
////        // Check if the product is already in the cart
////        cart.getItems().stream()
////                .filter(item -> item.getProduct().getId().equals(product.getId()))
////                .findFirst()
////                .ifPresentOrElse(
////                        item -> {
////                            // If the item exists, update the quantity
////                            item.setQuantity(item.getQuantity() + quantity);
////                            logger.info("Updated quantity for product ID {}: new quantity is {}", productId, item.getQuantity());
////                        },
////                        () -> {
////                            // If the item does not exist, add a new CartItem
////                            CartItem newItem = new CartItem(product, quantity);
////                            cart.getItems().add(newItem);
////                            logger.info("Added new item to cart: product ID {}, quantity {}", productId, quantity);
////                        }
////                );
////
////        // Save the updated cart and log the new state
////        Cart updatedCart = cartRepository.save(cart);
////        logger.info("Cart updated successfully: {}", updatedCart.getItems());
////
////        // Map the updated cart to DTO and return
////        return cartMapper.toDTO(updatedCart);
////    }
//
//
////    public CartDTO addToCart(String email, String productId, int quantity) {
////        // Retrieve the user by email
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UserNotFoundException("User  not found for email: " + email));
////
////        ObjectId userObjectId = user.getId(); // Assuming user.getId() is already an ObjectId.
////
////        // Retrieve or create the user's cart
////        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
////            Cart newCart = new Cart();
////            newCart.setUserId(userObjectId);
////            newCart.setItems(new ArrayList<>());
////            return cartRepository.save(newCart);
////        });
////
////        // Retrieve the product by ID
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
////
////        // Check if the product is already in the cart
////        cart.getItems().stream()
////                .filter(item -> item.getProduct().getId().equals(product.getId()))
////                .findFirst()
////                .ifPresentOrElse(
////                        item -> {
////                            // If the item exists, update the quantity
////                            item.setQuantity(item.getQuantity() + quantity);
////                        },
////                        () -> {
////                            // If the item does not exist, add a new CartItem
////                            CartItem newItem = new CartItem(product, quantity);
////                            cart.getItems().add(newItem);
////                        }
////                );
////
////        // Save the updated cart
////        Cart updatedCart = cartRepository.save(cart);
////
////        // Map the updated cart to DTO and return
////        return cartMapper.toDTO(updatedCart);
////    }
//
//
//
//    private Cart createNewCartForUser (String userId) {
//        // Fetch the user from the database using the userId
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User  not found for ID: " + userId));
//
//        // Create a new cart and set the user
//        Cart newCart = new Cart();
//        newCart.setUser (user); // Set the User object, not just the userId
//
//        return cartRepository.save(newCart); // Save the new cart to the database
//    }
//
////    public CartDTO addToCart(String userId, String productId, int quantity) {
////        // Convert userId to ObjectId
////        ObjectId userObjectId = new ObjectId(userId);
////
////        // Fetch the product to check stock
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
////
////        // Check if enough stock is available
////        if (product.getQuantity() < quantity) {
////            throw new IllegalArgumentException("Not enough stock available");
////        }
////
////        // Find or create a cart for the user
////        Cart cart = cartRepository.findByUserId(userObjectId)
////                .orElseGet(() -> createNewCartForUser (userObjectId)); // Create a new cart if none exists
////
////        // Add product to cart
////        cart.addItem(product, quantity);
////
////        // Decrement stock
////        product.setQuantity(product.getQuantity() - quantity);
////        productRepository.save(product); // Save updated product stock
////
////        // Save updated cart
////        cartRepository.save(cart);
////
////        return cartMapper.toDTO(cart); // Return the updated cart as DTO
////    }
//
//
////    public CartDTO addToCart(String userId, String productId, int quantity) {
////        // Convert userId to ObjectId
////        ObjectId userObjectId = new ObjectId(userId);
////
////        // Fetch the product to check stock
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
////
////        // Check if enough stock is available
////        if (product.getQuantity() < quantity) {
////            throw new IllegalArgumentException("Not enough stock available");
////        }
////
////        // Find or create a cart for the user
////        Cart cart = cartRepository.findByUserId(userObjectId)
////                .orElseGet(() -> createNewCartForUser (userObjectId)); // Create a new cart if none exists
////
////        // Add product to cart
////        cart.addItem(product, quantity);
////
////        // Decrement stock
////        product.setQuantity(product.getQuantity() - quantity);
////        productRepository.save(product); // Save updated product stock
////
////        // Calculate total price
////        double totalPrice = cart.getItems().stream()
////                .mapToDouble(item -> {
////                    Double price = item.getPrice(); // Get the price of the item
////                    return (price != null ? price : 0.0) * item.getQuantity(); // Handle null price
////                })
////                .sum();
////        cart.setTotalPrice(totalPrice); // Update the cart's total price
////
////        // Save updated cart
////        cartRepository.save(cart);
////
////        return cartMapper.toDTO(cart); // Return the updated cart as DTO
////    }
//
//
//    public CartDTO addToCart(String userId, String productId, int quantity) {
//        // Fetch user using String ID
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
//
//        // Fetch product using String ID (no conversion needed)
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
//
//        ObjectId userObjectId = new ObjectId(userId); // Convert only for CartRepository
//        Cart cart = cartRepository.findByUserId(userObjectId)
//                .orElse(new Cart(user));
//
//        cart.addItem(product, quantity);
//        cartRepository.save(cart);
//
//        return new CartDTO(cart);
//    }
//
//
//
//
//
//
//
//
//
//    // Method to create a new cart for the user
//    private Cart createNewCartForUser (ObjectId userId) {
//        // Fetch the user from the database using the userId
//        User user = userRepository.findById(String.valueOf(userId))
//                .orElseThrow(() -> new ResourceNotFoundException("User  not found for ID: " + userId));
//
//        // Create a new cart and set the user
//        Cart newCart = new Cart();
//        newCart.setUser (user); // Set the User object
//
//        return cartRepository.save(newCart); // Save the new cart to the database
//    }
//
//
//
//
//    public void clearCart(String userId) {
//        ObjectId userObjectId = new ObjectId(userId);
//        cartRepository.deleteByUserId(userObjectId);
//    }
//
//
//    public Optional<Cart> getCartByUserId(String userId) {
//        // Convert userId to ObjectId
//        ObjectId objectId = new ObjectId(userId);
//        return cartRepository.findByUserId(objectId);
//    }
//
//
//    public CartDTO updateItemQuantity(String email, String productId, String action) {
//        // Retrieve the user by email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User  not found for email: " + email));
//
//        // Retrieve the user's cart
//        Cart cart = cartRepository.findByUserId(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user ID: " + user.getId()));
//
//        // Log the current state of the cart
//        logger.info("Current cart items: {}", cart.getItems());
//
//        // Check if the product exists in the cart
//        CartItem cartItem = cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().toString().equals(productId)) // Ensure proper comparison
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart with ID: " + productId));
//
//        // Update the quantity based on the action
//        if ("increase".equals(action)) {
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//        } else if ("decrease".equals(action)) {
//            if (cartItem.getQuantity() > 1) {
//                cartItem.setQuantity(cartItem.getQuantity() - 1);
//            } else {
//                // Optionally, remove the item if quantity is 1 and decrease is requested
//                cart.getItems().remove(cartItem);
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid action: " + action);
//        }
//
//        // Save the updated cart
//        Cart updatedCart = cartRepository.save(cart);
//
//        // Map the updated cart to DTO and return
//        return cartMapper.toDTO(updatedCart);
//    }
//}


//
//package com.example.Boutique_Final.service;
//
//import com.example.Boutique_Final.Mapper.CartMapper;
//import com.example.Boutique_Final.Mapper.CommentMapper;
//import com.example.Boutique_Final.dto.CartDTO;
//import com.example.Boutique_Final.dto.CartItemDTO;
//import com.example.Boutique_Final.dto.ProductDTO;
//import com.example.Boutique_Final.exception.InsufficientStockException;
//import com.example.Boutique_Final.exception.ResourceNotFoundException;
//import com.example.Boutique_Final.exception.UserNotFoundException;
//import com.example.Boutique_Final.model.*;
//import com.example.Boutique_Final.repositories.CartRepository;
//import com.example.Boutique_Final.repositories.ProductRepository;
//import com.example.Boutique_Final.repositories.UserRepository;
//import com.mongodb.DuplicateKeyException;
//import org.bson.types.ObjectId;
//import com.example.Boutique_Final.Mapper.CommentMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.example.Boutique_Final.model.Product;
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class CartService {
//    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
//
//    private final CartRepository cartRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final UserService userService;
//    private final CartMapper cartMapper;
//    private final ProductService productService;
//    private final CommentMapper commentMapper;
//
//    public CartService(CartRepository cartRepository, UserRepository userRepository,
//                       ProductRepository productRepository, UserService userService, CartMapper cartMapper, ProductService productService, CommentMapper commentMapper) {
//        this.cartRepository = cartRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.userService = userService;
//        this.cartMapper = cartMapper;
//        this.productService = productService;
//        this.commentMapper = commentMapper;
//    }
//
//    public CartDTO getOrCreateCart(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
//        return getOrCreateCart(user);
//    }
//
//    private CartDTO getOrCreateCart(User user) {
//        Cart cart = cartRepository.findByUserId(user.getId())
//                .orElseGet(() -> {
//                    Cart newCart = new Cart();
//                    newCart.setUserId(user.getId());
//                    newCart.setItems(List.of());
//                    return cartRepository.save(newCart);
//                });
//        return cartMapper.toDTO(cart);
//    }
//
//    public synchronized CartDTO addToCart(String userId, String productId, int quantity) {
//        ObjectId userObjectId = new ObjectId(userId);
//        ObjectId productObjectId = new ObjectId(productId);
//
//        // Fetch the product
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for ID: " + productId));
//
//        // Ensure only one cart exists for the user
//        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUserId(userObjectId);
//            newCart.setItems(new ArrayList<>());
//            newCart.setTotalPrice(0.0);
//            return cartRepository.save(newCart);
//        });
//        // Check if product already exists in cart
//        boolean productExists = false;
//        double totalPrice = 0.0;
//
//        for (CartItem item : cart.getItems()) {
//            if (item.getProduct().getId().equals(productObjectId)) {
//                // Update quantity if product exists
//                int newQuantity = item.getQuantity() + quantity;
//
//                // Check stock availability
//                if (newQuantity > product.getQuantity()) {
//                    throw new IllegalArgumentException("Not enough stock available!");
//                }
//
//                item.setQuantity(newQuantity);
//                productExists = true;
//            }
//            totalPrice += item.getProduct().getPrice().doubleValue() * item.getQuantity();
//        }
//
//        if (!productExists) {
//            // Add product if it doesn't already exist
//            CartItem newItem = new CartItem(product, quantity);
//            cart.getItems().add(newItem);
//            totalPrice += product.getPrice().doubleValue() * quantity;
//        }
//
//        // Deduct stock
//        product.setQuantity(product.getQuantity() - quantity);
//        productRepository.save(product);
//
//        // Update cart total price and save
//        cart.setTotalPrice(totalPrice);
//        cartRepository.save(cart);
//
//        return cartMapper.toDTO(cart);
//    }








//
//    public CartDTO addToCart(String userId, String productId, int quantity) {
//        ObjectId userObjectId = new ObjectId(userId);
//        ObjectId productObjectId = new ObjectId(productId);
//
//        // Fetch user and product
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found for ID: " + userId));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for ID: " + productId));
//
//        // Fetch the cart or create a new one
//        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUserId(userObjectId);
//            newCart.setItems(new ArrayList<>());
//            newCart.setTotalPrice(0.0);
//            return cartRepository.save(newCart);
//        });
//
//        // Merge duplicate products in the cart
//        Map<ObjectId, CartItem> mergedItems = new HashMap<>();
//        double totalPrice = 0.0;
//
//        for (CartItem item : cart.getItems()) {
//            mergedItems.put(item.getProduct().getId(), item);
//            totalPrice += item.getProduct().getPrice().doubleValue() * item.getQuantity();
//        }
//
//        // Check if the product is already in the cart
//        if (mergedItems.containsKey(productObjectId)) {
//            CartItem existingItem = mergedItems.get(productObjectId);
//            int newQuantity = existingItem.getQuantity() + quantity;
//
//            // Stock validation
//            if (newQuantity > product.getQuantity()) {
//                throw new IllegalArgumentException("Cannot add " + quantity + " items. Only " + product.getQuantity() + " left in stock!");
//            }
//
//            existingItem.setQuantity(newQuantity);
//        } else {
//            // Validate stock
//            if (quantity > product.getQuantity()) {
//                throw new IllegalArgumentException("Cannot add " + quantity + " items. Only " + product.getQuantity() + " left in stock!");
//            }
//
//            CartItem newItem = new CartItem(product, quantity);
//            mergedItems.put(productObjectId, newItem);
//        }
//
//        // Deduct stock after validation
//        product.setQuantity(product.getQuantity() - quantity);
//        productRepository.save(product);
//
//        // Recalculate total price
//        totalPrice = 0.0;
//        for (CartItem item : mergedItems.values()) {
//            totalPrice += item.getProduct().getPrice().doubleValue() * item.getQuantity();
//        }
//
//        // Save the updated cart
//        cart.setItems(new ArrayList<>(mergedItems.values()));
//        cart.setTotalPrice(totalPrice);
//        Cart updatedCart = cartRepository.save(cart);
//
//        logger.info("Cart updated successfully. Total price: {}", updatedCart.getTotalPrice());
//
//        return cartMapper.toDTO(updatedCart);
//    }





//    public ResponseEntity<ProductDTO> addToCart(ProductDTO productDTO) {
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
//                null // comments can be null or empty initially
//        );
//
//        // Save the product in the repository
//        Product savedProduct = productRepository.save(product);
//
//        // Construct a ProductDTO to return as the response
//        ProductDTO savedProductDTO = new ProductDTO(
//                savedProduct.getId().toHexString(), // Convert ObjectId to String
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


//    public ResponseEntity<CartDTO> addToCart(String userId, String productId, int quantity) {
//        // Convert the userId from String to ObjectId for product
//        ObjectId userObjectId = new ObjectId(userId);  // Convert userId String to ObjectId
//
//        // Fetch the product as a ProductDTO
//        ProductDTO productDTO = productService.getProductById(productId);
//
//        // Convert String ID to ObjectId for product (if needed)
//        ObjectId productObjectId = new ObjectId(productDTO.getId());
//
//        // Construct a Product entity
//        Product product = new Product(
//                productObjectId,
//                productDTO.getName(),
//                productDTO.getDescription(),
//                productDTO.getPrice(),
//                productDTO.getQuantity(),
//                productDTO.getImage(),
//                productDTO.getCategory(),
//                null
//        );
//
//        // Convert ObjectId back to String for user
//        String userIdString = userObjectId.toHexString();  // Convert ObjectId to String
//
//        // Fetch the user using String userId
//        User user = userService.getUserById(userIdString);  // Pass String userId
//
//        // Add the product to the user's cart
//        Cart cart = cartRepository.findByUserId(userObjectId).orElse(new Cart(user));  // Pass String userId
//        cart.addItem(product, quantity);
//
//        // Save the updated cart
//        cartRepository.save(cart);
//
//        // Convert cart to CartDTO and return
//        CartDTO updatedCartDTO = new CartDTO(cart);
//        return ResponseEntity.ok(updatedCartDTO);
////    }
//
//    public CartDTO updateItemQuantity(String userId, String productId, String action) {
//        ObjectId userObjectId = new ObjectId(userId);
//        ObjectId productObjectId = new ObjectId(productId);
//
//        Cart cart = cartRepository.findByUserId(userObjectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user ID: " + userId));
//
//        CartItem cartItem = cart.getItems().stream()
//                .filter(item -> item.getProduct() != null && item.getProduct().getId().equals(productObjectId))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart: " + productId));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in database: " + productId));
//
//        if ("increase".equalsIgnoreCase(action)) {
//            if (cartItem.getQuantity() >= product.getQuantity()) {
//                throw new IllegalArgumentException("Not enough stock available");
//            }
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//        } else if ("decrease".equalsIgnoreCase(action) && cartItem.getQuantity() > 1) {
//            cartItem.setQuantity(cartItem.getQuantity() - 1);
//        } else {
//            throw new IllegalArgumentException("Invalid action or quantity too low");
//        }
//
//        // 🔥 Update total price
//        cart.setTotalPrice(cart.getItems().stream()
//                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
//                .sum()
//        );
//
//        cartRepository.save(cart);
//        return cartMapper.toDTO(cart);
//    }


//
//    public CartDTO updateItemQuantity(String email, String productId, String action) {
//        // Retrieve user by email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
//
//        // Retrieve user's cart
//        Cart cart = cartRepository.findByUserId(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user ID: " + user.getId()));
//
//        // Log the current state of the cart
//        logger.info("Current cart items: {}", cart.getItems());
//
//        // Check if the product exists in the cart
//        CartItem cartItem = cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().toString().equals(productId)) // Ensure proper comparison
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart with ID: " + productId));
//
//        // Update the quantity based on the action
//        if ("increase".equals(action)) {
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//            logger.info("Increased quantity for product ID {}: new quantity is {}", productId, cartItem.getQuantity());
//        } else if ("decrease".equals(action)) {
//            if (cartItem.getQuantity() > 1) {
//                cartItem.setQuantity(cartItem.getQuantity() - 1);
//                logger.info("Decreased quantity for product ID {}: new quantity is {}", productId, cartItem.getQuantity());
//            } else {
//                throw new IllegalArgumentException("Cannot decrease quantity below 1");
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid action: " + action);
//        }
//
//        // Save the updated cart
//        Cart updatedCart = cartRepository.save(cart);
//        logger.info("Cart updated successfully: {}", updatedCart.getItems());
//
//        return cartMapper.toDTO(updatedCart); // Return the updated cart as DTO
//    }


//    public Optional<Cart> getCartByUserId(String userId) {
//        ObjectId objectId = new ObjectId(userId);
//        return cartRepository.findByUserId(objectId);
////    }
//
//
//    public CartDTO getCartByUserId(String userId) {
//        ObjectId userObjectId = new ObjectId(userId);
//        Optional<Cart> cartOptional = cartRepository.findByUserId(userObjectId);
//        if (cartOptional.isEmpty()) {
//            return null;
//        }
//
//        Cart cart = cartOptional.get();
//        List<CartItemDTO> cartItems = cart.getItems().stream().map(item -> {
//            Product product = productRepository.findById(item.getProduct().getId().toHexString()).orElse(null);
//            return new CartItemDTO(
//                    product != null ? product.getId().toHexString() : "",
//                    product != null ? product.getName() : "Unknown",
//                    product != null ? product.getPrice() : 0,
//                    item.getQuantity(),
//                    product != null ? product.getImage() : ""
//            );
//        }).collect(Collectors.toList());
//
//        return new CartDTO(
//                cart.getId().toHexString(),
//                cart.getUser().getId().toHexString(),
//                cart.getUser().getEmail(),
//                cartItems,
//                cart.getTotalPrice()
//        );
//    }
//}


//    public CartDTO getCartByUserId(String userId) {
//        ObjectId userObjectId = new ObjectId(userId);
//        Optional<Cart> cartOptional = cartRepository.findByUserId(userObjectId);
//
//        if (cartOptional.isEmpty()) {
//            return null;
//        }
//
//        Cart cart = cartOptional.get();
//        List<CartItemDTO> cartItems = cart.getItems().stream().map(item -> {
//            Product product = productRepository.findById(item.getProduct().getId().toHexString()).orElse(null);
//            return new CartItemDTO(
//                    product != null ? product.getId().toHexString() : "",
//                    product != null ? product.getName() : "Unknown",
//                    product != null ? product.getPrice() : 0,  // ✅ Now fetching price correctly
//                    item.getQuantity(),
//                    product != null ? product.getImage() : ""
//            );
//        }).collect(Collectors.toList());
//
//        return new CartDTO(cart.getId().toHexString(), cart.getUser().getId().toHexString(), cart.getUser().getEmail(), cartItems, cart.getTotalPrice());
//    }
//
//}

package com.example.Boutique_Final.service;

import com.example.Boutique_Final.Mapper.CartMapper;
import com.example.Boutique_Final.dto.CartDTO;
import com.example.Boutique_Final.dto.CartItemDTO;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import com.example.Boutique_Final.exception.UserNotFoundException;
import com.example.Boutique_Final.model.Cart;
import com.example.Boutique_Final.model.CartItem;
import com.example.Boutique_Final.model.Product;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.CartRepository;
import com.example.Boutique_Final.repositories.ProductRepository;
import com.example.Boutique_Final.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, UserRepository userRepository,
                       ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    public CartDTO getOrCreateCart(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
        return getOrCreateCart(user);
    }

    private CartDTO getOrCreateCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(user.getId());
                    newCart.setItems(List.of());
                    return cartRepository.save(newCart);
                });

        return cartMapper.toDTO(cart);
    }


//    public CartDTO addToCart(String email, String productId, int quantity) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
//
//        ObjectId userObjectId = user.getId(); // Direct assignment, assuming user.getId() is already an ObjectId.
//
//        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUserId(userObjectId);
//            newCart.setItems(new ArrayList<>());
//            return cartRepository.save(newCart);
//        });
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
//
//        cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().equals(product.getId()))
//                .findFirst()
//                .ifPresentOrElse(
//                        item -> item.setQuantity(item.getQuantity() + quantity),
//                        () -> cart.getItems().add(new CartItem(product, quantity))
//                );
//
//        return cartMapper.toDTO(cartRepository.save(cart));
//    }
//
//
//
//    public CartDTO updateItemQuantity(String email, String productId, String action) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
//
//        Cart cart = cartRepository.findByUserId(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user ID: " + user.getId()));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
//
//        cart.getItems().removeIf(item -> {
//            if (item.getProduct().getId().equals(product.getId())) {
//                if ("decrease".equals(action) && item.getQuantity() > 1) {
//                    item.setQuantity(item.getQuantity() - 1);
//                    return false;
//                }
//                return true;
//            }
//            return false;
//        });
//
//        return cartMapper.toDTO(cartRepository.save(cart));
//    }


//    public CartDTO addToCart(String email, String productId, int quantity) {
//        // Retrieve the user by email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User  not found for email: " + email));
//
//        ObjectId userObjectId = user.getId(); // Assuming user.getId() is already an ObjectId.
//
//        // Retrieve or create the user's cart
//        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUserId(userObjectId);
//            newCart.setItems(new ArrayList<>());
//            return cartRepository.save(newCart);
//        });
//
//        // Retrieve the product by ID
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
//
//        // Log the current state of the cart before adding the item
//        logger.info("Current cart items before adding: {}", cart.getItems());
//
//        // Check if the product is already in the cart
//        cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().equals(product.getId()))
//                .findFirst()
//                .ifPresentOrElse(
//                        item -> {
//                            // If the item exists, update the quantity
//                            item.setQuantity(item.getQuantity() + quantity);
//                            logger.info("Updated quantity for product ID {}: new quantity is {}", productId, item.getQuantity());
//                        },
//                        () -> {
//                            // If the item does not exist, add a new CartItem
//                            CartItem newItem = new CartItem(product, quantity);
//                            cart.getItems().add(newItem);
//                            logger.info("Added new item to cart: product ID {}, quantity {}", productId, quantity);
//                        }
//                );
//
//        // Save the updated cart and log the new state
//        Cart updatedCart = cartRepository.save(cart);
//        logger.info("Cart updated successfully: {}", updatedCart.getItems());
//
//        // Map the updated cart to DTO and return
//        return cartMapper.toDTO(updatedCart);
//    }


//    public CartDTO addToCart(String email, String productId, int quantity) {
//        // Retrieve the user by email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User  not found for email: " + email));
//
//        ObjectId userObjectId = user.getId(); // Assuming user.getId() is already an ObjectId.
//
//        // Retrieve or create the user's cart
//        Cart cart = cartRepository.findByUserId(userObjectId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUserId(userObjectId);
//            newCart.setItems(new ArrayList<>());
//            return cartRepository.save(newCart);
//        });
//
//        // Retrieve the product by ID
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
//
//        // Check if the product is already in the cart
//        cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().equals(product.getId()))
//                .findFirst()
//                .ifPresentOrElse(
//                        item -> {
//                            // If the item exists, update the quantity
//                            item.setQuantity(item.getQuantity() + quantity);
//                        },
//                        () -> {
//                            // If the item does not exist, add a new CartItem
//                            CartItem newItem = new CartItem(product, quantity);
//                            cart.getItems().add(newItem);
//                        }
//                );
//
//        // Save the updated cart
//        Cart updatedCart = cartRepository.save(cart);
//
//        // Map the updated cart to DTO and return
//        return cartMapper.toDTO(updatedCart);
//    }



    private Cart createNewCartForUser (String userId) {
        // Fetch the user from the database using the userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User  not found for ID: " + userId));

        // Create a new cart and set the user
        Cart newCart = new Cart();
        newCart.setUser (user); // Set the User object, not just the userId

        return cartRepository.save(newCart); // Save the new cart to the database
    }

    public CartDTO addToCart(String userId, String productId, int quantity) {
        // Convert userId to ObjectId
        ObjectId userObjectId = new ObjectId(userId);

        // Fetch the product to check stock
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Check if enough stock is available
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        // Find or create a cart for the user
        Cart cart = cartRepository.findByUserId(userObjectId)
                .orElseGet(() -> createNewCartForUser(userObjectId)); // Ensure this method also expects ObjectId

        // Add product to cart
        cart.addItem(product, quantity);

        // Decrement stock
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product); // Save updated product stock

        // Save updated cart
        cartRepository.save(cart);

        return cartMapper.toDTO(cart); // Return the updated cart as DTO
    }


//    public CartDTO addToCart(String userId, String productId, int quantity) {
//        // Convert userId to ObjectId
//        ObjectId userObjectId = new ObjectId(userId);
//
//        // Fetch the product to check stock
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//
//        // Check if enough stock is available
//        if (product.getQuantity() < quantity) {
//            throw new IllegalArgumentException("Not enough stock available");
//        }
//
//        // Find or create a cart for the user
//        Cart cart = cartRepository.findByUserId(userObjectId)
//                .orElseGet(() -> createNewCartForUser (userObjectId)); // Create a new cart if none exists
//
//        // Add product to cart
//        cart.addItem(product, quantity);
//
//        // Decrement stock
//        product.setQuantity(product.getQuantity() - quantity);
//        productRepository.save(product); // Save updated product stock
//
//        // Calculate total price
//        double totalPrice = cart.getItems().stream()
//                .mapToDouble(item -> {
//                    Double price = item.getPrice(); // Get the price of the item
//                    return (price != null ? price : 0.0) * item.getQuantity(); // Handle null price
//                })
//                .sum();
//        cart.setTotalPrice(totalPrice); // Update the cart's total price
//
//        // Save updated cart
//        cartRepository.save(cart);
//
//        return cartMapper.toDTO(cart); // Return the updated cart as DTO
//    }


//    public CartDTO addToCart(String userId, String productId, int quantity) {
//        // Fetch user using String ID
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
//
//        // Fetch product using String ID (no conversion needed)
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
//
//        ObjectId userObjectId = new ObjectId(userId); // Convert only for CartRepository
//        Cart cart = cartRepository.findByUserId(userObjectId)
//                .orElse(new Cart(user));
//
//        cart.addItem(product, quantity);
//        cartRepository.save(cart);
//
//        return new CartDTO(cart);
//    }
//








    // Method to create a new cart for the user
    private Cart createNewCartForUser(ObjectId userId) {
        User user = userRepository.findById(userId.toHexString())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + userId));

        Cart newCart = new Cart();
        newCart.setUser(user); // Set User object
        newCart.setUserId(userId); // Ensure userId is saved

        return cartRepository.save(newCart);
    }







    public void clearCart(String userId) {
        ObjectId userObjectId = new ObjectId(userId);
        cartRepository.deleteByUserId(userObjectId);
    }


    public Optional<Cart> getCartByUserId(String userId) {
        try {
            ObjectId objectId = new ObjectId(userId); // Ensure conversion if stored as ObjectId
            return cartRepository.findByUserId(objectId);
        } catch (IllegalArgumentException e) {
            return Optional.empty(); // Handle invalid userId
        }
    }



    @Autowired
    private MongoTemplate mongoTemplate;

    public CartDTO updateItemQuantity(String userId, String productId, String action) {
        System.out.println("🔍 DEBUG: Searching cart for userId: " + userId);
        System.out.println("🔍 DEBUG: Received productId: " + productId);

        // Convert userId to ObjectId
        ObjectId userObjectId;
        try {
            userObjectId = new ObjectId(userId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("❌ Invalid userId format: " + userId);
        }

        // Convert productId to ObjectId
        if (!ObjectId.isValid(productId)) {
            throw new IllegalArgumentException("❌ Invalid productId format: " + productId);
        }
        ObjectId productObjectId = new ObjectId(productId);

        // 🔍 Query the cart using userId as ObjectId
        Query cartQuery = new Query(Criteria.where("userId").is(userObjectId));
        Cart existingCart = mongoTemplate.findOne(cartQuery, Cart.class, "cart");

        if (existingCart == null) {
            throw new ResourceNotFoundException("🛑 No cart found for userId: " + userId);
        }

        // 🔍 Check if product exists in the cart
        boolean productExists = existingCart.getItems().stream()
                .anyMatch(item -> item.getProduct().getId().equals(productObjectId));

        if (!productExists) {
            throw new ResourceNotFoundException("❌ Product ID " + productId + " not found in cart");
        }

        // 🔄 Update quantity based on action
        Query updateQuery = new Query(
                Criteria.where("userId").is(userObjectId).and("items.product.id").is(productObjectId)
        );

        Update update = new Update();
        if ("increase".equals(action)) {
            update.inc("items.$.quantity", 1);
        } else if ("decrease".equals(action)) {
            update.inc("items.$.quantity", -1);
        } else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }

        mongoTemplate.updateFirst(updateQuery, update, "cart");

        // 🔄 Fetch updated cart
        Cart updatedCart = mongoTemplate.findOne(cartQuery, Cart.class, "cart");
        if (updatedCart == null) {
            throw new ResourceNotFoundException("Cart not found after update");
        }

        return cartMapper.toDTO(updatedCart);
    }


    public boolean removeProductFromCart(String email, String productId) {
        // Find user by email
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            logger.error("User not found with email: {}", email);
            return false;
        }

        // Fetch cart using user ID
        Optional<Cart> cartOptional = cartRepository.findByUserId(user.getId());
        if (cartOptional.isEmpty()) {
            logger.error("Cart not found for user with ID: {}", user.getId());
            return false; // Cart not found
        }

        Cart cart = cartOptional.get();
        logger.info("Cart fetched for user {}: {}", user.getId(), cart.getItems().size());

        // Convert productId to ObjectId
        ObjectId productObjectId;
        try {
            productObjectId = new ObjectId(productId);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid product ID format: {}", productId);
            return false; // Invalid product ID format
        }

        // Remove product from cart
        boolean removed = cart.getItems().removeIf(cartItem -> {
            // Check if the product ObjectId inside the cart item matches the provided productId
            ObjectId cartProductId = cartItem.getProduct().getId(); // Ensure `getProduct()` returns the correct ObjectId
            logger.info("Comparing product ID {} with {}", cartProductId, productObjectId);
            return cartProductId != null && cartProductId.equals(productObjectId);
        });

        if (!removed) {
            logger.warn("Product with ID {} not found in cart.", productId);
            return false; // Product not found in cart
        }

        cartRepository.save(cart); // Save updated cart
        logger.info("Product removed from cart successfully.");
        return true;
    }



}

