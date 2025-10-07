package com.flavorfleet.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restaurant-service", url = "${restaurant-service.url:http://localhost:8082}")
public interface RestaurantClient {
    @GetMapping("/restaurants/{id}/menu")
    List<MenuItemDTO> getMenuItems(@PathVariable("id") Long restaurantId);
}