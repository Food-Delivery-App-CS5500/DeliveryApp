package edu.northeastern.cs5500.delivery.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.model.Delivery;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.model.Review;

@Module
public class RepositoryModule {
    @Provides
    public GenericRepository<Delivery> provideDeliveryRepository() {
        return new InMemoryRepository<>();
    }

    @Provides
    public GenericRepository<Restaurant> provideRestaurantRepository() {
        return new InMemoryRepository<>();
    }

    @Provides
    public GenericRepository<CreditCard> provideCreditCardRepository() {
        return new InMemoryRepository<>();
    }

    @Provides
    public GenericRepository<Review> provideReviewRepository() {
        return new InMemoryRepository<>();
    }

    @Provides
    public GenericRepository<Order> provideOrderRepository() {
        return new InMemoryRepository<>();
    }
}

/*
=======

// Here's an example of how you imght swap out the in-memory repository for a database-backed
// repository:
package edu.northeastern.cs5500.delivery.repository;
import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.delivery.model.Delivery;
import edu.northeastern.cs5500.delivery.service.MongoDBService;

    @Module public class RepositoryModule {
    @Provides
    public GenericRepository<Delivery> provideDeliveryRepository(MongoDBService mongoDBService) {
        return new MongoDBRepository<>(Delivery.class, mongoDBService);
    }

    @Module public class RepositoryModule {
    @Provides
    public GenericRepository<Restaurant> provideRestaurantRepository(MongoDBService mongoDBService) {
        return new MongoDBRepository<>(Restaurant.class, mongoDBService);
    }
}
*/
