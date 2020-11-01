package edu.northeastern.cs5500.delivery.controller;

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

        final Restaurant defaultRestaurant1 = new Restaurant();
        defaultRestaurant1.setRestaurantName("Best Pizza");
        defaultRestaurant1.setRestaurantDescription("Best taste from Italy!");
        HashMap<String, Double> defaultRestaurant1Menu = new HashMap<>();
        defaultRestaurant1Menu.put("Pepperoni Pizza", 10.99);
        defaultRestaurant1Menu.put("Cheese Pizza", 8.99);
        defaultRestaurant1.setRestaurantMenu(defaultRestaurant1Menu);
        defaultRestaurant1.setIsActive(true);

        final Restaurant defaultRestaurant2 = new Restaurant();
        defaultRestaurant2.setRestaurantName("Salads and Smoothies");
        defaultRestaurant2.setRestaurantDescription("Live and eat healthy!");
        HashMap<String, Double> defaultRestaurant2Menu = new HashMap<>();
        defaultRestaurant2Menu.put("Fresh Tossed Salad - 1 protein, large", 11.99);
        defaultRestaurant2Menu.put("Banana, Mango Kale Fusion Smoothie", 8.99);
        defaultRestaurant1.setRestaurantMenu(defaultRestaurant2Menu);
        defaultRestaurant1.setIsActive(true);

        try {
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

    @Nonnull
    public Collection<Restaurant> getRestaurants() {
        log.debug("RestaurantController > getRestaurants()");
        return restaurants.getAll();
    }

    @Nonnull
    public Restaurant addRestaurant(@Nonnull Restaurant restaurant) throws Exception {
        log.debug("RestaurantController > addRestaurant(...)");
        if (!restaurant.isValid()) {
            // TODO: replace with a real invalid object exception
            // probably not one exception per object type though...
            throw new ExceptionClass("InvalidRestaurantException");
        }

        ObjectId id = restaurant.getId();

        if (id != null && restaurants.get(id) != null) {
            // TODO: replace with a real duplicate key exception
            throw new ExceptionClass("DuplicateKeyException");
        }

        return restaurants.add(restaurant);
    }

    public void updateRestaurant(@Nonnull Restaurant restaurant) throws Exception {
        log.debug("RestaurantController > updateRestaurant(...)");
        restaurants.update(restaurant);
    }

    public void deleteRestaurant(@Nonnull ObjectId id) throws Exception {
        log.debug("RestaurantController > deleteRestaurant(...)");
        restaurants.delete(id);
    }
}
