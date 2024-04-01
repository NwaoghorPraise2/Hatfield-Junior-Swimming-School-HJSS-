package controller;

import database.LearnersDB;
import models.Learner;
import java.time.LocalDate;
import java.util.List;

public class LearnerController {
    private static final LearnerController INSTANCE = new LearnerController();
    private LearnersDB learnersDB;

    // Private constructor to prevent external instantiation
    private LearnerController() {
        learnersDB = LearnersDB.getInstance();
    }

    // Access point to get the singleton instance
    public static LearnerController getInstance() {
        return INSTANCE;
    }

    public String registerLearner(String name, LocalDate dateOfBirth, String gender, String emergencyContact, int currentGradeLevel) {
        Learner learner = new Learner(name, gender, dateOfBirth, emergencyContact, currentGradeLevel);
        learnersDB.addLearner(learner);
        return "Learner registered successfully!";
    }

    public List<Learner> getAllLearners() {
        return learnersDB.getLearners();
    }
}
