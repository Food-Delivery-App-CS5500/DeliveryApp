package edu.northeastern.cs5500.delivery.model;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    public Class<Delivery> provideDeliveryClass() {
        return Delivery.class;
    }

    @Provides
    public Class<Restaurant> provideRestaurantClass() {
        return Restaurant.class;
    }

    @Provides
    public Class<CreditCard> providesCreditCardClass() {
        return CreditCard.class;
    }

    @Provides
    public Class<User> providesUserClass() {
        return User.class;
    }

    @Provides
    public Class<DeliveryDriver> providesDeliveryDriverClass() {
        return DeliveryDriver.class;
    }

    @Provides
    public Class<FoodItem> provideFoodItemClass() {
        return FoodItem.class;
    }

    @Provides
    public Class<Order> provideOrderClass() {
        return Order.class;
    }
}
