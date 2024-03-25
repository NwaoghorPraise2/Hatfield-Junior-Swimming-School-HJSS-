package controller;

import models.Learner;

import java.time.LocalDate;
import java.util.Date;

public class LearnerController {
    public void registerLearner(String name, LocalDate dateOfBirth, String gender, String emergencyContact, int currentGradeLevel){
        Learner learner = new Learner(name, gender, dateOfBirth, emergencyContact, currentGradeLevel);
        System.out.println(learner.getId() + " " + learner.getName() + " " + learner.getDateOfBirth());
    }
}

