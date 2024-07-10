package org.example.sportsacademiesmanagement.helper;

import org.example.sportsacademiesmanagement.models.Athlete;

public class AthleteStringConverter extends javafx.util.StringConverter<Athlete> {
    // This class is used to convert Athlete objects to strings and vice versa
    @Override
    public String toString(Athlete athlete) {
        if (athlete == null) {
            return null;
        }
        return athlete.getUniqueID() + ": " + athlete.getFirstName() + " " + athlete.getLastName() + ": " + athlete.getGender();
    }

    // This method is not needed
    @Override
    public Athlete fromString(String string) {
        return null; // Conversion from string to Athlete is not needed
    }
}