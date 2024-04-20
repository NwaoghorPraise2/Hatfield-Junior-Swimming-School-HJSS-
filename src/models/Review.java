package models;

/**
 * Represents a review of a lesson.
 */
public class Review {
    private int rating;
    private String review;
    private Lesson lesson;

    /**
     * Constructs a Review object with the given attributes.
     * @param rating The rating of the review.
     * @param review The review content.
     * @param lesson The lesson associated with the review.
     */
    public Review(int rating, String review, Lesson lesson) {
        this.rating = rating;
        this.review = review;
        this.lesson = lesson;
    }

    // Getters and Setters

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
