package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
// import dagger.Module;
// import dagger.Provides;

@Data
public class Restaurant {
    private Integer restaurantId;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantMenu;
    private String listedHours;
    private CuisineType cuisineType;
    private Boolean isActive;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private Integer zipCode;

    /*
    * Restaurant Constructor
    */
    public Restaurant(Integer restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.isActive = true;
    }

}