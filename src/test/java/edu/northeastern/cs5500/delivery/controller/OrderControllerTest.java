package edu.northeastern.cs5500.delivery.controller;

import java.util.HashMap;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;

import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.Restaurant;

public class OrderControllerTest {
    Order testOrder1 = new Order();
    Order testOrder2 = new Order();
    Order testOrder3 = new Order();
    Restaurant testRestaurant1 = new Restaurant();
    Restaurant testRestaurant2 = new Restaurant();
    Restaurant testRestaurant3 = new Restaurant();

    @BeforeEach
    public void init() {
        testRestaurant1.setRestaurantName("Best Calzones!");
        testRestaurant1.setRestaurantDescription("Best taste from Italy!");
        HashMap<ObjectId, FoodItem> testRestaurant1Menu = new HashMap<>();
        FoodItem testFood1 = new FoodItem();
        testFood1.setId(new ObjectId());
        testFood1.setFoodItem("Pepperoni Calzone");
        testFood1.setFoodPrice(1199);
        FoodItem testFood2 = new FoodItem();
        testFood2.setId(new ObjectId());
        testFood2.setFoodItem("Cheese Spinach Calzone");
        testFood2.setFoodPrice(1099);
        testRestaurant1Menu.put(testFood1.getId(), testFood1);
        testRestaurant1Menu.put(testFood2.getId(), testFood2);
        testRestaurant1.setRestaurantMenu(testRestaurant1Menu);
        testRestaurant1.setIsActive(true);

        testRestaurant2.setRestaurantName("Taj Mahal Indian");
        testRestaurant2.setRestaurantDescription("Best Indian food!");
        HashMap<ObjectId, FoodItem> testRestaurant2Menu = new HashMap<>();
        FoodItem testFood3 = new FoodItem();
        testFood3.setId(new ObjectId());
        testFood3.setFoodItem("Vegetarian meal");
        testFood3.setFoodPrice(1199);
        FoodItem testFood4 = new FoodItem();
        testFood4.setId(new ObjectId());
        testFood4.setFoodItem("Chicken meal");
        testFood4.setFoodPrice(1299);
        testRestaurant2Menu.put(testFood3.getId(), testFood3);
        testRestaurant2Menu.put(testFood4.getId(), testFood4);
        testRestaurant2.setRestaurantMenu(testRestaurant2Menu);
        testRestaurant2.setIsActive(true);

        testOrder1.setId(new ObjectId());
        testOrder1.
    }
}
