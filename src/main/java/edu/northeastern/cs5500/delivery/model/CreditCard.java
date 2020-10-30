package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.Data;

/**
 * This is a class representing a creditcard object. It contains an integer of cardnumber, a string
 * of userName and a LocalDate to store expirationDate.
 */
@Data
public class CreditCard {
    private Integer cardNumber;
    private String userName;
    private LocalDate expirationDate;

    /** @return true if this credit card is valid */
    @JsonIgnore
    public boolean isValid() {
        return cardNumber != null && expirationDate != null && !userName.isEmpty();
    }
}
