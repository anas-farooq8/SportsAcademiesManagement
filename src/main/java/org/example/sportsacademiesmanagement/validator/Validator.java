package org.example.sportsacademiesmanagement.validator;

import javafx.scene.control.*;
import org.example.sportsacademiesmanagement.data.DataStore;
import org.example.sportsacademiesmanagement.models.Athlete;
import org.example.sportsacademiesmanagement.models.TrainingProgram;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {
    // Prevent instantiation

    // Validate the input fields of the athlete form
    public static boolean validateAthleteForm(TextField firstNameField, TextField lastNameField, ChoiceBox<String> genderField,
                                              DatePicker dobPicker, TextField contactField, ToggleGroup professionalGroup,
                                              ChoiceBox<Integer> experienceLevelField) {

        // Check if any of the fields are empty
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || genderField.getValue() == null ||
                dobPicker.getValue() == null || contactField.getText().isEmpty() || professionalGroup.getSelectedToggle() == null ||
                experienceLevelField.getValue() == null) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        // Check if the contact number contains only digits
        if (!contactField.getText().matches("\\d+")) {
            showAlert("Error", "Contact number must contain only digits.");
            return false;
        }

        // Check if the experience level is between 1 and 5
        if (!validateExperienceLevel(experienceLevelField.getValue())) {
            showAlert("Error", "Experience level must be between 1 and 5.");
            return false;
        }

        return true;
    }

    // Validate the input fields of the training program form
    public static boolean validateExperienceLevel(Integer experienceLevel) {
        return experienceLevel >= 1 && experienceLevel <= 5;
    }

    // Validate the input fields of the training program form
    public static boolean validateSubscriptionInput(Athlete athlete, TrainingProgram program, String monthlyCostText) {
        if (athlete == null || program == null || monthlyCostText == null || monthlyCostText.isEmpty()) {
            showAlert("Validation Error", "All fields must be filled.");
            return false;
        }

        // Validate professional status
        if (program.getSport().isProfessionalOnly() && !athlete.isProfessional()) {
            showAlert("Validation Error", "This program is for professional athletes only.");
            return false;
        }

        // Validate gender
        if (!validateGender(athlete.getGender(), program.getParticipantGender())) {
            showAlert("Validation Error", "Athlete's gender does not match the program's participant gender.");
            return false;
        }

        // Validate experience level
        if (athlete.getExperienceLevel() < program.getMinExperienceLevel()) {
            showAlert("Validation Error", "Athlete's experience level is below the minimum required for this program.");
            return false;
        }

        // Validate monthly cost
        try {
            Double.parseDouble(monthlyCostText);
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Monthly Cost must be a valid number.");
            return false;
        }

        return true;
    }

    // Validate the input fields of the training program form (Gender)
    private static boolean validateGender(String athleteGender, String programGender) {
        if (programGender.equalsIgnoreCase("Mixed")) {
            return true; // Mixed allows all genders
        }
        if (athleteGender.equalsIgnoreCase("Mixed")) {
            return true; // Mixed athletes can participate in all programs
        }
        return athleteGender.equalsIgnoreCase(programGender);
    }

    // Calculate the discount based on the athlete's experience level and professional status
    public static double calculateDiscount(int experienceLevel, boolean isProfessional) {
        double discount = 0.0;
        if (experienceLevel <= 2) {
            discount = 0.1;
        } else if (experienceLevel <= 4) {
            discount = 0.2;
        } else if (experienceLevel == 5) {
            discount = 0.3;
        }
        if (isProfessional) {
            discount += 0.2;
        }
        return discount;
    }

    // Validate the input fields of the training program form
    public static boolean validateReservationInput(Athlete athlete, TrainingProgram program, LocalDate reservationDate) {
        // Check if any of the fields are empty
        if (athlete == null || program == null || reservationDate == null) {
            showAlert("Validation Error", "All fields must be filled.");
            return false;
        }

        // Check if the selected program requires reservations
        if (!program.isReservationRequired()) {
            showAlert("Validation Error", "Selected training program does not require reservations.");
            return false;
        }


        // Check if the athlete has a subscription to the selected program
        if (!athleteHasSubscription(athlete, program)) {
            showAlert("Validation Error", "Athlete must have a subscription to the selected training program.");
            return false;
        }

        // Check if the athlete already has a reservation for the selected date
        if (athleteHasExistingReservation(athlete, program, reservationDate)) {
            showAlert("Validation Error", "Athlete already has a reservation for the selected date and training program.");
            return false;
        }

        return true;
    }

    // Validate the input fields of the training program form
    public static boolean canCancelReservation(LocalDate reservationDate) {
        // Check if the reservation date is at least one day in the future
        LocalDate currentDate = LocalDate.now();
        return !reservationDate.isBefore(currentDate.plusDays(1));
    }

    // Validate the input fields of the training program form
    private static boolean athleteHasSubscription(Athlete athlete, TrainingProgram program) {
        // Check if the athlete has a subscription to the selected program
        return DataStore.subscriptions.stream()
                .anyMatch(subscription -> subscription.getAthlete().equals(athlete) && subscription.getTrainingProgram().equals(program));
    }

    // Validate the input fields of the training program form
    private static boolean athleteHasExistingReservation(Athlete athlete, TrainingProgram program, LocalDate date) {
        return DataStore.trainingProgramReservations.stream()
                .anyMatch(reservation -> reservation.getAthlete().equals(athlete) &&
                        reservation.getTrainingProgram().equals(program) &&
                        reservation.getReservationDate().equals(date));
    }

    // Show an alert dialog with the specified title and content
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
