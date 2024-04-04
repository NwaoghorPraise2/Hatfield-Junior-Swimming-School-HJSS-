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

    public String updateBooking(String bookingId, String lessonRef) {
        try {
            Booking booking = bookingDB.getBookingByBookingId(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking not found");
            }

            if (!booking.getStatus().equals("Booked")) {
                throw new IllegalStateException("Booking status is:" + " " + booking.getStatus() +" "+ "and cannot be changed");
            }

            Lesson lesson = booking.getLesson();

            Lesson oldLesson = lessonController.getLessonByRef(lesson.getLessonRef());
            if (oldLesson == null) {
                throw new IllegalArgumentException("Lesson not found");
            }



            if (!lesson.isAvailable()) {
                throw new IllegalStateException("Lesson is fully booked");
            }

            booking.setLesson(lesson);

            return "Booking updated successfully";
        } catch (IllegalArgumentException | IllegalStateException e) {
            return e.getMessage();
        }
    }

}













