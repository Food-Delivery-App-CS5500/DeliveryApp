package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
public class Restaurant implements Model {
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
    private ObjectId id;

    /** @return true if this restaurant is valid */
    @JsonIgnore
    public boolean isValid() {
        return isActive == true;
    }
}
