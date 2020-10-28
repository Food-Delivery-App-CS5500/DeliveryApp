package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.util.Collection;
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
            throw new Exception("InvalidRestaurantException");
        }

        ObjectId id = restaurant.getId();

        if (id != null && restaurants.get(id) != null) {
            // TODO: replace with a real duplicate key exception
            throw new Exception("DuplicateKeyException");
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
