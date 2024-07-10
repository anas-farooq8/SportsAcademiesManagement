package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;

public class User {
    private static int idCounter = 0;
    private final int uniqueID;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String contactInfo;

    public User(String firstName, String lastName, String gender, LocalDate dateOfBirth, String contactInfo) {
        this.uniqueID = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getUniqueID() {
        return uniqueID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
