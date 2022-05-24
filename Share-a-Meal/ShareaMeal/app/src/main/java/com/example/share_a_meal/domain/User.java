package com.example.share_a_meal.domain;

import java.io.Serializable;

public class User implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String street;
    private final String city;
    private final String emailAddress;
    private final String phoneNumber;
    private final String[] roles;

    public User(String firstName, String lastName, String street, String city, String emailAddress, String phoneNumber, String[] roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String[] getRoles() {
        return roles;
    }
}