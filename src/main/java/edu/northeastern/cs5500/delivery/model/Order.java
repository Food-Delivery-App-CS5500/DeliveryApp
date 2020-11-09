package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
    private LocalDate orderTime;
    private LocalDate scheduleDelivery;
    private LocalDate deliveredTime;
    private OrderStatus status;

    /** @return true if this order is valid */
    @JsonIgnore
    public boolean isValid() {
        return !userName.isEmpty() && !orderFoodItems.isEmpty();
    }
}
