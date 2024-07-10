package org.example.sportsacademiesmanagement.helper;

import org.example.sportsacademiesmanagement.data.DataStore;
import org.example.sportsacademiesmanagement.models.*;
import org.example.sportsacademiesmanagement.validator.Validator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Helper {
    // Inserting Dummy Data
    public static void initializeData() {
        Athlete athlete1 = new Athlete("John", "Doe", "Male", LocalDate.of(1990, 1, 1), "1234567890", true, 5);
        Athlete athlete2 = new Athlete("Jane", "Smith", "Female", LocalDate.of(1995, 5, 15), "0987654321", false, 3);
        DataStore.athletes.add(athlete1);
        DataStore.athletes.add(athlete2);

        // Calculating the registrationFees
        double registrationFee1 = DataStore.REGULAR_ATHLETE_FEE;
        if(athlete1.isProfessional()) {
            registrationFee1 = DataStore.PROFESSIONAL_ATHLETE_FEE;
        }
        double discount1 = Validator.calculateDiscount(athlete1.getExperienceLevel(), athlete1.isProfessional());

        // Create Enrollment
        Enrollment enrollment1 = new Enrollment(
                athlete1,
                LocalDate.now(),
                registrationFee1,
                discount1
        );

        // Create Payment
        Payment payment1 = new Payment(
                LocalDate.now(),
                "Cash",
                enrollment1
        );

        // Calculating the registrationFees
        double registrationFee2 = DataStore.REGULAR_ATHLETE_FEE;
        if(athlete2.isProfessional()) {
            registrationFee2 = DataStore.PROFESSIONAL_ATHLETE_FEE;
        }
        double discount2 = Validator.calculateDiscount(athlete2.getExperienceLevel(), athlete2.isProfessional());

        // Create Enrollment
        Enrollment enrollment2 = new Enrollment(
                athlete2,
                LocalDate.now(),
                registrationFee2,
                discount2
        );

        // Create Payment
        Payment payment2 = new Payment(
                LocalDate.now(),
                "Cash",
                enrollment2
        );

        // Add Enrollment and Payment to DataStore
        DataStore.enrollments.add(enrollment1);
        DataStore.enrollments.add(enrollment2);
        DataStore.payments.add(payment1);
        DataStore.payments.add(payment2);

        // Creating Sports
        Sport sport1 = new Sport("Soccer", false);
        Sport sport2 = new Sport("Basketball", true);
        Sport sport3 = new Sport("Tennis", false);

        DataStore.sports.add(sport1);
        DataStore.sports.add(sport2);
        DataStore.sports.add(sport3);

        // Creating Facilities
        Facility facility1 = new Facility("Facility 1", 50);
        Facility facility2 = new Facility("Facility 2", 100);
        DataStore.facilities.add(facility1);
        DataStore.facilities.add(facility2);

        // Adding Coaches
        ArrayList<String> degrees1 = new ArrayList<>();
        degrees1.add("Bachelor of Sports Science");
        degrees1.add("Certified Strength and Conditioning Specialist");

        ArrayList<String> degrees2 = new ArrayList<>();
        degrees2.add("Master of Kinesiology");
        degrees2.add("Certified Personal Trainer");

        Coach coach1 = new Coach("Ahmed", "Jamshaid", "Male",
                LocalDate.of(1995, 5, 15), "12344556", sport1, degrees1);

        Coach coach2 = new Coach("Ali", "Khan", "Male",
                LocalDate.of(1995, 5, 15), "12344556", sport2, degrees2);

        DataStore.coaches.add(coach1);
        DataStore.coaches.add(coach2);

        // Add training programs
        TrainingProgram trainingProgram1 = new TrainingProgram(sport1, facility1, coach1, 2, true,
                "Mixed", 20, DayOfWeek.MONDAY);
        TrainingProgram trainingProgram2 = new TrainingProgram(sport2, facility2, coach2, 3, false,
                "Male", 15, DayOfWeek.WEDNESDAY);

        // Adding Training Program
        DataStore.trainingPrograms.add(trainingProgram1);
        DataStore.trainingPrograms.add(trainingProgram2);
    }

    // Format the program details for display
    public static String formatProgramDetails(TrainingProgram program) {
        return String.format("%s, %s, %s %s, Min. Experience: %d, Reservation Required: %b, Gender: %s, Day: %s",
                program.getSport().getName(),
                program.getFacility().getName(),
                program.getCoach().getFirstName(),
                program.getCoach().getLastName(),
                program.getMinExperienceLevel(),
                program.isReservationRequired(),
                program.getParticipantGender(),
                program.getDayOfWeek().name());
    }
}
