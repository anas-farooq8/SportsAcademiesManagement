package org.example.sportsacademiesmanagement.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.sportsacademiesmanagement.data.DataStore;
import org.example.sportsacademiesmanagement.models.Athlete;
import org.example.sportsacademiesmanagement.models.Enrollment;
import org.example.sportsacademiesmanagement.models.Payment;
import org.example.sportsacademiesmanagement.validator.Validator;
import org.example.sportsacademiesmanagement.viewfactory.ViewFactory;

import java.time.LocalDate;

// Controller for the Athlete Management window
public class AthleteManagementController {

    // FXML elements
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private TextField contactField;

    @FXML
    private RadioButton professionalTrue;

    @FXML
    private RadioButton professionalFalse;

    @FXML
    private ToggleGroup professionalGroup;

    @FXML
    private ChoiceBox<Integer> experienceLevelField;

    @FXML
    private TableView<Athlete> athleteTable;

    @FXML
    private TableColumn<Athlete, Integer> idColumn;

    @FXML
    private TableColumn<Athlete, String> firstNameColumn;

    @FXML
    private TableColumn<Athlete, String> lastNameColumn;

    @FXML
    private TableColumn<Athlete, String> genderColumn;

    @FXML
    private TableColumn<Athlete, LocalDate> dobColumn;

    @FXML
    private TableColumn<Athlete, String> contactColumn;

    @FXML
    private TableColumn<Athlete, Boolean> professionalColumn;

    @FXML
    private TableColumn<Athlete, Integer> experienceLevelColumn;

    @FXML
    private Button registerBtn;

    // Initialize the window components
    @FXML
    public void initialize() {
        // Set the cell value factories for the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("uniqueID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        professionalColumn.setCellValueFactory(new PropertyValueFactory<>("professional"));
        experienceLevelColumn.setCellValueFactory(new PropertyValueFactory<>("experienceLevel"));

        // Populate the Fields
        dobPicker.setValue(LocalDate.of(2000, 1, 1));
        genderField.setItems(FXCollections.observableArrayList("Male", "Female", "Mixed"));
        experienceLevelField.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        professionalGroup = new ToggleGroup();
        professionalTrue.setToggleGroup(professionalGroup);
        professionalFalse.setToggleGroup(professionalGroup);

        // Populate the table
        refreshAthleteTable();

        // Handle table row selection
        athleteTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for single click
                Athlete selectedAthlete = athleteTable.getSelectionModel().getSelectedItem();
                // For editing Purposes
                if (selectedAthlete != null) {
                    placeAthleteDetailsInForm(selectedAthlete);
                    registerBtn.setVisible(false);
                }
            }
        });
    }


    // Handle the registration of an athlete
    @FXML
    private void handleRegisterAthlete(ActionEvent event) {
        // Validate inputs
        if (!Validator.validateAthleteForm(firstNameField, lastNameField, genderField, dobPicker, contactField, professionalGroup, experienceLevelField)) {
            return;
        }

        // Parsing the inputs
        Athlete athlete = getAthlete();

        // Add the athlete to the DataStore
        DataStore.athletes.add(athlete);
        // Handle the registration payment
        handleRegistrationPayment(athlete);
        // Refresh the athlete table and clear the form
        refreshAthleteTable();
        clearForm();
    }

    // Get the athlete from the form fields
    private Athlete getAthlete() {
        LocalDate dob = dobPicker.getValue();
        boolean isProfessional = professionalGroup.getSelectedToggle().equals(professionalTrue);
        int experienceLevel = experienceLevelField.getValue();

        // Create the athlete
        return new Athlete(
                firstNameField.getText(),
                lastNameField.getText(),
                genderField.getValue(),
                dob,
                contactField.getText(),
                isProfessional,
                experienceLevel
        );
    }

    // Refresh the athlete table
    private void refreshAthleteTable() {
        // Clear the table and add all athletes from the DataStore
        athleteTable.getItems().setAll(DataStore.athletes);
    }

    // Clear the form
    @FXML
    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        genderField.setValue(null);
        dobPicker.setValue(LocalDate.of(2000, 1, 1));
        contactField.clear();
        professionalGroup.selectToggle(null);
        experienceLevelField.setValue(null);

        // Remove the selected athlete
        athleteTable.getSelectionModel().clearSelection();

        // Make the register button visible
        registerBtn.setVisible(true);
    }


    // Handle the editing of an athlete
    @FXML
    private void handleEditAthlete(ActionEvent event) {
        // Get the selected athlete
        Athlete selectedAthlete = athleteTable.getSelectionModel().getSelectedItem();
        if (selectedAthlete == null) {
            Validator.showAlert("Error", "No athlete selected for editing.");
            return;
        }

        // Validate inputs
        if (!Validator.validateAthleteForm(firstNameField, lastNameField, genderField, dobPicker, contactField, professionalGroup, experienceLevelField)) {
            return;
        }

        // Update the athlete
        selectedAthlete.setFirstName(firstNameField.getText());
        selectedAthlete.setLastName(firstNameField.getText());
        selectedAthlete.setGender(genderField.getValue());
        selectedAthlete.setDateOfBirth(dobPicker.getValue());
        selectedAthlete.setContactInfo(contactField.getText());
        selectedAthlete.setProfessional(professionalGroup.getSelectedToggle().equals(professionalTrue));
        selectedAthlete.setExperienceLevel(experienceLevelField.getValue());

        // Refresh the athlete table and clear the form
        refreshAthleteTable();
        clearForm();
    }

    // Handle the deletion of an athlete
    private void placeAthleteDetailsInForm(Athlete selectedAthlete) {
        // Populate the form fields with the selected athlete's details, for editing the athlete
        firstNameField.setText(selectedAthlete.getFirstName());
        lastNameField.setText(selectedAthlete.getLastName());
        genderField.setValue(selectedAthlete.getGender());
        dobPicker.setValue(selectedAthlete.getDateOfBirth());
        contactField.setText(selectedAthlete.getContactInfo());
        professionalGroup.selectToggle(selectedAthlete.isProfessional() ? professionalTrue : professionalFalse);
        experienceLevelField.setValue(selectedAthlete.getExperienceLevel());
    }



    // Handle the deletion of an athlete
    private void handleRegistrationPayment(Athlete athlete) {
        // Calculate discount based on experience level (example logic)
        double registrationFee = DataStore.REGULAR_ATHLETE_FEE;
        if(athlete.isProfessional()) {
            registrationFee = DataStore.PROFESSIONAL_ATHLETE_FEE;
        }
        // Calculate discount based on experience level
        double discount = Validator.calculateDiscount(athlete.getExperienceLevel(), athlete.isProfessional());

        // Create Enrollment
        Enrollment enrollment = new Enrollment(
                athlete,
                LocalDate.now(),
                registrationFee,
                discount
        );

        // Create Payment
        Payment payment = new Payment(
                LocalDate.now(),
                "Cash",
                enrollment
        );

        // Add Enrollment and Payment to DataStore
        DataStore.enrollments.add(enrollment);
        DataStore.payments.add(payment);

        // Simulating payment process (e.g., showing payment confirmation dialog)
        showPaymentConfirmation(payment);
    }

    // Show payment confirmation dialog
    private void showPaymentConfirmation(Payment payment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Payment successful! Total cost: $" + payment.getTotalCost());
        alert.showAndWait();
    }

    // Handle the exit button
    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load the main window
        ViewFactory.getInstance().showMainWindow();
    }
}
