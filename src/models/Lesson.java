package models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Lesson {
    private String lessonRef;
    private int gradeLevel;
    private DayOfWeek day;
    private LocalTime timeSlot;
    private int capacity;
    private Map<Integer, Learner> bookings;


    public Lesson(int gradeLevel, DayOfWeek day, LocalTime timeSlot) {
        this.lessonRef  = "L" + gradeLevel + day + timeSlot; //refactor this.
        this.gradeLevel = gradeLevel;
        this.day = day;
        this.timeSlot = timeSlot;
        this.capacity = 4;
        this.bookings = new HashMap<>(capacity);
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<Integer, Learner> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Integer, Learner> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "gradeLevel=" + gradeLevel +
                ", day=" + day +
                ", timeSlot=" + timeSlot +
                ", capacity=" + capacity +
                ", bookings=" + bookings +
                '}';
    }
}
