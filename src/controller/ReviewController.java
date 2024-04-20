package controller;

import database.ReviewDB;
import models.Lesson;
import models.Review;

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
    public String addReview(String review, int rating, Lesson lesson) {
        Review review1 =  new Review( rating, review, lesson);
        reviewDB.addReview(review1);
        return "Review added successfully";
    }

}
