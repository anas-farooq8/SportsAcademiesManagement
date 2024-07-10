package org.example.sportsacademiesmanagement.controller;

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
import java.util.Optional;

// Subscription Management Controller
public class SubscriptionManagementController {

    @FXML
    private ChoiceBox<Athlete> athleteChoiceBox;

    @FXML
    private ChoiceBox<TrainingProgram> trainingProgramChoiceBox;

    @FXML
    private TextField monthlyCostField;

    @FXML
    private TableView<Subscription> subscriptionTable;

    @FXML
    private TableColumn<Subscription, String> subscriptionIdColumn;

    @FXML
    private TableColumn<Subscription, String> athleteNameColumn;

    @FXML
    private TableColumn<Subscription, String> programNameColumn;

    @FXML
    private TableColumn<Subscription, String> monthlyCostColumn;

    private final ObservableList<Subscription> subscriptionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize choice boxes with detailed information
        athleteChoiceBox.setItems(FXCollections.observableArrayList(DataStore.athletes));
        athleteChoiceBox.setConverter(new AthleteStringConverter());

        trainingProgramChoiceBox.setItems(FXCollections.observableArrayList(DataStore.trainingPrograms));
        trainingProgramChoiceBox.setConverter(new TrainingProgramStringConverter());

        // Set up the table columns
        subscriptionIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUniqueID()));
        athleteNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getAthlete().getUniqueID() + ": " + cellData.getValue().getAthlete().getFirstName() + " " + cellData.getValue().getAthlete().getLastName()
        ));
        programNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                Helper.formatProgramDetails(cellData.getValue().getTrainingProgram())
        ));
        monthlyCostColumn.setCellValueFactory(cellData -> new SimpleStringProperty("$" + String.format("%.2f", cellData.getValue().getMonthlyCost())));

        // Setting the subscriptions from the DataStore
        subscriptionList.addAll(DataStore.subscriptions);
        // Set the table data
        subscriptionTable.setItems(subscriptionList);
    }

    // Create Subscription
    @FXML
    private void handleCreateSubscription() {
        // Get the selected athlete, program, and monthly cost
        Athlete selectedAthlete = athleteChoiceBox.getValue();
        TrainingProgram selectedProgram = trainingProgramChoiceBox.getValue();
        String monthlyCostText = monthlyCostField.getText();

        // Validate input
        if (!Validator.validateSubscriptionInput(selectedAthlete, selectedProgram, monthlyCostText)) {
            return;
        }

        // Parse monthly cost
        double monthlyCost = Double.parseDouble(monthlyCostText);

        // Apply discount
        double discount = Validator.calculateDiscount(selectedAthlete.getExperienceLevel(), selectedAthlete.isProfessional());
        double discountedCost = monthlyCost * (1 - discount);

        // Show payment dialog
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment Confirmation");
        paymentAlert.setHeaderText(null);
        paymentAlert.setContentText("The total payment amount is $" + String.format("%.2f", discountedCost) + ". Do you want to proceed?");
        Optional<ButtonType> result = paymentAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Create a new subscription
            Subscription newSubscription = new Subscription(selectedAthlete, selectedProgram, discountedCost);
            DataStore.subscriptions.add(newSubscription);
            subscriptionList.add(newSubscription);

            // Handle payment
            Payment newPayment = new Payment(LocalDate.now(), "Cash", newSubscription);
            DataStore.payments.add(newPayment);
            clearForm();
        }
    }

    // Clear Form
    @FXML
    private void clearForm() {
        athleteChoiceBox.setValue(null);
        trainingProgramChoiceBox.setValue(null);
        monthlyCostField.clear();
    }

    // Cancel Subscription
    @FXML
    private void handleCancelSubscription() {
        // Get the selected subscription
        Subscription selectedSubscription = subscriptionTable.getSelectionModel().getSelectedItem();
        if (selectedSubscription == null) {
            Validator.showAlert("Cancellation Error", "No subscription selected for cancellation.");
            return;
        }

        // Remove subscription from list and data store
        DataStore.subscriptions.remove(selectedSubscription);
        subscriptionList.remove(selectedSubscription);
    }

    // Exit
    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) subscriptionTable.getScene().getWindow();
        stage.close();

        // Load the Main Window
        ViewFactory.getInstance().showMainWindow();
    }
}
