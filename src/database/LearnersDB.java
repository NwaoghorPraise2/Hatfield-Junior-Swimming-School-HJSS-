package database;

import models.Learner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Learner getLearner(String id) {
        for (Learner learner : learners) {
            if (learner.getId().equals(id)) {
                return learner;
            }
        }
        return null;
    }

    public Learner getRandomLearner() {
        if (learners.isEmpty()) {
            return null; // Return null if the database is empty
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(learners.size()); // Generate a random index
            return learners.get(randomIndex); // Return the learner at the random index
        }
    }


}
