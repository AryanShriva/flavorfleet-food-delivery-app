package com.flavorfleet.restaurant_service.service;

import com.flavorfleet.restaurant_service.dto.RestaurantDTO;
import com.flavorfleet.restaurant_service.dto.MenuItemDTO;
import com.flavorfleet.restaurant_service.model.Restaurant;
import com.flavorfleet.restaurant_service.model.MenuItem;
import com.flavorfleet.restaurant_service.repository.RestaurantRepository;
import com.flavorfleet.restaurant_service.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Cacheable(value = "restaurants", key = "'all'")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Cacheable(value = "restaurants", key = "#id")
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return toDTO(restaurant);
    }

    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        Restaurant saved = restaurantRepository.save(restaurant);
        return toDTO(saved);
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        Restaurant updated = restaurantRepository.save(restaurant);
        return toDTO(updated);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    public MenuItemDTO createMenuItem(Long restaurantId, MenuItemDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setRestaurant(restaurant);
        MenuItem saved = menuItemRepository.save(menuItem);
        return toMenuItemDTO(saved);
    }

    @Cacheable(value = "menuItems", key = "#restaurantId")
    public List<MenuItemDTO> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findAll().stream()
                .filter(item -> item.getRestaurant().getId().equals(restaurantId))
                .map(this::toMenuItemDTO)
                .collect(Collectors.toList());
    }

    private RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        return dto;
    }

    private MenuItemDTO toMenuItemDTO(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setRestaurantId(menuItem.getRestaurant().getId());
        return dto;
    }
}