//package com.example.Boutique_Final.dto;
//
//import com.example.Boutique_Final.model.Order;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//public class OrderDTO {
//    private Long id;
//    private Long userId;
//    @NotBlank(message = "Address is required")
//    private String address;
//    @NotBlank(message = "Phone name is required")
//    private String phoneNumber;
//    private Order.OrderStatus status;
//    private LocalDateTime createdAt;
//    private List<OrderItemDTO> orderItems;
//}



package com.example.Boutique_Final.dto;

import com.example.Boutique_Final.model.Order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String userId;
    private String address;
    private String phoneNumber;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;

    public OrderDTO(String id, String userId, String address, String phoneNumber, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
    }

}
