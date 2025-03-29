package com.example.Boutique_Final.service;

import com.example.Boutique_Final.dto.OrderDTO;
import com.example.Boutique_Final.Mapper.OrderMapper;
import com.example.Boutique_Final.dto.OrderItemDTO;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import com.example.Boutique_Final.model.Order;
import com.example.Boutique_Final.model.OrderItem;
import com.example.Boutique_Final.model.Product;
import com.example.Boutique_Final.repositories.OrderRepository;
import com.example.Boutique_Final.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public OrderDTO createOrder(Order order) {
        System.out.println("Processing Order: " + order);

        if (order.getUser() == null || order.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID is required to create an order.");
        }
        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order items are required.");
        }

        // Create new Order entity
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.valueOf("PREPARING"));

        // Process order items and associate with the order
        List<OrderItem> processedItems = order.getOrderItems().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProduct().getId().toHexString())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + item.getProduct().getId()));
                    return new OrderItem(product, item.getQuantity(), item.getTotalPrice());
                })
                .collect(Collectors.toList());
        order.setOrderItems(processedItems);

        // Save the order and return the DTO
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderDTO(savedOrder); // Map to DTO for the response
    }



    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll(); // Fetch all orders

        return orders.stream()
                .map(order -> {
                    List<OrderItemDTO> orderItems = (order.getOrderItems() != null ? order.getOrderItems() : Collections.emptyList()).stream()
                            .map(item -> {
                                // Ensure 'item' is of type OrderItem
                                if (item instanceof OrderItem) {
                                    Product product = ((OrderItem) item).getProduct(); // Cast to OrderItem
                                    return new OrderItemDTO(
                                            product != null ? product.getId().toHexString() : "N/A",
                                            product != null ? product.getName() : "N/A",
                                            ((OrderItem) item).getQuantity(),
                                            ((OrderItem) item).getTotalPrice()
                                    );
                                }
                                return null; // Handle case where item is not an OrderItem
                            })
                            .filter(Objects::nonNull) // Filter out nulls
                            .collect(Collectors.toList());

                    return new OrderDTO(
                            order.getId(),
                            order.getUser () != null ? order.getUser ().getId().toHexString() : null,
                            order.getAddress(),
                            order.getPhoneNumber(),
                            order.getStatus(),
                            order.getCreatedAt(),
                            order.getTotalPrice(),
                            orderItems
                    );
                })
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByUserId(String userId) {
        System.out.println("Fetching Orders for userId : " + userId);
        return orderRepository.findByUser_Id(userId).stream()
                .map(orderMapper::toOrderDTO) // Map entity to DTO
                .collect(Collectors.toList());
    }
}
