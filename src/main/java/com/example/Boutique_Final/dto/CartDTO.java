//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail;
//    private List<CartItemDTO> cartItems;
//    private Double totalPrice;
//
//    // Constructor to initialize all fields
//    public CartDTO(String id, String userId, String userEmail, List<CartItemDTO> cartItems, Double totalPrice) {
//        this.id = id;
//        this.userId = userId;
//        this.userEmail = userEmail;
//        this.cartItems = cartItems;
//        this.totalPrice = totalPrice;
//    }
//
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser() == null) {
//                throw new IllegalArgumentException("User in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            this.userEmail = cart.getUser().getEmail();
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Cast before calling
//                product.getName(),
//                product.getPrice(),
//                cartItem.getQuantity(),
//                product.getImage()
//        );
//
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum()
//                : 0.0;
//    }
//}

//
//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail;
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to initialize all fields
//    public CartDTO(String id, String userId, String userEmail, List<CartItemDTO> cartItems, Double totalPrice) {
//        this.id = id;
//        this.userId = userId;
//        this.userEmail = userEmail;
//        this.cartItems = cartItems;
//        this.totalPrice = totalPrice;
//    }
//
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser () == null) {
//                throw new IllegalArgumentException("User  in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
//
//            this.userEmail = cart.getUser ().getEmail();
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Cast before calling
//                product.getName(),
//                product.getPrice(),
//                cartItem.getQuantity(),
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum()
//                : 0.0;
//    }
//}



//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail;
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to initialize all fields
//    public CartDTO(String id, String userId, String userEmail, List<CartItemDTO> cartItems, Double totalPrice) {
//        this.id = id;
//        this.userId = userId;
//        this.userEmail = userEmail;
//        this.cartItems = cartItems;
//        this.totalPrice = totalPrice;
//    }
//
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser () == null) {
//                throw new IllegalArgumentException("User  in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
//
//            this.userEmail = cart.getUser ().getEmail();
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Cast before calling
//                product.getName(),
//                product.getPrice(),
//                cartItem.getQuantity(),
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum()
//                : 0.0;
//    }
//}



//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail;
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to initialize all fields
////    public CartDTO(String id, String userId, String userEmail, List<CartItemDTO> cartItems, Double totalPrice) {
////        this.id = id;
////        this.userId = userId;
////        this.userEmail = userEmail;
////        this.cartItems = cartItems;
////        this.totalPrice = totalPrice;
////    }
////
////    // Constructor to map Cart to CartDTO
////    public CartDTO(Cart cart) {
////        if (cart == null) {
////            throw new IllegalArgumentException("Cart cannot be null");
////        }
////
////        try {
////            // Convert ObjectId to String for cart id
////            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
////
////            if (cart.getUser () == null) {
////                throw new IllegalArgumentException("User  in Cart cannot be null");
////            }
////
////            // Convert ObjectId to String for user id
////            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
////
////            this.userEmail = cart.getUser ().getEmail();
////
////            // Map CartItems to CartItemDTOs
////            this.cartItems = cart.getItems() != null
////                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
////                    : List.of();
////
////            // Calculate the total price
////            this.totalPrice = calculateTotalPrice(this.cartItems);
////
////        } catch (Exception e) {
////            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
////            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
////        }
////    }
//
//
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser () == null) {
//                throw new IllegalArgumentException("User  in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
//
//            // Set userEmail from the User object
//            this.userEmail = cart.getUser ().getEmail(); // Ensure this is not null
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Cast before calling
//                product.getName(),
//                product.getPrice(),
//                cartItem.getQuantity(),
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() * item.getQuantity() : 0.0)
//                .sum()
//                : 0.0;
//    }
//}


//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail;
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser () == null) {
//                throw new IllegalArgumentException("User  in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
//
//            // Set userEmail from the User object
//            this.userEmail = cart.getUser ().getEmail(); // Ensure this is not null
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Convert ObjectId to String
//                product.getName(), // Get product name
//                product.getPrice(), // Get product price
//                cartItem.getQuantity(), // Get quantity from CartItem
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() * item.getQuantity() : 0.0)
//                .sum()
//                : 0.0;
//    }
//}

//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail; // User's email
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            if (cart.getUser () == null) {
//                throw new IllegalArgumentException("User  in Cart cannot be null");
//            }
//
//            // Convert ObjectId to String for user id
//            this.userId = cart.getUserId() != null ? cart.getUserId().toHexString() : null;
//
//            // Set userEmail from the User object
//            this.userEmail = cart.getUser ().getEmail(); // Ensure this is not null
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate the total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId() instanceof ObjectId ? ((ObjectId) product.getId()).toHexString() : null, // Convert ObjectId to String
//                product.getName(), // Get product name
//                product.getPrice(), // Get product price
//                cartItem.getQuantity(), // Get quantity from CartItem
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() * item.getQuantity() : 0.0)
//                .sum()
//                : 0.0;
//    }
//}


