package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.bson.Document;

public class MongoDBRestaurantRepository extends MongoDBRepository<Restaurant>
        implements GenericRestaurantRepository {

    @Inject
    public <T> MongoDBRestaurantRepository(MongoDBService mongoDBService) {
        super(Restaurant.class, mongoDBService);
        collection.createIndex(new Document("restaurantName", "text"));
    }

    @Override
    public Collection<Restaurant> getRestaurantsByName(String searchString) {
        // Create regex string from search string
        Document regexQuery = new Document();
        regexQuery.append("$regex", ".*" + Pattern.quote(searchString) + ".*");
        regexQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("restaurantName", regexQuery);
        return collection.find(findQuery).into(new ArrayList<>());
    }
}
