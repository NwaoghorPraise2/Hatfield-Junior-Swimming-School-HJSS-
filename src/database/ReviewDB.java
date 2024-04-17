package database;
import models.Lesson;
import models.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewDB {
    private final List<Review> reviews;
    private static ReviewDB instance;

    public static ReviewDB getInstance() {
        if (instance == null) {
            instance = new ReviewDB();
        }
        return instance;
    }

    public ReviewDB () {
        reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

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

    public List<Review> getReviews() {
        return reviews;
    }

}
