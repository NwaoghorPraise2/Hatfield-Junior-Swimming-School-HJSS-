package controller;

import models.Learner;

import java.time.LocalDate;
import java.util.Date;

public class LearnerController {
    public void registerLearner(String id, String name, LocalDate dateOfBirth, String emergencyContact, int currentGradeLevel){
        Learner learner = new Learner(id, name, dateOfBirth, emergencyContact, currentGradeLevel);
        System.out.println(learner.getName());
    }
}

