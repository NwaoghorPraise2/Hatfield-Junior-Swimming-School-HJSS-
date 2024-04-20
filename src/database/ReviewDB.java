package database;

import models.Review;
import models.Lesson;
import java.util.ArrayList;
import java.util.List;

/**
 * Database class responsible for managing reviews.
 */
public class ReviewDB {
    private final List<Review> reviews;
    private static ReviewDB instance;

    /**
     * Retrieves the singleton instance of ReviewDB.
     * @return The singleton instance of ReviewDB.
     */
    public static ReviewDB getInstance() {
        if (instance == null) {
            instance = new ReviewDB();
        }
        return instance;
    }

    /**
     * Constructs a ReviewDB instance.
     */
    public ReviewDB() {
        reviews = new ArrayList<>();
    }

    /**
     * Adds a review to the database.
     * @param review The review to be added.
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Finds reviews by coach name.
     * @param coachName The name of the coach.
     * @return A list of reviews associated with the coach.
     */
    public List<Review> findReviewsByCoachName(String coachName) {
        List<Review> reviewsByCoach = new ArrayList<>();
        for (Review review : reviews) {
            Lesson lesson = review.getLesson();
            if (lesson != null && lesson.getCoach().equals(coachName)) {
                reviewsByCoach.add(review);
            }
        }
        return reviewsByCoach;
    }

    /**
     * Retrieves all reviews stored in the database.
     * @return A list of all reviews.
     */
    public List<Review> getReviews() {
        return reviews;
    }
}
