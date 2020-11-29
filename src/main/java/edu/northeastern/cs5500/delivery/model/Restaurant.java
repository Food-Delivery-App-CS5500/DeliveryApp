package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Restaurant implements Model {
    private ObjectId id;
    private String restaurantName;
    private String restaurantDescription;
    private HashMap<String, FoodItem> restaurantMenu;
    private String listedHours;
    private String cuisineType;
    private Boolean isActive;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private Integer zipCode;
    // private Integer earnings;

    /** @return true if this restaurant is valid */
    @JsonIgnore
    public boolean isValid() {
        return (isActive == true) && (restaurantMenu.size() > 0);
    }
}
