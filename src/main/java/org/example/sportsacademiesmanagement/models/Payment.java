package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;

public class Payment implements PriceList {
    private static int idCounter = 0;
    private final int uniqueID;
    private LocalDate date;
    private String paymentMethod;
    private PriceList relatedEntity; // Subscription or Enrollment
    private double totalCost;

    public Payment(LocalDate date, String paymentMethod, PriceList relatedEntity) {
        this.uniqueID = ++idCounter;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.relatedEntity = relatedEntity;
        this.totalCost = relatedEntity.calculateTotalPrice();
    }

    // Getters and Setters
    public int getUniqueID() {
        return uniqueID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PriceList getRelatedEntity() {
        return relatedEntity;
    }

    public void setRelatedEntity(PriceList relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public double calculateTotalPrice() {
        return totalCost;
    }
}

