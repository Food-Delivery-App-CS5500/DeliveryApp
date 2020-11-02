package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.Data;
import org.bson.types.ObjectId;

/** This is a class representing a food order object. It contains information for a food order. */
@Data
public class Order implements Model {
    private ObjectId id;
    private String userName;
    private ObjectId restaurantId;
    private String foodItem;
    private Double price;
    private Integer quantity;
    private String comment;
    private LocalDate orderTime;
    private LocalDate scheduleDelivery;
    private LocalDate deliveredTime;
    private OrderStatus status;

    /** @return true if this order is valid */
    @JsonIgnore
    public boolean isValid() {
        return quantity != null && quantity > 0 && !userName.isEmpty() && !foodItem.isEmpty();
    }
}