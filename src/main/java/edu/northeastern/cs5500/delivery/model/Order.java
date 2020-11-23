package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.HashMap;
import lombok.Data;
import org.bson.types.ObjectId;

/** This is a class representing a food order object. It contains information for a food order. */
@Data
public class Order implements Model {
    private ObjectId id;
    private String userName;
    private ObjectId restaurantId;
    private HashMap<ObjectId, Integer> orderFoodItems;
    private String comment;
    private LocalDateTime orderTime;
    private LocalDateTime scheduleDelivery;
    private LocalDateTime deliveredTime;
    private OrderStatus status;
    private CreditCard paymentCard;

    /** @return true if this order is valid */
    @JsonIgnore
    public boolean isValid() {
        return restaurantId != null && orderFoodItems != null && !orderFoodItems.isEmpty();
    }
}
