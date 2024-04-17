package models;

public class Review {
    private int rating;
    private String review;
    private Lesson lesson;

    public Review(int rating, String review, Lesson lesson) {
        this.rating = rating;
        this.review = review;
        this.lesson = lesson;
    }

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
