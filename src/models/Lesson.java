package models;

import utils.AppManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private LocalDate date;
    private DayOfWeek dayOfTheWeek;
    private String coach;
    private int gradeLevel;
    private String lessonRef;
    private int capacity;
    private List<Integer> bookings;
    private String status;
    private LocalTime startTime;
    private String timeSlot;

    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, int capacity) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        this.generateLessonRef();
        this.capacity = capacity;
        this.bookings = new ArrayList<>(capacity);
        this.status = "Available";
        this.startTime = AppManager.getInstance().setStartTime(dayOfTheWeek);
        this.timeSlot = createTimeSlot(startTime);
    }

    public Lesson(){

    }

    private void generateLessonRef() {
        this.lessonRef = String.format("%s-%02d-%02d-%d", dayOfTheWeek.toString().toLowerCase(), date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    private String createTimeSlot(LocalTime startTime) {
        LocalTime endTime = startTime.plusHours(1);
        return startTime + " - " + endTime;
    }



    private void updateStatus() {
        if (bookings.size() == this.capacity) {
            this.status = "Fully Booked";
        }
    }

    private String timeSlot(LocalTime startTime) {
        LocalTime endTime = startTime.plusHours(1);
        return startTime + " - " + endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DayOfWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getLessonRef() {
        return lessonRef;
    }

    public void setLessonRef(String lessonRef) {
        this.lessonRef = lessonRef;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Integer> getBookings() {
        return bookings;
    }

    public void setBookings(List<Integer> bookings) {
        this.bookings = bookings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }



    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-10s | %-12d | %-20s | %-8d | %-10s | %-15s |",
                date, dayOfTheWeek, coach, gradeLevel, lessonRef, capacity, status, timeSlot);
    }

}