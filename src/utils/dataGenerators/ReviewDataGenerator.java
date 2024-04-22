package utils.dataGenerators;

import controller.BookingController;
import controller.LessonController;
import controller.ReviewController;
import models.Booking;
import models.Lesson;

import java.util.List;
import java.util.Random;

/**
 * The ReviewDataGenerator class is responsible for generating random reviews and ratings
 * for attended bookings, associating them with the corresponding lessons.
 */
public class ReviewDataGenerator {

    private final ReviewController reviewController;
    private final LessonController lessonController;
    private final BookingController bookingController;
    private static final String[] compliments = {
            // Array of predefined compliments
    };
    private final Random random;

    /**
     * Constructs a ReviewDataGenerator object initializing controllers and random number generator.
     */
    public ReviewDataGenerator() {
        this.reviewController = new ReviewController();
        this.lessonController = new LessonController();
        this.random = new Random();
        this.bookingController = new BookingController();
    }

    /**
     * Retrieves a random compliment from the predefined array of compliments.
     *
     * @return A random compliment.
     */
    public String getRandomCompliment() {
        int index = random.nextInt(compliments.length);
        return compliments[index];
    }

    /**
     * Generates a random rating between 1 and 5.
     *
     * @return A random rating.
     */
    public int getRandomRating() {
        return random.nextInt(5) + 1;
    }

    /**
     * Generates reviews for all attended bookings by associating random compliments
     * and ratings with their corresponding lessons.
     */
    public void generateReviewsForAttendedBookings() {
        // Get all bookings where status is Attended
        List<Booking> attendedBookings = bookingController.getBookingsByStatus("Attended");

        // Iterate through each attended booking
        for (Booking booking : attendedBookings) {
            // Generate a random review and rating for each booking
            String randomReview = getRandomCompliment();
            int randomRating = getRandomRating();

            // Get the lesson associated with the booking
            Lesson lesson = booking.getLesson();
            String learnerId = booking.getLearnerId();

            // Add the review for the lesson
            addReview(randomReview, randomRating, lesson, learnerId);
        }
    }

    /**
     * Adds a review for a lesson with the specified rating and content.
     *
     * @param review    The content of the review.
     * @param rating    The rating assigned to the lesson.
     * @param lesson    The lesson for which the review is being added.
     * @param learnerId The ID of the learner submitting the review.
     */
    public void addReview(String review, int rating, Lesson lesson, String learnerId) {
        if (lesson != null)
            reviewController.addReview(review, rating, lesson, learnerId);
    }
}
