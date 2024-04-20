package models;

import java.util.List;

public class Booking {
    private String bookingId;
    private Lesson lesson;
    private String learnerId;
    private String status;
    private static int counter = 1;

    public Booking(String  learnerId, Lesson lesson) {
        this.bookingId = generateBookingID();
        this.lesson = lesson;
        this.learnerId = learnerId;
        this.status = "Booked";
    }

    //for data generation purpose only
    public Booking() {
    }


    public static String generateBookingID() {
        String prefix = "SWLES-T";
        String uniqueID = String.format("%s%03d", prefix, counter);
        counter++;
        return uniqueID;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", lesson=" + lesson +
                ", learnerId='" + learnerId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
