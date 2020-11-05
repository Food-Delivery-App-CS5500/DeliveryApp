package edu.northeastern.cs5500.delivery.model;

import lombok.Data;

@Data
abstract class Person implements Model {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Integer phoneNumber;

    /** Constructor for Person */
    public Person(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            Integer phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
