package edu.northeastern.cs5500.delivery.model;

abstract class Person {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;

    /** Constructor for Person */
    public Person(
            String username, String firstName, String lastName, String email, Integer phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * username getter
     *
     * @return the username as a string
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * first name getter
     *
     * @return the first name as a string
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * last name getter
     *
     * @return the last name as a string
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * email getter
     *
     * @return the email as a string
     */
    public String getEmail() {
        return this.email;
    }

    /** phone number getter the phone number as an integer */
    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }

    /** sets first name based on method argument */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** sets last name based on method argument */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** sets email based on method argument */
    public void setEmail(String email) {
        this.email = email;
    }

    /** sets phone number based on method argument */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
