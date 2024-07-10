package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Coach extends User {
    private Sport sport;
    private ArrayList<String> degrees;

    public Coach(String firstName, String lastName, String gender, LocalDate dateOfBirth, String contactInfo,
                 Sport sport, ArrayList<String> degrees) {
        super(firstName, lastName, gender, dateOfBirth, contactInfo);
        this.sport = sport;
        this.degrees = degrees;
    }

    // Getters and Setters
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public ArrayList<String> getDegrees() {
        return degrees;
    }

    public void setDegrees(ArrayList<String> degrees) {
        this.degrees = degrees;
    }
}
