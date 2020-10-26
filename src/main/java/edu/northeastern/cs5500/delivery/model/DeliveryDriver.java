package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * This is a class representing a delivery driver object. A driver should be able to
 * view the order and set pick up and delivered time. A driver inherits from a Person class.
 */
public class DeliveryDriver extends Person{
    private Integer DeliveryId;
    private Integer RestaurantId;
    private Integer OrderId;
    private LocalDate PickUpTime;
    private LocalDate DeliveredTime;

    /**
     * Constructor for DeliveryDriver
     */
    public DeliveryDriver(Integer DeliveryId, Integer RestaurantId, Integer OrderId, LocalDate PickUpTime, LocalDate DeliveredTime) {
        super(username, firstName, lastName, email, phoneNumber);
        this.DeliveryId = DliveryId;
        this.RestaurantId = RestaurantId;
        this.OrderId = OrderId;
        this.PickUpTime = PickUpTime;
        this.DeliveredTime = DeliveredTime;
    }

    /**
     * Return the DeliveryId
     * @return Integer - representing DeliveryId
     */
    public Integer getDeliveryId() {
        return this.DeliveryId;
    }

    /**
     * Return the RestaurantId
     * @return Integer - representing RestaurantId
     */
    public Integer getRestaurantId() {
        return this.RestaurantId;
    }

    /**
     * Return the OrderId
     * @return Integer - representing OrderId
     */
    public Integer getOrderId() {
        return this.OrderId;
    }
    
    /**
     * Return the PickUpTime
     * @return LocalDate - representing PickUpTime
     */
    public LocalDate getPickUpTime() {
        return this.PickUpTime;
    }
    
    /**
     * Return the DeliveredTime
     * @return LocalDate - representing DeliveredTime
     */
    public LcalDate getDeliveredTime() {
        return this.DeliveredTime;
    }
    
    /**
     * Set DeliveryId
     * @param Integer - representing DeliveryId
     */
    public void setDeliveryId(Integer DeliveryId) {
        this.DeliveryId = DeliveryId;
    }

    /**
     * Set RestaurantId
     * @param Integer - representing RestaurantId
     */
    public void setRestaurantId(Integer RestaurantId) {
        this.RestaurantId = RestaurantId;
    }

    /**
     * Set OrderId
     * @param Integer - representing OrderId
     */
    public void setOrderId(Integer OrderId) {
        this.OrderId = OrderId;
    }

    /**
     * Set PickUpTime
     * @param LocalDate - representing PickUpTime
     */
    public void setPickUpTime(LocalDate PickUpTime) {
        this.PickUpTime = PickUpTime;
    }

    /**
     * Set DeliveredTime
     * @param LocalDate - representing DeliveredTime
     */
    public void setDeliveredTime(LocalDate DeliveredTime) {
        this.DeliveredTime = DeliveredTime;
    }
}