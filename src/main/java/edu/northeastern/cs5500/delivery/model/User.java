package edu.northeastern.cs5500.delivery.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;

public class User extends Person {
    private String address;
    private String city;
    private String state;
    private String zip;

    public User(String username, String firstName, String lastName, String email, Integer phoneNumber, String address, String city, String state, String zip) {
        super(username, firstName, lastName, email, phoneNumber);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    /**
     * Getter for address
     * @return address as a string
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for city
     * @return city as a string
     */
    public String getCity() {
        return city;
    }

    /**
     * getter for state
     * @return state as a string
     */
    public String getState() {
        return state;
    }

    /**
     * getter for zip
     * @return zip as a string
     */
    public String getZip() {
        return zip;
    }

    /**
     * sets address based on method argument
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * sets city based on method argument
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * sets state based on method argument
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * sets zip based on method argument
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
}
