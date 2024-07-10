package org.example.sportsacademiesmanagement.models;

import java.time.DayOfWeek;

public class TrainingProgram {
    private static int idCounter = 0;
    private final int uniqueID;
    private Sport sport;
    private Facility facility;
    private Coach coach;
    private int minExperienceLevel;
    private boolean reservationRequired;
    private String participantGender; // Could be enum or string ("male", "female", "mixed")
    private int duration; // in minutes
    private DayOfWeek dayOfWeek;

    public TrainingProgram(Sport sport, Facility facility, Coach coach, int minExperienceLevel,
                           boolean reservationRequired, String participantGender, int duration, DayOfWeek dayOfWeek) {
        this.uniqueID = ++idCounter;
        this.sport = sport;
        this.facility = facility;
        this.coach = coach;
        this.minExperienceLevel = minExperienceLevel;
        this.reservationRequired = reservationRequired;
        this.participantGender = participantGender;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    // Getters and Setters
    public int getUniqueID() {
        return uniqueID;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public int getMinExperienceLevel() {
        return minExperienceLevel;
    }

    public void setMinExperienceLevel(int minExperienceLevel) {
        this.minExperienceLevel = minExperienceLevel;
    }

    public boolean isReservationRequired() {
        return reservationRequired;
    }

    public void setReservationRequired(boolean reservationRequired) {
        this.reservationRequired = reservationRequired;
    }

    public String getParticipantGender() {
        return participantGender;
    }

    public void setParticipantGender(String participantGender) {
        this.participantGender = participantGender;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
