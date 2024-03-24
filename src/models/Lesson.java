package models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

public class Lesson {
    private int gradeLevel;
    private DayOfWeek day;
    private LocalTime timeSlot;
    private int capacity;
    private Map<Integer, Learner> bookings;

    public Lesson(int gradeLevel, DayOfWeek day, LocalTime timeSlot, int capacity, Map<Integer, Learner> bookings) {
        this.gradeLevel = gradeLevel;
        this.day = day;
        this.timeSlot = timeSlot;
        this.capacity = capacity;
        this.bookings = bookings;
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
}
