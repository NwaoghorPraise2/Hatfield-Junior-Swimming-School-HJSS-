package controller;

import models.Learner;


import java.time.LocalDate;

public class LearnerController {
    public String registerLearner(String name, LocalDate dateOfBirth, String gender, String emergencyContact, int currentGradeLevel){
        Learner learner = new Learner(name, gender, dateOfBirth, emergencyContact, currentGradeLevel);
        return "Learner registered successfully!" + learner.getId() + " " + learner.getName() + " " + learner.getDateOfBirth();
    }
}

