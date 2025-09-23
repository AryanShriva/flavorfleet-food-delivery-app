package com.flavorfleet.restaurant_service.controller;

import com.flavorfleet.restaurant_service.dto.RestaurantDTO;
import com.flavorfleet.restaurant_service.dto.MenuItemDTO;
import com.flavorfleet.restaurant_service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restaurantService.createRestaurant(dto);
    }

    @PutMapping("/{id}")
    public RestaurantDTO updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        return restaurantService.updateRestaurant(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{restaurantId}/menu")
    public MenuItemDTO createMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItemDTO dto) {
        return restaurantService.createMenuItem(restaurantId, dto);
    }

    @GetMapping("/{restaurantId}/menu")
    public List<MenuItemDTO> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.getMenuItemsByRestaurant(restaurantId);
    }
}