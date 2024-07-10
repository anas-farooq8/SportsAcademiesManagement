package org.example.sportsacademiesmanagement.models;

import java.time.LocalDate;

public class TrainingProgramReservation {
    private final String uniqueID;
    private Athlete athlete;
    private TrainingProgram trainingProgram;
    private LocalDate reservationDate;

    public TrainingProgramReservation(Athlete athlete, TrainingProgram trainingProgram, LocalDate reservationDate) {
        this.uniqueID = generateUniqueID(trainingProgram.getUniqueID(), athlete.getUniqueID(), reservationDate);
        this.athlete = athlete;
        this.trainingProgram = trainingProgram;
        this.reservationDate = reservationDate;
    }

    private String generateUniqueID(int trainingProgramID, int athleteID, LocalDate date) {
        return trainingProgramID + "_" + athleteID + "_" + date.toString().replace("-", "");
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
