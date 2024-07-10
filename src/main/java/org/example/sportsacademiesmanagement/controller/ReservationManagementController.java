package org.example.sportsacademiesmanagement.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.sportsacademiesmanagement.data.DataStore;
import org.example.sportsacademiesmanagement.helper.AthleteStringConverter;
import org.example.sportsacademiesmanagement.helper.Helper;
import org.example.sportsacademiesmanagement.helper.TrainingProgramStringConverter;
import org.example.sportsacademiesmanagement.models.*;
import org.example.sportsacademiesmanagement.validator.Validator;
import org.example.sportsacademiesmanagement.viewfactory.ViewFactory;

import java.time.LocalDate;

// Controller class for the Reservation Management Window
public class ReservationManagementController {

    @FXML
    private ChoiceBox<Athlete> athleteChoiceBox;

    @FXML
    private ChoiceBox<TrainingProgram> trainingProgramChoiceBox;

    @FXML
    private DatePicker reservationDatePicker;

    @FXML
    private TableView<TrainingProgramReservation> reservationTable;

    @FXML
    private TableColumn<TrainingProgramReservation, String> reservationIdColumn;

    @FXML
    private TableColumn<TrainingProgramReservation, String> athleteNameColumn;

    @FXML
    private TableColumn<TrainingProgramReservation, String> programNameColumn;

    @FXML
    private TableColumn<TrainingProgramReservation, LocalDate> reservationDateColumn;

    // List of reservations
    private final ObservableList<TrainingProgramReservation> reservationList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize choice boxes with detailed information
        athleteChoiceBox.setItems(FXCollections.observableArrayList(DataStore.athletes));
        athleteChoiceBox.setConverter(new AthleteStringConverter());

        trainingProgramChoiceBox.setItems(FXCollections.observableArrayList(DataStore.trainingPrograms));
        trainingProgramChoiceBox.setConverter(new TrainingProgramStringConverter());

        // Set up the table columns
        reservationIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUniqueID()));
        athleteNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getAthlete().getUniqueID() + ": " + cellData.getValue().getAthlete().getFirstName() + " " + cellData.getValue().getAthlete().getLastName()
        ));
        programNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                Helper.formatProgramDetails(cellData.getValue().getTrainingProgram())
        ));
        reservationDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReservationDate()));

        // Setting the reservations from the DataStore
        reservationList.addAll(DataStore.trainingProgramReservations);

        // Set the table data
        reservationTable.setItems(reservationList);
    }

    // Format the program details for display
    @FXML
    private void handleCreateReservation() {
        // Get the selected athlete, program, and reservation date
        Athlete selectedAthlete = athleteChoiceBox.getValue();
        TrainingProgram selectedProgram = trainingProgramChoiceBox.getValue();
        LocalDate reservationDate = reservationDatePicker.getValue();

        // Validate input
        if (!Validator.validateReservationInput(selectedAthlete, selectedProgram, reservationDate)) {
            return;
        }

        // Create new reservation
        TrainingProgramReservation newReservation = new TrainingProgramReservation(selectedAthlete, selectedProgram, reservationDate);

        // Add reservation to data store and list
        DataStore.trainingProgramReservations.add(newReservation);
        reservationList.add(newReservation);
        clearForm();
    }

    // Clearing User Selections
    @FXML
    private void clearForm() {
        athleteChoiceBox.setValue(null);
        trainingProgramChoiceBox.setValue(null);
        reservationDatePicker.setValue(null);
    }

    // Cancel Reservation
    @FXML
    private void handleCancelReservation() {
        // Get the selected reservation
        TrainingProgramReservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            Validator.showAlert("Cancellation Error", "No reservation selected for cancellation.");
            return;
        }

        // Validate cancellation date
        if (!Validator.canCancelReservation(selectedReservation.getReservationDate())) {
            Validator.showAlert("Cancellation Error", "Reservations can only be canceled up to 1 day before the scheduled date.");
            return;
        }

        // Remove reservation from data store and list
        DataStore.trainingProgramReservations.remove(selectedReservation);
        reservationList.remove(selectedReservation);
    }

    // Exit the Reservation Management Window
    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) reservationTable.getScene().getWindow();
        stage.close();

        // Load the Main Window
        ViewFactory.getInstance().showMainWindow();
    }
}
