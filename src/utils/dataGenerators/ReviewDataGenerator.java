package utils.dataGenerators;

import controller.BookingController;
import controller.LessonController;
import controller.ReviewController;
import models.Booking;
import models.Lesson;

import java.util.List;
import java.util.Random;

public class ReviewDataGenerator {
    private final ReviewController reviewController;
    private final LessonController lessonController;
    private final BookingController bookingController;
    private static final String[] compliments = {
            "Amazing instructor!",
            "Fantastic facilities!",
            "Great atmosphere!",
            "Highly recommend to everyone!",
            "Instructor was very patient and supportive!",
            "Best swimming lesson ever!",
            "I learned so much!",
            "The staff is so friendly and helpful!",
            "Very clean pool!",
            "I feel much more confident in the water now!",
            "Wonderful experience!",
            "I can't wait for my next lesson!",
            "My children absolutely loved it!",
            "The lessons were well-organized and structured!",
            "I never thought I could swim, but now I can!",
            "Excellent teaching methods!",
            "The instructor tailored the lesson to my needs!",
            "Great value for the price!",
            "The facility is top-notch!",
            "The lessons were fun and enjoyable!",
            "I'm so glad I signed up for these lessons!",
            "I feel safer in the water now!",
            "The instructor was knowledgeable and skilled!",
            "I made progress quickly!",
            "The lesson schedule was flexible and accommodating!",
            "My friends were right, this place is amazing!",
            "I feel healthier and more active since starting lessons!",
            "My fear of water is gone thanks to these lessons!",
            "The lessons helped me improve my technique!",
            "I had a blast during every lesson!"
    };
    private final Random random;

    public ReviewDataGenerator() {
        this.reviewController = new ReviewController();
        this.lessonController = new LessonController();
        this.random = new Random();
        this.bookingController = new BookingController();
    }


    public String getRandomCompliment() {
        int index = random.nextInt(compliments.length);
        return compliments[index];
    }


    public int getRandomRating() {
        return random.nextInt(5) + 1;
    }

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

    public void addReview(String review, int rating, Lesson lesson, String learnerId) {
        if (lesson != null)
            reviewController.addReview(review, rating, lesson, learnerId);
}
}
