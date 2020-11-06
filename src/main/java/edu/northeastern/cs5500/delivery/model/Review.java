package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * This is a class representing a review object. Users should be able to retrieve any fields in this
 * class or create a new review object.
 */
@Data
public class Review implements Model {
    private ObjectId id;
    private String userName;
    private ObjectId restaurantId;
    private LocalDate createdTime;
    private String comment;
    private Double rating;

    /** @return true if this Review is valid */
    @JsonIgnore
    public boolean isValid() {
        return !comment.isEmpty() && rating >= 0.0 && rating <= 5.0;
    }
}
