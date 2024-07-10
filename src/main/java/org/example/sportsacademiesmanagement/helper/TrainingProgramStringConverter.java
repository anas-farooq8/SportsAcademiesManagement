package org.example.sportsacademiesmanagement.helper;

import org.example.sportsacademiesmanagement.models.TrainingProgram;

public class TrainingProgramStringConverter extends javafx.util.StringConverter<TrainingProgram> {
    // This class is used to convert TrainingProgram objects to strings and vice versa
    @Override
    public String toString(TrainingProgram trainingProgram) {
        if (trainingProgram == null) {
            return null;
        }
        return trainingProgram.getSport().getName() + ", " +
                trainingProgram.getFacility().getName() + ", " +
                trainingProgram.getCoach().getFirstName() + " " +
                trainingProgram.getCoach().getLastName() + ", " +
                trainingProgram.getParticipantGender();
    }

    @Override
    public TrainingProgram fromString(String string) {
        return null;
    }
}
