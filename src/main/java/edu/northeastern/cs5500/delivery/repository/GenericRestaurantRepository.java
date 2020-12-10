package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.Restaurant;
import java.util.Collection;

/*
 * Generic repository for restaurants to implement a search by text method for restaurant names
 */
public interface GenericRestaurantRepository extends GenericRepository<Restaurant> {

    /**
     * return possible restaurant name based on search string
     *
     * @param searchString
     * @return
     */
    public Collection<Restaurant> getRestaurantsByName(String searchString);
}
