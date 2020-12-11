package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.*;
import java.time.LocalDate;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * This is a class representing a creditcard object. It contains an integer of cardnumber, a string
 * of userName and a LocalDate to store expirationDate.
 */
@Data
public class CreditCard implements Model {
    private ObjectId id;
    private Long cardNumber;
    private String userName;
    private Integer expirationYear;
    private Integer expirationMonth;

    /** @return true if this credit card is valid */
    @JsonIgnore
    public boolean isValid() {
        LocalDate today = LocalDate.now();
        Integer year = today.getYear();
        Integer month = today.getMonthValue();
        // System.out.println("Year" + year);
        // System.out.println("expY" + expirationYear);
        // System.out.println("Month" + month);
        // System.out.println("expM" + expirationMonth);
        if (expirationYear == null || expirationYear < year) {
            return false;
        }
        if (expirationMonth == null || expirationYear == year && expirationMonth <= month) {
            return false;
        }
        return cardNumber != null && !userName.isEmpty();
    }
}
