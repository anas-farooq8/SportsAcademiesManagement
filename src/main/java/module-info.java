module org.example.sportsacademiesmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.logging;

    opens org.example.sportsacademiesmanagement to javafx.fxml;
    exports org.example.sportsacademiesmanagement;

    exports org.example.sportsacademiesmanagement.helper;
    exports org.example.sportsacademiesmanagement.validator;
    exports org.example.sportsacademiesmanagement.models;
    exports org.example.sportsacademiesmanagement.viewfactory;
    exports org.example.sportsacademiesmanagement.controller;
    opens org.example.sportsacademiesmanagement.controller to javafx.fxml;
    exports org.example.sportsacademiesmanagement.data;
    opens org.example.sportsacademiesmanagement.data to javafx.fxml;
}