package com.flavorfleet.order_service.repository;

import com.flavorfleet.order_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}