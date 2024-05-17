package controller;

import database.ReviewDB;
import models.Lesson;
import models.Review;

import java.util.List;

/**
 * Controller class responsible for managing reviews.
 */
public class ReviewController {
    private static ReviewDB reviewDB;

    public ReviewController() {
        reviewDB = ReviewDB.getInstance();
    }

    /**
     * Adds a review for a lesson.
     * @param review The review content.
     * @param rating The rating given to the lesson.
     * @param lesson The lesson to which the review is added.
     * @return A message indicating the success of adding the review.
     */
    public String addReview(String review, int rating, Lesson lesson, String learnerId) {
        Review review1 =  new Review( rating, review, lesson, learnerId);
        reviewDB.addReview(review1);
        return "Review added successfully";
    }

    public List<Review> getReviewsByCoachName(String coach) {
        return reviewDB.findReviewsByCoachName(coach);
    }

    public List<Review> getReviews() {
        return reviewDB.getReviews();
    }
}
