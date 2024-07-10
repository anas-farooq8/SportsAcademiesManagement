package org.example.sportsacademiesmanagement.viewfactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.sportsacademiesmanagement.App;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewFactory {
    // For Exception Handling
    private static final Logger logger = Logger.getLogger(ViewFactory.class.getName());

    // Singleton instance
    private static ViewFactory viewFactory;

    private ViewFactory() {
        // private constructor to prevent instantiation
    }

    // Get instance method
    public static synchronized ViewFactory getInstance() {
        if (viewFactory == null) {
            viewFactory = new ViewFactory();
        }
        return viewFactory;
    }

    // Creates the Stage
    private void createStage(FXMLLoader loader) {
        Scene scene = null;

        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            // Log the exception instead of printing the stack trace
            logger.log(Level.SEVERE, "An error occurred while creating the stage", e);
        }

        Stage stage = new Stage();
        if (scene != null) {
            stage.setScene(scene);
        } else {
            // Log a warning if the scene is null
            logger.warning("Scene is null. Stage creation failed.");
            return;
        }

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Management Athletic Academies");
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }


    // Methods to show different windows
    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main-window.fxml"));
        createStage(loader);
    }

    // Display the athlete management window
    public void showAthleteManagementWindow() {
        printMessage("Athlete Management Window");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("athlete-management.fxml"));
        createStage(loader);
    }

    // Display the coach management window
    public void showSubscriptionManagementWindow() {
        printMessage("Subscription Management Window");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("subscription-management.fxml"));
        createStage(loader);
    }

    // Display the facility management window
    public void showReservationManagementWindow() {
        // Implement reservation management window loading
        printMessage("Reservation Management Window");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("reservation-management.fxml"));
        createStage(loader);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
