package org.example.sportsacademiesmanagement;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.sportsacademiesmanagement.viewfactory.ViewFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.sportsacademiesmanagement.helper.Helper.initializeData;

public class App extends Application {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void start(Stage stage) {
        initializeData();
        try {
            // Run the Main Window
            ViewFactory.getInstance().showMainWindow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred during application start", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
