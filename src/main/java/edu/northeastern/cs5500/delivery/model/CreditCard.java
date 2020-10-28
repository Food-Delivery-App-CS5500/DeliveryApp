package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;

/**
 * This is a class representing a creditcard object. It contains an integer of cardnumber, a string
 * of userName and a LocalDate to store expirationDate.
 */
public class CreditCard {
    private Integer cardNumber;
    private String userName;
    private LocalDate expirationDate;

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
}
