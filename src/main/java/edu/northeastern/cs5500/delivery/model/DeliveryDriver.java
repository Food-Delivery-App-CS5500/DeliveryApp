package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import lombok.EqualsAndHashCode;
import lombok.Data;

/**
 * This is a class representing a delivery driver object. A driver should be able to view the order
 * and set pick up and delivered time. A driver inherits from a Person class.
 */
@EqualsAndHashCode(callSuper = true)
@Data

public class DeliveryDriver extends Person {
    private ObjectId Id;
    private ObjectId RestaurantId;
    private ObjectId OrderId;
    private LocalDate PickUpTime;
    private LocalDate DeliveredTime;

    /** Constructor for DeliveryDriver */
    public DeliveryDriver(
            String username,
            String firstName,
            String lastName,
            String email,
            Integer phoneNumber,
            ObjectId Id,
            // ObjectId RestaurantId,
            ObjectId OrderId)
            // LocalDate PickUpTime,
            // LocalDate DeliveredTime
             {
        super(username, firstName, lastName, email, phoneNumber);
        this.Id = Id;
        // this.RestaurantId = RestaurantId;
        this.OrderId = OrderId;
        // this.PickUpTime = PickUpTime;
        // this.DeliveredTime = DeliveredTime;
    }

    /** @return true if this User is valid */
    @JsonIgnore
    public boolean isValid() {
        return !username.isEmpty()
                && !firstName.isEmpty()
                && !lastName.isEmpty()
                && Integer.toString(phoneNumber).length() == 10
                && !email.isEmpty();
    }
}
