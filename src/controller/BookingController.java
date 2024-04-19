package controller;

import database.BookingDB;
import models.Booking;
import models.Learner;
import models.Lesson;

public class BookingController {
    private final BookingDB bookingDB;
    private final LessonController lessonController;
    private final LearnerController learnerController;

    // Constants for booking statuses
    private static final String BOOKED_STATUS = "Booked";
    private static final String CANCELLED_STATUS = "Cancelled";
    private static final String ATTENDED_STATUS = "Attended";

    /**
     * Constructs a BookingController instance.
     *
     */
    public BookingController() {
        this.bookingDB = BookingDB.getInstance();
        this.lessonController = new LessonController();
        this.learnerController = LearnerController.getInstance();
    }

    /**
     * Creates a booking for a learner.
     *
     * @param learnerId The ID of the learner.
     * @param lessonRef The reference of the lesson.
     * @return A message indicating the result of the booking attempt.
     * @throws IllegalArgumentException if learnerId or lessonRef is null, or if the learner or lesson is not found.
     * @throws IllegalStateException if the lesson is fully booked, learner has already booked this lesson, or learner is not eligible to book this lesson.
     */
    public String createBooking(String learnerId, String lessonRef) {
        try {
            // Validate inputs
            if (learnerId == null || lessonRef == null) {
                throw new IllegalArgumentException("Invalid input: learnerId or lessonRef is null");
            }

            Lesson lesson = lessonController.getLessonByRef(lessonRef);
            if (lesson == null) {
                throw new IllegalArgumentException("Lesson not found");
            }
            if (!lesson.isAvailable()) {
                throw new IllegalStateException("Lesson is fully booked");
            }

            Learner learner = learnerController.getLearnerById(learnerId);
            if (learner == null) {
                throw new IllegalArgumentException("Learner not found");
            }
            if (learner.getBookedLessons().contains(lessonRef)) {
                throw new IllegalStateException("Learner has already booked this lesson");
            }
            if (lesson.getGradeLevel() > learner.getCurrentGradeLevel() + 1 || lesson.getGradeLevel() < learner.getCurrentGradeLevel()) {
                throw new IllegalStateException("Learner is not eligible to book this lesson");
            }

            lesson.addBookedLearner(learnerId);

            Booking booking = new Booking(learnerId, lesson);
            bookingDB.addBooking(booking);

            learner.getBookedLessons().add(booking.getBookingId());

            return "Booking Successful!!! Take your booking code: " + booking.getBookingId();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return handleException(e);
        }
    }

    /**
     * Updates the booking with the specified booking ID.
     *
     * @param bookingId The ID of the booking to update.
     * @param lessonRef The reference to the new lesson.
     * @return A string indicating the result of the update operation.
     * @throws IllegalArgumentException if bookingId or lessonRef is null, or if the booking, learner associated with the booking, or lesson is not found.
     * @throws IllegalStateException if the booking status is not "Booked", or if the new lesson is fully booked.
     */
    public String updateBooking(String bookingId, String lessonRef) {
        try {
            // Validate inputs
            if (bookingId == null || lessonRef == null) {
                throw new IllegalArgumentException("Invalid input: bookingId or lessonRef is null");
            }

            // Retrieve booking
            Booking booking = getBookingById(bookingId);

            // Check booking status
            if (!booking.getStatus().equals(BOOKED_STATUS)) {
                throw new IllegalStateException("Booking status is: " + booking.getStatus() + " and cannot be changed");
            }

            // Retrieve old lesson
            Lesson oldLesson = booking.getLesson();

            // Remove booking from old lesson
            oldLesson.removeBookedLearner(booking.getLearnerId());

            // Retrieve learner
            Learner learner = getLearnerById(booking.getLearnerId());

            // Remove booking from learner's booked lessons
            learner.getBookedLessons().remove(bookingId);

            // Retrieve new lesson
            Lesson lesson = lessonController.getLessonByRef(lessonRef);
            if (!lesson.isAvailable()) {
                throw new IllegalStateException("Lesson is fully booked");
            }

            // Set new lesson for booking
            booking.setLesson(lesson);
            lesson.addBookedLearner(booking.getLearnerId());
            booking.setStatus(BOOKED_STATUS);

            return "Booking updated successfully";
        } catch (IllegalArgumentException | IllegalStateException e) {
            return handleException(e);
        }
    }

    /**
     * Cancels a booking.
     *
     * @param bookingId The ID of the booking to be cancelled.
     * @return A string indicating the result of the cancellation operation.
     * @throws IllegalArgumentException if bookingId is null or empty, or if the booking, learner associated with the booking, or lesson is not found.
     */
    public String cancelBooking(String bookingId) {
        try {
            // Validate input
            if (bookingId == null || bookingId.isEmpty()) {
                throw new IllegalArgumentException("Booking ID cannot be null or empty");
            }

            // Fetch booking
            Booking booking = getBookingById(bookingId);

            // Remove booked learner from the lesson
            Lesson lesson = booking.getLesson();
            lesson.removeBookedLearner(booking.getLearnerId());

            // Remove booking from learner's booked lessons and add to cancelled lessons
            Learner learner = getLearnerById(booking.getLearnerId());
            learner.getBookedLessons().remove(bookingId);
            learner.getCancelledLessons().add(bookingId);

            // Update booking status
            booking.setStatus(CANCELLED_STATUS);

            return "Booking cancelled successfully";
        } catch (IllegalArgumentException e) {
            return handleException(e);
        }
    }

    /**
     * Marks a lesson as attended.
     *
     * @param bookingId The ID of the booking for the lesson.
     * @return A message indicating the success of attending the lesson.
     * @throws IllegalArgumentException If the booking ID is invalid, or if the booking or learner associated with the booking is not found.
     */
    public String attendLesson(String bookingId) {
        try {
            // Validate input
            if (bookingId == null || bookingId.isEmpty()) {
                throw new IllegalArgumentException("Invalid booking ID: " + bookingId);
            }

            //user regular expression to ensure shape of input.

            // Retrieve booking
            Booking booking = getBookingById(bookingId);

            // Retrieve learner
            Learner learner = getLearnerById(booking.getLearnerId());

//            Remember to increase user grade level after attebding take note//

            // Mark lesson as attended
            learner.getAttendedLessons().remove(bookingId);
            learner.getAttendedLessons().add(bookingId);

            // Update booking status
            booking.setStatus(ATTENDED_STATUS);

            return "Lesson attended successfully";
        } catch (IllegalArgumentException e) {
            return handleException(e);
        }
    }

    // Helper method to handle exceptions
    private String handleException(Exception e) {
        return "Failed: " + e.getMessage();
    }

    // Helper method to retrieve booking by ID
    public Booking getBookingById(String bookingId) {
        Booking booking = bookingDB.getBookingByBookingId(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        return booking;
    }

    // Helper method to retrieve learner by ID
    private Learner getLearnerById(String learnerId) {
        Learner learner = learnerController.getLearnerById(learnerId);
        if (learner == null) {
            throw new IllegalArgumentException("Learner not found");
        }
        return learner;
    }
}
