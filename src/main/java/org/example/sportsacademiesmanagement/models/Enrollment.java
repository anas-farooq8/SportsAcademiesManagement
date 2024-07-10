package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;

public class Enrollment implements PriceList {
    private final int uniqueID;
    private Athlete athlete;
    private LocalDate date;
    private double cost;
    private double discountPercentage;

    public Enrollment(Athlete athlete, LocalDate date, double cost, double discountPercentage) {
        // The Enrollment code will be the same as the athlete's code since each athlete will have a unique registration.
        this.uniqueID = athlete.getUniqueID();
        this.athlete = athlete;
        this.date = date;
        this.cost = cost;
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    public int getUniqueID() {
        return uniqueID;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateTotalPrice() {
        return cost - (cost * discountPercentage);
    }
}
