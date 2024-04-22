package utils.reportHandlers;

import controller.ReviewController;
import models.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the generation of coach reports.
 */
public class CoachReportHandler {
    private final ReviewController reviewController;
    private final String[] COACHES = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Christopher", "Jennifer"};
    private int currentIndex;

    /**
     * Constructs a new CoachReportHandler object.
     */
    public CoachReportHandler() {
        this.reviewController = new ReviewController();
        this.currentIndex = 0;
    }

    /**
     * Generates a coach report for the specified month.
     * @param month The month for which the report is generated (1-12).
     */
    public void generateCoachReport(int month) {
        LocalDate startDate = calculateStartDate(month);
        LocalDate endDate = calculateEndDate(startDate);
        Map<String, Double> coachRatings = new HashMap<>();

        // Iterate over all coaches
        for (String coachName : COACHES) {
            List<Review> reviews = getReviewsForCoachInMonth(coachName, startDate, endDate);
            double averageRating = calculateAverageRating(reviews);
            // Round up the average rating to the nearest whole number
            averageRating = Math.ceil(averageRating);
            coachRatings.put(coachName, averageRating);
        }

        displayCoachReport(coachRatings);
    }

    private LocalDate calculateStartDate(int month) {
        int currentYear = LocalDate.now().getYear();
        return LocalDate.of(currentYear, month, 1);
    }

    private LocalDate calculateEndDate(LocalDate startDate) {
        return startDate.plusMonths(1).minusDays(1);
    }

    private List<Review> getReviewsForCoachInMonth(String coachName, LocalDate startDate, LocalDate endDate) {
        List<Review> coachReviews = reviewController.getReviewsByCoachName(coachName);
        List<Review> filteredReviews = new ArrayList<>();

        // Filter reviews based on the lesson's date being within the start date
        for (Review review : coachReviews) {
            LocalDate lessonDate = review.getLesson().getDate();
            if (!lessonDate.isBefore(startDate) && !lessonDate.isAfter(endDate)) {
                filteredReviews.add(review);
            }
        }

        return filteredReviews;
    }

    private double calculateAverageRating(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return 0.0; // Return 0.0 if no reviews available
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }

    private void displayCoachReport(Map<String, Double> coachRatings) {
        System.out.println("Coach Ratings Report:");
        System.out.println("----------------------");

        for (Map.Entry<String, Double> entry : coachRatings.entrySet()) {
            String coachName = entry.getKey();
            double averageRating = entry.getValue();

            System.out.println("Coach Name: " + coachName);
            System.out.println("Average Rating: " + (int)averageRating); // Convert to int to display whole number
            System.out.println();
        }
    }
}
