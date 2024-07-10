package org.example.sportsacademiesmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.sportsacademiesmanagement.viewfactory.ViewFactory;

// Controller class for the Main Window
public class MainWindowController {

    // Handle the buttons' actions
    // Each button will close the current stage and open the corresponding window

    // The buttons are: Athlete Management, Subscription Management, Reservation Management, Exit
    @FXML
    private void handleAthleteManagement(ActionEvent event) {
        // Close the previous stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load Athlete Management Window
        ViewFactory.getInstance().showAthleteManagementWindow();
    }

    @FXML
    private void handleSubscriptionManagement(ActionEvent event) {
        // Close the previous stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load Subscription Management Window
        ViewFactory.getInstance().showSubscriptionManagementWindow();
    }

    @FXML
    private void handleReservationManagement(ActionEvent event) {
        // Close the previous stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load Reservation Management Window
        ViewFactory.getInstance().showReservationManagementWindow();
    }

    // Exit the application
    @FXML
    private void handleExit(ActionEvent event) {
        // Get the current stage and close it
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);
    }
}