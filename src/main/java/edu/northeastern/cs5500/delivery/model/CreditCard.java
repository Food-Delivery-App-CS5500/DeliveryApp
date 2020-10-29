package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
        return cardNumber != null && !cardNumber.isEmpty() and expirationDate != null && !expirationDate.isEmpty()
    }
    '''
    /** Constructor for a creditcard */
    public CreditCard(Integer cardNumber, String userName, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.expirationDate = expirationDate;
    }

    /**
     * Return the creditCard number
     *
     * @return Integer - representing card number
     */
    public Integer getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Return the userName
     *
     * @return String - representing userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Return the expiration date.
     *
     * @return LocalDate - representing card expiration date.
     */
    public LocalDate getExpDate() {
        return this.expirationDate;
    }
    '''
}

}

