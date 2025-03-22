//package com.example.Boutique_Final.Mapper;
//import com.example.Boutique_Final.dto.OrderDTO;
//import com.example.Boutique_Final.dto.OrderItemDTO;
//import com.example.Boutique_Final.model.Order;
//import com.example.Boutique_Final.model.OrderItem;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface OrderMapper {
//    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "orderItems", source = "items")
//    OrderDTO toDTO(Order order);
//
//    @Mapping(target = "user.id", source = "userId")
//    @Mapping(target = "items", source = "orderItems")
//    Order toEntity(OrderDTO orderDTO);
//
//    List<OrderDTO> toDTOs(List<Order> orders);
//    List<Order> toEntities(List<OrderDTO> orderDTOS);
//    @Mapping(target = "productId", source = "product.id")
//    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
//    @Mapping(target = "product.id", source = "productId")
//    OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO);
//
//    List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> orderItem);
//    List<OrderItem> toOrderItemEntities(List<OrderItemDTO> orderItemDTO);
//
//
//}

package com.example.Boutique_Final.Mapper;

import com.example.Boutique_Final.dto.OrderDTO;
import com.example.Boutique_Final.dto.OrderItemDTO;
import com.example.Boutique_Final.model.Order;
import com.example.Boutique_Final.model.OrderItem;
import org.springframework.stereotype.Component;
import org.bson.types.ObjectId; // Import ObjectId

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId().toString(),  // Convert ObjectId to String
                order.getUser().getId().toString(),  // Convert ObjectId to String
                order.getAddress(),
                order.getPhoneNumber(),
                order.getStatus(),
                order.getCreatedAt(),
                toOrderItemDTOList(order.getItems())
        );
    }

    public List<OrderDTO> toDTOs(List<Order> orders) {
        return orders.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> items) {
        return items.stream().map(this::toOrderItemDTO).collect(Collectors.toList());
    }

    private OrderItemDTO toOrderItemDTO(OrderItem item) {
        return new OrderItemDTO(
                item.getProduct().getId().toString(), // Convert ObjectId to String
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
