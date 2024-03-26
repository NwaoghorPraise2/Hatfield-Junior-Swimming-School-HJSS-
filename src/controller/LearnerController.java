package controller;

import database.LearnersDB;
import models.Learner;
import java.time.LocalDate;
import java.util.List;

public class LearnerController {
    LearnersDB learnersDB = LearnersDB.getInstance();
    public String registerLearner(String name, LocalDate dateOfBirth, String gender, String emergencyContact, int currentGradeLevel){
        Learner learner = new Learner(name, gender, dateOfBirth, emergencyContact, currentGradeLevel);
        learnersDB.addLearner(learner);
        return "Learner registered successfully!";
    }

    public List<Learner> getAllLearners() {
        return learnersDB.getLearners();
    }
}

