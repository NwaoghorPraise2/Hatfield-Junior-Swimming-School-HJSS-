package view;

import models.Review;

import java.util.List;

import controller.ReviewController;

public class ReviewView {

    private ReviewController reviewController = new ReviewController();

    public ReviewView() {
    }
    public void displayReviews() {
        List<Review> allReviews = reviewController.getReviews();
        for (Review review : allReviews) {
            System.out.println(review.toString());
        }
    }
}
