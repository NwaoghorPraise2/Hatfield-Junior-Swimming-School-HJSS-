package controller;

import database.LearnersDB;
import models.Learner;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller class responsible for managing learners.
 */
public class LearnerController {
    private LearnersDB learnersDB = LearnersDB.getInstance();

    /**
     * Registers a new learner.
     * @param name The name of the learner.
     * @param dateOfBirth The date of birth of the learner.
     * @param gender The gender of the learner.
     * @param emergencyContact The emergency contact of the learner.
     * @param currentGradeLevel The current grade level of the learner.
     * @return A message indicating the success of the registration.
     */
    public String registerLearner(String name, LocalDate dateOfBirth, String gender, String emergencyContact, int currentGradeLevel) {
        Learner learner = new Learner(name, gender, dateOfBirth, emergencyContact, currentGradeLevel);
        learnersDB.addLearner(learner);
        return "Learner registered successfully! " + "Take your Username: " + learner.getId();
    }

    /**
     * Retrieves all learners stored in the database.
     * @return A list of all learners.
     */
    public List<Learner> getAllLearners() {
        return learnersDB.getLearners();
    }

    /**
     * Retrieves a learner by their ID.
     * @param id The ID of the learner.
     * @return The learner with the specified ID.
     */
    public Learner getLearnerById(String id) {
        return learnersDB.getLearner(id);
    }

    /**
     * Retrieves a random learner ID from the database.
     * @return A random learner ID.
     */
    public String getRandomLearnerID() {
        Learner learner = learnersDB.getRandomLearner();
        return learner.getId();
    }

    /**
     * Updates learner data including booked, attended, and cancelled lessons.
     * @param learnerId The ID of the learner to update.
     * @param bookedLessons The list of booked lessons.
     * @param attendedLesson The list of attended lessons.
     * @param cancelledLesson The list of cancelled lessons.
     * @return A message indicating the success of the update.
     */
    public String updateLearnerData(String learnerId, List<String> bookedLessons, List<String> attendedLesson, List<String> cancelledLesson){
        Learner learner = learnersDB.getLearner(learnerId);
        if (learner == null) {
            throw new IllegalArgumentException("Learner not found");
        }
        learner.setBookedLessons(bookedLessons);
        learner.setAttendedLessons(attendedLesson);
        learner.setCancelledLessons(cancelledLesson);

        return "Learner updated successfully!";
    }
}
