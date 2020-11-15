package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Delivery;
import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class OrderController {
    private final GenericRepository<Order> orders;
    
    @Inject
    OrderController(GenericRepository<Order> orderRepository) {
        orders = orderRepository;

        log.info("OrderController > construct");

        if (orders.count() > 0) {
            return;
        }

        log.info("OrderController > construct > adding default orders");

        final Order defaultOrder1 = new Order();
        defaultOrder1.setUserName("John Eatsalot");
        defaultOrder1.setRestaurantId("Best taste from Italy!");
        // FoodItem defaultFood1 = new FoodItem();
        // defaultFood1.setFoodItem("Pepperoni Pizza");
        // defaultFood1.setFoodPrice(1099);
        // FoodItem defaultFood2 = new FoodItem();
        // defaultFood2.setFoodItem("Cheese Pizza");
        // defaultFood2.setFoodPrice(899);
        // ArrayList<ObjectId> defaultRestaurant1Menu = new ArrayList<>();
        // defaultRestaurant1Menu.add(defaultFood1.getId());
        // defaultRestaurant1Menu.add(defaultFood2.getId());
        // defaultRestaurant1.setRestaurantMenu(defaultRestaurant1Menu);
        // defaultRestaurant1.setIsActive(true);

        try {
            addOrder(defaultOrder1);
            addOrder(defaultOrder2);
        } catch (Exception e) {
            log.error("OrderController > construct > adding default orders > failure?");
            e.printStackTrace();
        }
}
