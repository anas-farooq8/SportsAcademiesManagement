package org.example.sportsacademiesmanagement.data;

import org.example.sportsacademiesmanagement.models.*;

import java.util.ArrayList;
import java.util.List;

// Database
public class DataStore {

    public static final double REGULAR_ATHLETE_FEE = 50.0;
    public static final double PROFESSIONAL_ATHLETE_FEE = 20.0;

    // Stores the data of the application
    public static List<Sport> sports = new ArrayList<>();
    public static List<Facility> facilities = new ArrayList<>();
    public static List<Athlete> athletes = new ArrayList<>();
    public static List<Coach> coaches = new ArrayList<>();
    public static List<Enrollment> enrollments = new ArrayList<>();
    public static List<Payment> payments = new ArrayList<>();
    public static List<Subscription> subscriptions = new ArrayList<>();
    public static List<TrainingProgram> trainingPrograms = new ArrayList<>();
    public static List<TrainingProgramReservation> trainingProgramReservations = new ArrayList<>();

}
