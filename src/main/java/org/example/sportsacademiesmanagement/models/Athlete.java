package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;

public class Athlete extends User {
    private boolean professional;
    private int experienceLevel; // From 1 to 5

    public Athlete(String firstName, String lastName, String gender, LocalDate dateOfBirth, String contactInfo,
                   boolean professional, int experienceLevel) {
        super(firstName, lastName, gender, dateOfBirth, contactInfo);
        this.professional = professional;
        this.experienceLevel = experienceLevel;
    }

    // Getters and Setters
    public boolean isProfessional() {
        return professional;
    }

    public void setProfessional(boolean professional) {
        this.professional = professional;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
