package controller;

import database.BookingDB;
import models.Booking;
import models.Learner;
import models.Lesson;

public class BookingController {
    private final BookingDB bookingDB;
    private final LessonController lessonController;
    private final LearnerController learnerController;

    public BookingController() {
        this.bookingDB = BookingDB.getInstance();
        this.lessonController = new LessonController();
        this.learnerController = LearnerController.getInstance();
    }

    /**
     * Creates a booking for a learner with the specified ID for the lesson referenced by lessonRef.
     *
     * @param learnerId The ID of the learner for whom the booking is to be created.
     * @param lessonRef The reference of the lesson for which the booking is to be created.
     * @return A message indicating the result of the booking attempt.
     */
    public String createBooking(String learnerId, String lessonRef) {
        try {
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

            if (learner.getBookedLessons().contains(lesson.getLessonRef())) {
                throw new IllegalStateException("Learner has already booked this lesson");
            }

            if (lesson.getGradeLevel() > learner.getCurrentGradeLevel() + 1 || lesson.getGradeLevel() < learner.getCurrentGradeLevel()) {
                throw new IllegalStateException("Learner is not eligible to book this lesson");
            }

            lesson.addBookedLearner(learner.getId());

            Booking booking = new Booking(learnerId, lesson);
            bookingDB.addBooking(booking);

            learner.getBookedLessons().add(booking.getBookingId());

            return "Booking Successful!!! Take your booking code: " + booking.getBookingId();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return e.getMessage();
        }
    }

    /**
     * Updates the booking with the specified booking ID to use the lesson referenced by lessonRef.
     *
     * @param bookingId The ID of the booking to update.
     * @param lessonRef The reference to the new lesson.
     * @return A string indicating the result of the update operation.
     *         If successful, returns "Booking updated successfully".
     *         If an error occurs, returns an error message describing the issue.
     * @throws IllegalArgumentException if bookingId or lessonRef is null, or if booking or learner associated with the booking is not found.
     * @throws IllegalStateException    if the booking status is not "Booked", or if the new lesson is fully booked.
     */
    public String updateBooking(String bookingId, String lessonRef) {
        try {
            // Validate inputs
            if (bookingId == null || lessonRef == null) {
                throw new IllegalArgumentException("Invalid input: bookingId or lessonRef is null");
            }

            // Retrieve booking
            Booking booking = bookingDB.getBookingByBookingId(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking not found");
            }

            // Check booking status
            if (!booking.getStatus().equals("Booked")) {
                throw new IllegalStateException("Booking status is: " + booking.getStatus() + " and cannot be changed");
            }

            // Retrieve old lesson
            Lesson oldLesson = booking.getLesson();
            if (oldLesson == null) {
                throw new IllegalArgumentException("Lesson in booking not found");
            }

            // Remove booking from old lesson
            oldLesson.removeBookedLearner(booking.getLearnerId());

            // Retrieve learner
            Learner learner = learnerController.getLearnerById(booking.getLearnerId());
            if (learner == null) {
                throw new IllegalArgumentException("Learner not found");
            }

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
            booking.setStatus("Booked");

            return "Booking updated successfully";
        } catch (IllegalArgumentException | IllegalStateException e) {
            return e.getMessage();
        }
    }


    /**
     * Cancels a booking by updating its status to "Cancelled" and performing associated actions.
     *
     * @param bookingId The ID of the booking to be cancelled.
     * @return A string indicating the result of the cancellation operation.
     *         If the cancellation is successful, returns "Booking cancelled successfully".
     *         If there is an error during cancellation, returns an error message.
     */
    public String cancelBooking(String bookingId) {
        try {
            // Validate input
            if (bookingId == null || bookingId.isEmpty()) {
                throw new IllegalArgumentException("Booking ID cannot be null or empty");
            }

            // Fetch booking, lesson, and learner in one go
            Booking booking = bookingDB.getBookingByBookingId(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking not found");
            }

            // Remove booked learner from the lesson
            Lesson lesson = booking.getLesson();
            if (lesson == null) {
                throw new IllegalArgumentException("Lesson in booking not found");
            }
            lesson.removeBookedLearner(booking.getLearnerId());

            // Remove booking from learner's booked lessons and add to cancelled lessons
            Learner learner =  learnerController.getLearnerById(booking.getLearnerId());
            if (learner == null) {
                throw new IllegalArgumentException("Learner not found");
            }
            learner.getBookedLessons().remove(bookingId);
            learner.getCancelledLessons().add(bookingId);

            // Update booking status
            booking.setStatus("Cancelled");

            return "Booking cancelled successfully";
        } catch (IllegalArgumentException e) {
            return "Failed to cancel booking: " + e.getMessage();
        } catch (Exception e) {
            return "Failed to cancel booking due to unexpected error";
        }
    }

    public String attendLesson(String bookingId){
        return null;
    }
}













