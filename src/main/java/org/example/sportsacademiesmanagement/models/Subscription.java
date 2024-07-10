package org.example.sportsacademiesmanagement.models;

public class Subscription implements PriceList {
    private static int idCounter = 0;
    private final String uniqueID;
    private Athlete athlete;
    private TrainingProgram trainingProgram;
    private double monthlyCost;

    public Subscription(Athlete athlete, TrainingProgram trainingProgram, double monthlyCost) {
        this.uniqueID = generateUniqueID(athlete.getUniqueID(), trainingProgram.getUniqueID());
        this.athlete = athlete;
        this.trainingProgram = trainingProgram;
        this.monthlyCost = monthlyCost;
    }

    private String generateUniqueID(int athleteID, int trainingProgramID) {
        return athleteID + "_" + trainingProgramID + "_" + ++idCounter;
    }

    // Getters and Setters
    public String getUniqueID() {
        return uniqueID;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public double calculateTotalPrice() {
        // Assuming monthlyCost is already discounted
        return monthlyCost;
    }
}
