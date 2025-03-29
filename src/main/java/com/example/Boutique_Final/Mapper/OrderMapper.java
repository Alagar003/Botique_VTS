////package com.example.Boutique_Final.Mapper;
////import com.example.Boutique_Final.dto.OrderDTO;
////import com.example.Boutique_Final.dto.OrderItemDTO;
////import com.example.Boutique_Final.model.Order;
////import com.example.Boutique_Final.model.OrderItem;
////import org.mapstruct.Mapper;
////import org.mapstruct.Mapping;
////
////import java.util.List;
////
////@Mapper(componentModel = "spring")
////public interface OrderMapper {
////    @Mapping(target = "userId", source = "user.id")
////    @Mapping(target = "orderItems", source = "items")
////    OrderDTO toDTO(Order order);
////
////    @Mapping(target = "user.id", source = "userId")
////    @Mapping(target = "items", source = "orderItems")
////    Order toEntity(OrderDTO orderDTO);
////
////    List<OrderDTO> toDTOs(List<Order> orders);
////    List<Order> toEntities(List<OrderDTO> orderDTOS);
////    @Mapping(target = "productId", source = "product.id")
////    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
////    @Mapping(target = "product.id", source = "productId")
////    OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO);
////
////    List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> orderItem);
////    List<OrderItem> toOrderItemEntities(List<OrderItemDTO> orderItemDTO);
////
////
////}
//
//package com.example.Boutique_Final.Mapper;
//
//import com.example.Boutique_Final.dto.OrderDTO;
//import com.example.Boutique_Final.dto.OrderItemDTO;
//import com.example.Boutique_Final.model.Order;
//import com.example.Boutique_Final.model.OrderItem;
//import org.springframework.stereotype.Component;
//import org.bson.types.ObjectId; // Import ObjectId
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class OrderMapper {
//
//    public OrderDTO toDTO(Order order) {
//        return new OrderDTO(
//                order.getId().toString(),  // Convert ObjectId to String
//                order.getUser().getId().toString(),  // Convert ObjectId to String
//                order.getAddress(),
//                order.getPhoneNumber(),
//                order.getStatus(),
//                order.getCreatedAt(),
//                toOrderItemDTOList(order.getItems())
//        );
//    }
//
//    public List<OrderDTO> toDTOs(List<Order> orders) {
//        return orders.stream().map(this::toDTO).collect(Collectors.toList());
//    }
//
//    private List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> items) {
//        return items.stream().map(this::toOrderItemDTO).collect(Collectors.toList());
//    }
//
//    private OrderItemDTO toOrderItemDTO(OrderItem item) {
//        return new OrderItemDTO(
//                item.getProduct().getId().toString(), // Convert ObjectId to String
//                item.getProduct().getName(),
//                item.getQuantity(),
//                item.getPrice()
//        );
//    }
//}


package com.example.Boutique_Final.Mapper;//package com.example.Boutique_Final.Mapper;//package com.example.Boutique_Final.Mapper;
////import com.example.Boutique_Final.dto.OrderDTO;
////import com.example.Boutique_Final.dto.OrderItemDTO;
////import com.example.Boutique_Final.model.Order;
////import com.example.Boutique_Final.model.OrderItem;
////import org.mapstruct.Mapper;
////import org.mapstruct.Mapping;
////
////import java.util.List;
////
////@Mapper(componentModel = "spring")
////public interface OrderMapper {
////    @Mapping(target = "userId", source = "user.id")
////    @Mapping(target = "orderItems", source = "items")
////    OrderDTO toDTO(Order order);
////
////    @Mapping(target = "user.id", source = "userId")
////    @Mapping(target = "items", source = "orderItems")
////    Order toEntity(OrderDTO orderDTO);
////
////    List<OrderDTO> toDTOs(List<Order> orders);
////    List<Order> toEntities(List<OrderDTO> orderDTOS);
////    @Mapping(target = "productId", source = "product.id")
////    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
////    @Mapping(target = "product.id", source = "productId")
////    OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO);
////
////    List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> orderItem);
////    List<OrderItem> toOrderItemEntities(List<OrderItemDTO> orderItemDTO);
////
////
////}
////package com.example.Boutique_Final.Mapper;
////
////import com.example.Boutique_Final.dto.OrderDTO;
////import com.example.Boutique_Final.dto.OrderItemDTO;
////import com.example.Boutique_Final.model.Order;
////import com.example.Boutique_Final.model.OrderItem;
////import org.springframework.stereotype.Component;
////import java.math.BigDecimal;
////import java.util.List;
////import java.util.stream.Collectors;
////
////@Component
////public class OrderMapper {
////
////    public OrderDTO toDTO(Order order) {
////        List<OrderItemDTO> orderItems = toOrderItemDTOList(order.getOrderItems());
////
////        return new OrderDTO(
////                order.getId().toString(),
////                order.getUser().getId().toString(),
////                order.getAddress(),
////                order.getPhoneNumber(),
////                order.getStatus(),
////                order.getCreatedAt(),
////                calculateTotalPrice(order.getOrderItems()).doubleValue(), // ✅ Convert BigDecimal to double
////                orderItems
////        );
////    }
////
////    public List<OrderDTO> toDTOs(List<Order> orders) {
////        return orders != null ? orders.stream()
////                .map(this::toDTO)
////                .collect(Collectors.toList()) : List.of();
////    }
////
////    private List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> items) {
////        return (items != null) ? items.stream()
////                .map(this::toOrderItemDTO)
////                .collect(Collectors.toList()) : List.of();
////    }
////
////    private OrderItemDTO toOrderItemDTO(OrderItem item) {
////        if (item == null || item.getProduct() == null) {
////            throw new IllegalArgumentException("OrderItem or its Product cannot be null");
////        }
////
////        return new OrderItemDTO(
////                item.getProduct().getId().toString(),
////                item.getProduct().getName(),
////                item.getQuantity(),
////                item.getTotalPrice()
////        );
////    }
////
////    private BigDecimal calculateTotalPrice(List<OrderItem> items) {
////        return (items != null) ? items.stream()
////                .map(item -> item.getTotalPrice().multiply(BigDecimal.valueOf(item.getQuantity()))) // ✅ Correct multiplication
////                .reduce(BigDecimal.ZERO, BigDecimal::add) // ✅ Sum all values
////                : BigDecimal.ZERO;
////    }
////}
////
////import com.example.Boutique_Final.dto.OrderDTO;
////import com.example.Boutique_Final.dto.OrderItemDTO;
////import com.example.Boutique_Final.model.Order;
////import com.example.Boutique_Final.model.OrderItem;
////
////import java.util.stream.Collectors;
////
////public class OrderMapper {
////    public static OrderDTO toOrderDTO(Order order) {
////        return new OrderDTO(
////                order.getId(),
////                order.getUser() != null ? order.getUser().getId().toHexString() : null,
////                order.getAddress(),
////                order.getPhoneNumber(),
////                order.getStatus(),
////                order.getCreatedAt(),
////                order.getTotalPrice(),
////                order.getOrderItems().stream()
////                        .map(OrderMapper::toOrderItemDTO)
////                        .collect(Collectors.toList())
////        );
////    }
////
////    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
////        return new OrderItemDTO(
////                orderItem.getProduct() != null ? orderItem.getProduct().getId().toHexString() : null,
////                orderItem.getProduct() != null ? orderItem.getProduct().getName() : null,
////                orderItem.getQuantity(),
////                orderItem.getTotalPrice()
////        );
////    }
////}
////
//
//import com.example.Boutique_Final.dto.OrderDTO;
//import com.example.Boutique_Final.dto.OrderItemDTO;
//import com.example.Boutique_Final.model.Order;
//import com.example.Boutique_Final.model.OrderItem;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class OrderMapper {
//    private static final Logger log = LoggerFactory.getLogger(OrderMapper.class);
//
//    public static OrderDTO toOrderDTO(Order order) {
//        log.debug("Mapping Order to OrderDTO: id={}", order.getId());
//        return new OrderDTO(
//                order.getId(),
//                order.getUser() != null ? order.getUser().getId().toHexString() : null,
//                order.getAddress(),
//                order.getPhoneNumber(),
//                order.getStatus(),
//                order.getCreatedAt(),
//                order.getTotalPrice(),
//                order.getOrderItems() != null ?
//                        order.getOrderItems().stream()
//                                .map(OrderMapper::toOrderItemDTO)
//                                .collect(Collectors.toList()) :
//                        List.of() // Return an empty list if order items are null
//        );
//    }
//
//    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
//        log.debug("Mapping OrderItem to OrderItemDTO: id={}", orderItem.getId());
//
//        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
//            throw new IllegalArgumentException("Invalid quantity for OrderItem: " + orderItem.getId());
//        }
//
//        if (orderItem.getTotalPrice() == null || orderItem.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Invalid price for OrderItem: " + orderItem.getId());
//        }
//
//        return new OrderItemDTO(
//                orderItem.getProduct() != null ? orderItem.getProduct().getId().toHexString() : null,
//                orderItem.getProduct() != null ? orderItem.getProduct().getName() : null,
//                orderItem.getQuantity(),
//                orderItem.getTotalPrice()
//        );
//    }
//}




import com.example.Boutique_Final.dto.OrderDTO;
import com.example.Boutique_Final.dto.OrderItemDTO;
import com.example.Boutique_Final.model.Order;
import com.example.Boutique_Final.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUser() != null ? order.getUser().getId().toHexString() : null,
                order.getAddress(),
                order.getPhoneNumber(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getTotalPrice(),
                order.getOrderItems() != null ?
                        order.getOrderItems().stream()
                                .map(this::toOrderItemDTO)
                                .collect(Collectors.toList()) :
                        List.of()
        );
    }

    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getProduct() != null ? orderItem.getProduct().getId().toHexString() : null,
                orderItem.getProduct() != null ? orderItem.getProduct().getName() : null,
                orderItem.getQuantity(),
                orderItem.getTotalPrice()
        );
    }
}