//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Cart;
//import com.example.Boutique_Final.model.CartItem;
//import com.example.Boutique_Final.model.Product;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor // Default no-arg constructor for MapStruct
//public class CartDTO {
//    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);
//
//    private String id; // Converted from ObjectId
//    private String userId; // Converted from ObjectId
//    private String userEmail; // User's email (must be fetched separately)
//    private List<CartItemDTO> cartItems; // List of cart items
//    private Double totalPrice; // Total price of the cart
//
//    // Constructor to map Cart to CartDTO
//    // Constructor to map Cart to CartDTO
//    public CartDTO(Cart cart) {
//        if (cart == null) {
//            throw new IllegalArgumentException("Cart cannot be null");
//        }
//
//        try {
//            // Convert ObjectId to String for cart id
//            this.id = cart.getId() != null ? cart.getId().toHexString() : null;
//
//            // Get userId from User reference instead of cart.getUserId()
//            this.userId = (cart.getUser() != null && cart.getUser().getId() != null)
//                    ? cart.getUser().getId().toHexString()
//                    : null;
//
//            // Get userEmail from User object
//            this.userEmail = (cart.getUser() != null) ? cart.getUser().getEmail() : null;
//
//            // Map CartItems to CartItemDTOs
//            this.cartItems = cart.getItems() != null
//                    ? cart.getItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList())
//                    : List.of();
//
//            // Calculate total price
//            this.totalPrice = calculateTotalPrice(this.cartItems);
//
//        } catch (Exception e) {
//            logger.error("Error creating CartDTO: {}", e.getMessage(), e);
//            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
//        }
//    }
//
//    // Utility method to map CartItem to CartItemDTO
//    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
//        if (cartItem == null) {
//            throw new IllegalArgumentException("CartItem cannot be null");
//        }
//
//        Product product = cartItem.getProduct();
//        if (product == null) {
//            throw new IllegalArgumentException("Product in CartItem cannot be null");
//        }
//
//        return new CartItemDTO(
//                product.getId().toHexString(), // Convert ObjectId to String
//                product.getName(), // Get product name
//                product.getPrice(), // Get product price
//                cartItem.getQuantity(), // Get quantity from CartItem
//                product.getImage() // Assuming Product has an image field
//        );
//    }
//
//    // Utility method to calculate total price
//    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        return cartItems != null && !cartItems.isEmpty()
//                ? cartItems.stream()
//                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() * item.getQuantity() : 0.0)
//                .sum()
//                : 0.0;
//    }
//}


package com.example.Boutique_Final.dto;

import com.example.Boutique_Final.model.Cart;
import com.example.Boutique_Final.model.CartItem;
import com.example.Boutique_Final.model.Product;
import com.example.Boutique_Final.repositories.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.Boutique_Final.model.User;

@Data
@NoArgsConstructor // Default no-arg constructor for MapStruct
public class CartDTO {
    private static final Logger logger = LoggerFactory.getLogger(CartDTO.class);

    private String id; // Converted from ObjectId
    private String userId; // Converted from ObjectId
    private String userEmail; // User's email (must be fetched separately)
    private List<CartItemDTO> cartItems; // List of cart items
    private Double totalPrice; // Total price of the cart


    public CartDTO(String id, String userId, String userEmail, List<CartItemDTO> cartItems, Double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }


    // Constructor to map Cart to CartDTO
    // Constructor to map Cart to CartDTO
    public CartDTO(Cart cart, UserRepository userRepository) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        try {
            // Convert ObjectId to String for cart id
            this.id = Optional.ofNullable(cart.getId()).map(ObjectId::toHexString).orElse(null);
            this.userId = Optional.ofNullable(cart.getUserId())
                    .map(ObjectId::toHexString)
                    .orElse(null);

            // ✅ Fetch User email using UserRepository
            // ✅ Correct way to fetch user email
            this.userEmail = userRepository.findById(cart.getUserId().toHexString())
                    .map(User::getEmail)
                    .orElseGet(() -> {
                        logger.warn("User email not found for userId: {}", cart.getUserId());
                        return null;
                    });



            // Map CartItems to CartItemDTOs
            this.cartItems = Optional.ofNullable(cart.getItems())
                    .map(items -> items.stream().map(this::mapToCartItemDTO).collect(Collectors.toList()))
                    .orElse(List.of());

            // Calculate total price
            this.totalPrice = calculateTotalPrice(this.cartItems);

        } catch (Exception e) {
            logger.error("Error creating CartDTO for cart ID {}: {}", cart.getId(), e.getMessage(), e);
            throw new RuntimeException("Error creating CartDTO: " + e.getMessage());
        }
    }

    // Utility method to map CartItem to CartItemDTO
    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        if (cartItem == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }

        Product product = Optional.ofNullable(cartItem.getProduct())
                .orElseThrow(() -> new IllegalArgumentException("Product in CartItem cannot be null"));

        // ✅ Fix: Use `cartItem.getPrice()` if available, otherwise fallback to `product.getPrice()`
        double itemPrice = cartItem.getPrice() != null ? cartItem.getPrice() : product.getPrice();

        return new CartItemDTO(
                product.getId().toHexString(),
                product.getName(),
                itemPrice,  // ✅ Correct price mapping
                cartItem.getQuantity(),
                product.getImage()
        );
}

    // Utility method to calculate total price
    private Double calculateTotalPrice(List<CartItemDTO> cartItems) {
        return cartItems != null && !cartItems.isEmpty()
                ? cartItems.stream()
                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() * item.getQuantity() : 0.0)
                .sum()
                : 0.0;
    }
}