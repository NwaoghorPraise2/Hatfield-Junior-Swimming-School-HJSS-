package database;

import models.Learner;

import java.util.ArrayList;
import java.util.List;

public class LearnersDB {

    private final List<Learner> learners;
    private static LearnersDB instance;

    public static LearnersDB getInstance() {
        if (instance == null) {
            instance = new LearnersDB();
        }
        return instance;
    }

    public LearnersDB() {
        learners = new ArrayList<>();
    }

    public void addLearner(Learner learner) {
        learners.add(learner);
    }

    public List<Learner> getLearners() {
        return learners;
    }
}
