package database;

import models.Learner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Database class responsible for managing learners.
 */
public class LearnersDB {

    private final List<Learner> learners;
    private static LearnersDB instance;

    /**
     * Retrieves the singleton instance of LearnersDB.
     * @return The singleton instance of LearnersDB.
     */
    public static LearnersDB getInstance() {
        if (instance == null) {
            instance = new LearnersDB();
        }
        return instance;
    }

    /**
     * Constructs a LearnersDB instance.
     */
    public LearnersDB() {
        learners = new ArrayList<>();
    }

    /**
     * Adds a learner to the database.
     * @param learner The learner to be added.
     */
    public void addLearner(Learner learner) {
        learners.add(learner);
    }

    /**
     * Retrieves all learners stored in the database.
     * @return A list of all learners.
     */
    public List<Learner> getLearners() {
        return learners;
    }

    /**
     * Retrieves a learner by their ID.
     * @param id The ID of the learner.
     * @return The learner with the specified ID, or null if not found.
     */
    public Learner getLearner(String id) {
        for (Learner learner : learners) {
            if (learner.getId().equals(id)) {
                return learner;
            }
        }
        return null;
    }

    /**
     * Retrieves a random learner from the database.
     * @return A randomly selected learner.
     */
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
