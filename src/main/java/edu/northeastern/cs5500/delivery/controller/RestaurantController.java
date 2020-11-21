package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class RestaurantController {
    private final GenericRepository<Restaurant> restaurants;

    @Inject
    RestaurantController(GenericRepository<Restaurant> restaurantRepository) {
        restaurants = restaurantRepository;

        log.info("RestaurantController > construct");

        if (restaurants.count() > 0) {
            return;
        }

        log.info("RestaurantController > construct > adding default restaurants");

        // Create 2 default restaurants to reside in the Restaurant Controller
        final Restaurant defaultRestaurant1 = new Restaurant();
        defaultRestaurant1.setRestaurantName("Best Pizza");
        defaultRestaurant1.setRestaurantDescription("Best taste from Italy!");
        FoodItem defaultFood1 = new FoodItem();
        defaultFood1.setId(new ObjectId());
        defaultFood1.setFoodItem("Pepperoni Pizza");
        defaultFood1.setFoodPrice(1099);
        FoodItem defaultFood2 = new FoodItem();
        defaultFood2.setId(new ObjectId());
        defaultFood2.setFoodItem("Cheese Pizza");
        defaultFood2.setFoodPrice(899);
        HashMap<ObjectId, FoodItem> defaultRestaurant1Menu = new HashMap<>();

        defaultRestaurant1.setRestaurantMenu(defaultRestaurant1Menu);
        defaultRestaurant1.setIsActive(true);

        final Restaurant defaultRestaurant2 = new Restaurant();
        defaultRestaurant2.setRestaurantName("Salads and Smoothies");
        defaultRestaurant2.setRestaurantDescription("Live and eat healthy!");
        FoodItem defaultFood3 = new FoodItem();
        defaultFood3.setId(new ObjectId());
        defaultFood3.setFoodItem("Fresh Tossed Salad - 1 protein, large");
        defaultFood3.setFoodPrice(1199);
        FoodItem defaultFood4 = new FoodItem();
        defaultFood4.setId(new ObjectId());
        defaultFood4.setFoodItem("Banana, Mango Kale Fusion Smoothie");
        defaultFood4.setFoodPrice(899);
        HashMap<ObjectId, FoodItem> defaultRestaurant2Menu = new HashMap<>();

        defaultRestaurant2.setRestaurantMenu(defaultRestaurant2Menu);
        defaultRestaurant2.setIsActive(true);

        try {
            defaultRestaurant1Menu.put(defaultFood1.getId(), defaultFood1);
            defaultRestaurant1Menu.put(defaultFood2.getId(), defaultFood2);
            defaultRestaurant2Menu.put(defaultFood3.getId(), defaultFood3);
            defaultRestaurant2Menu.put(defaultFood4.getId(), defaultFood4);
            addRestaurant(defaultRestaurant1);
            addRestaurant(defaultRestaurant2);
        } catch (Exception e) {
            log.error("RestaurantController > construct > adding default restaurants > failure?");
            e.printStackTrace();
        }
    }

    @Nullable
    public Restaurant getRestaurant(@Nonnull ObjectId uuid) {
        log.debug("RestaurantController > getRestaurants({})", uuid);
        return restaurants.get(uuid);
    }

    public Restaurant getRestaurant(String restaurantName) {
        
        return;
    }

    @Nonnull
    public Collection<Restaurant> getRestaurants() {
        log.debug("RestaurantController > getRestaurants()");
        return restaurants.getAll();
    }



    @Nonnull
    public Restaurant addRestaurant(@Nonnull Restaurant restaurant) throws ExceptionClass {
        log.debug("RestaurantController > addRestaurant(...)");
        if (!restaurant.isValid()) {
            // Replace with a real invalid object exception
            // probably not one exception per object type though
            throw new ExceptionClass("InvalidRestaurantException");
        }

        ObjectId id = restaurant.getId();

        if (id != null && restaurants.get(id) != null) {
            // Replace with a real duplicate key exception
            throw new ExceptionClass("DuplicateKeyException");
        }

        return restaurants.add(restaurant);
    }

    public void updateRestaurant(@Nonnull Restaurant restaurant) throws ExceptionClass {
        log.debug("RestaurantController > updateRestaurant(...)");
        restaurants.update(restaurant);
    }

    public void deleteRestaurant(@Nonnull ObjectId id) throws ExceptionClass {
        log.debug("RestaurantController > deleteRestaurant(...)");
        restaurants.delete(id);
    }
}
