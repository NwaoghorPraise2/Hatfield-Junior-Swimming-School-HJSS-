package controller;

import database.ReviewDB;
import models.Lesson;
import models.Review;

public class ReviewController {
    private static ReviewDB reviewDB;

    public ReviewController() {
        reviewDB = ReviewDB.getInstance();
    }

    public String addReview(String review, int rating, Lesson lesson) {
        Review review1 =  new Review( rating, review, lesson);
        reviewDB.addReview(review1);
        return "Review added successfully";
    }

}
