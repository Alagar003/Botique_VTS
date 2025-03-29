////package com.example.Boutique_Final.dto;
////
////import com.example.Boutique_Final.model.Order;
////import jakarta.validation.constraints.NotBlank;
////import lombok.Data;
////
////import java.time.LocalDateTime;
////import java.util.List;
////
////@Data
////public class OrderDTO {
////    private Long id;
////    private Long userId;
////    @NotBlank(message = "Address is required")
////    private String address;
////    @NotBlank(message = "Phone name is required")
////    private String phoneNumber;
////    private Order.OrderStatus status;
////    private LocalDateTime createdAt;
////    private List<OrderItemDTO> orderItems;
////}
//
//
//
//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Order.OrderStatus;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class OrderDTO {
//    private String id;
//    private String userId;
//    private String address;
//    private String phoneNumber;
//    private OrderStatus status;
//    private LocalDateTime createdAt;
//    private List<OrderItemDTO> items;
//
//    public OrderDTO(String id, String userId, String address, String phoneNumber, OrderStatus status, LocalDateTime createdAt) {
//        this.id = id;
//        this.userId = userId;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.status = status;
//        this.createdAt = createdAt;
//    }
//
//}



//package com.example.Boutique_Final.dto;////package com.example.Boutique_Final.dto;
//////
//////import com.example.Boutique_Final.model.Order;
//////import jakarta.validation.constraints.NotBlank;
//////import lombok.Data;
//////
//////import java.time.LocalDateTime;
//////import java.util.List;
//////
//////@Data
//////public class OrderDTO {
//////    private Long id;
//////    private Long userId;
//////    @NotBlank(message = "Address is required")
//////    private String address;
//////    @NotBlank(message = "Phone name is required")
//////    private String phoneNumber;
//////    private Order.OrderStatus status;
//////    private LocalDateTime createdAt;
//////    private List<OrderItemDTO> orderItems;
//////}
////
////
////
////package com.example.Boutique_Final.dto;
////
////import com.example.Boutique_Final.model.Order.OrderStatus;
////import lombok.AllArgsConstructor;
////import lombok.Data;
////import lombok.NoArgsConstructor;
////
////import java.time.LocalDateTime;
////import java.util.List;
////
////@Data
////@NoArgsConstructor
////@AllArgsConstructor
////public class OrderDTO {
////    private String id;
////    private String userId;
////    private String address;
////    private String phoneNumber;
////    private OrderStatus status;
////    private LocalDateTime createdAt;
////    private List<OrderItemDTO> items;
////
////    public OrderDTO(String id, String userId, String address, String phoneNumber, OrderStatus status, LocalDateTime createdAt) {
////        this.id = id;
////        this.userId = userId;
////        this.address = address;
////        this.phoneNumber = phoneNumber;
////        this.status = status;
////        this.createdAt = createdAt;
////    }
////
////}
//
//
//
////import com.example.Boutique_Final.model.Order;
////
////import java.time.LocalDateTime;
////import java.util.List;
////
////public class OrderDTO {
////    private String id;
////    private String userId;
////    private String address;
////    private String phoneNumber;
////    private Order.OrderStatus status;
////    private LocalDateTime createdAt;
////    private double totalPrice;
////    private List<OrderItemDTO> orderItems; // Ensure this is included
////
////    public OrderDTO(String id, String userId, String address, String phoneNumber,
////                    Order.OrderStatus status, LocalDateTime createdAt,
////                    double totalPrice, List<OrderItemDTO> orderItems) {
////        this.id = id;
////        this.userId = userId;
////        this.address = address;
////        this.phoneNumber = phoneNumber;
////        this.status = status;
////        this.createdAt = createdAt;
////        this.totalPrice = totalPrice;
////        this.orderItems = orderItems;
////    }
////
////    // Getters and Setters...
////}
////
//
//
//import com.example.Boutique_Final.model.Order;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class OrderDTO {
//    private final String id;
//    private final String userId;
//    private final String address;
//    private final String phoneNumber;
//    private final Order.OrderStatus status;
//    private final LocalDateTime createdAt;
//    private final double totalPrice;
//    private final List<OrderItemDTO> orderItems;
//
//    public OrderDTO(String id, String userId, String address, String phoneNumber,
//                    Order.OrderStatus status, LocalDateTime createdAt,
//                    double totalPrice, List<OrderItemDTO> orderItems) {
//        this.id = id;
//        this.userId = userId;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.status = status;
//        this.createdAt = createdAt;
//        this.totalPrice = totalPrice;
//        this.orderItems = orderItems;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public Order.OrderStatus getStatus() {
//        return status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public List<OrderItemDTO> getOrderItems() {
//        return orderItems;
//    }
//}


package com.example.Boutique_Final.dto;

import com.example.Boutique_Final.model.Order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private String address;
    private String phoneNumber;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private double totalPrice;
    private List<OrderItemDTO> orderItems;

    // Constructor with all arguments
    public OrderDTO(String id, String userId, String address, String phoneNumber,
                    OrderStatus status, LocalDateTime createdAt, double totalPrice,
                    List<OrderItemDTO> orderItems) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    // No-argument constructor for frameworks
    public OrderDTO() {
    }
}

