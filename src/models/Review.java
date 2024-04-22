package models;

/**
 * Represents a review of a lesson.
 */
public class Review {
    private int rating;
    private String review;
    private Lesson lesson;
    private String learnerId;

    /**
     * Constructs a Review object with the given attributes.
     * @param rating The rating of the review.
     * @param review The review content.
     * @param lesson The lesson associated with the review.
     */
    public Review(int rating, String review, Lesson lesson,String learnerId) {
        this.rating = rating;
        this.review = review;
        this.lesson = lesson;
        this.learnerId = learnerId;
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

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
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

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", review='" + review + '\'' +
                ", lesson=" + lesson +
                ", learnerId='" + learnerId + '\'' +
                '}';
    }
}
