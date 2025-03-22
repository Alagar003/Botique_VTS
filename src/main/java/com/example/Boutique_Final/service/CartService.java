

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

