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

    private static String generateBookingID() {
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

    public Lesson getLessons() {
        return lesson;
    }

    public void setLessons(Lesson lesson) {
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

}
