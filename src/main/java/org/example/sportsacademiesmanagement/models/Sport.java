package org.example.sportsacademiesmanagement.models;

public class Sport {
    private String name;
    private boolean professionalOnly;

    public Sport(String name, boolean professionalOnly) {
        this.name = name;
        this.professionalOnly = professionalOnly;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProfessionalOnly() {
        return professionalOnly;
    }

    public void setProfessionalOnly(boolean professionalOnly) {
        this.professionalOnly = professionalOnly;
    }
}
