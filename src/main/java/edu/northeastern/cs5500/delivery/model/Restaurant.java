package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;

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
    // @Inject (?)
    public Restaurant(Integer restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName
        this.isActive = true;
    }

    /*
    * Restaurant description setter/edit
    */
    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    /*
    * Restaurant Menu setter/edit
    */
    public void setRestaurantMenu(String restaurantMenu) {
        this.restaurantMenu = restaurantMenu
    }

    /*
    * Restaurant listedHours setter/edit
    */
    public void setRestaurantHours(String listedHours) {
        this.listedHours = listedHours;
    }

    /*
    * Restaurant CuisineType setter/edit
    */
    public void setRestaurantMenu(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    /*
    * Restaurant isActive getter
    * Is set to true by default for all restaurants
    */
    public Boolean getIsActive() {
        return this.isActive;
    }

    /*
    * Restaurant isActive setter/edit
    */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /*
    * Restaurant StreetAddress1 setter/edit
    */
    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    /*
    * Restaurant StreetAddress1 setter/edit
    */
    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    /*
    * Restaurant City setter/edit
    */
    public void setRestaurantCity(String city) {
        this.city = city;
    }

    /*
    * Restaurant state setter/edit
    */
    public void setRestaurantState(String state) {
        this.state = state;
    }

    /*
    * Restaurant zipcode setter/edit
    */
    public void setRestaurantZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }


}