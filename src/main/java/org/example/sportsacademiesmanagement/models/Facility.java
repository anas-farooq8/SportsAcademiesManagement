package org.example.sportsacademiesmanagement.models;

public class Facility {
    private String name;
    private int maxCapacity;

    public Facility(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
