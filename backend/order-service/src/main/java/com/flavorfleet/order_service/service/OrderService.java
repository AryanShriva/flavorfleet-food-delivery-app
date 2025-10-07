package com.flavorfleet.order_service.service;

import com.flavorfleet.order_service.client.RestaurantClient;
import com.flavorfleet.order_service.client.MenuItemDTO;
import com.flavorfleet.order_service.dto.OrderDTO;
import com.flavorfleet.order_service.dto.OrderItemDTO;
import com.flavorfleet.order_service.model.Order;
import com.flavorfleet.order_service.model.OrderItem;
import com.flavorfleet.order_service.repository.OrderRepository;
import com.flavorfleet.order_service.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantClient restaurantClient;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, RestaurantClient restaurantClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.restaurantClient = restaurantClient;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Fetch menu items from restaurant-service
        List<MenuItemDTO> menuItems = restaurantClient.getMenuItems(orderDTO.getRestaurantId());
        // Validate menu items and set unit prices
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            MenuItemDTO menuItem = menuItems.stream()
                    .filter(m -> m.getId().equals(itemDTO.getMenuItemId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Invalid menu item ID: " + itemDTO.getMenuItemId()));
            itemDTO.setUnitPrice(menuItem.getPrice());
        }

        // Create Order entity
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setTotalPrice(orderDTO.getOrderItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        // Create OrderItem entities
        Order finalOrder = order;
        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(itemDTO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(finalOrder);
            orderItem.setMenuItemId(itemDTO.getMenuItemId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(itemDTO.getUnitPrice());
            return orderItem;
        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);

        // Save to database
        order = orderRepository.save(order);

        // Convert back to DTO
        OrderDTO result = new OrderDTO();
        result.setId(order.getId());
        result.setUserId(order.getUserId());
        result.setRestaurantId(order.getRestaurantId());
        result.setTotalPrice(order.getTotalPrice());
        result.setStatus(order.getStatus());
        result.setCreatedAt(order.getCreatedAt());
        result.setOrderItems(orderItems.stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setMenuItemId(item.getMenuItemId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            return itemDTO;
        }).collect(Collectors.toList()));

        return result;
    }
}