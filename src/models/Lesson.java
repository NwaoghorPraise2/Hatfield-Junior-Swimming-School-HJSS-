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
    private List<String> bookings;
    private String status;
    private LocalTime startTime;
    private String timeSlot;
    private static int lessonCounter = 1;

    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, int capacity) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        generateLessonRef();
        this.capacity = capacity;
        this.bookings = new ArrayList<>(capacity);
        this.status = "Available";
        this.startTime = AppManager.getInstance().setStartTime(dayOfTheWeek);
        this.timeSlot = createTimeSlot(startTime);
    }

    public Lesson(){

    }

    private void generateLessonRef() {
        this.lessonRef = String.format("%s-%02d-%02d-%d-SLT%d", dayOfTheWeek.toString().toLowerCase().substring(0, 3), date.getDayOfMonth(), date.getMonthValue(), date.getYear(), lessonCounter++);
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

    public List<String> getBookings() {
        return bookings;
    }

    public void setBookings(List<String> bookings) {
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
        return "Lesson{" +
                "date=" + date +
                ", dayOfTheWeek=" + dayOfTheWeek +
                ", coach='" + coach + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", lessonRef='" + lessonRef + '\'' +
                ", capacity=" + capacity +
                ", bookings=" + bookings +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }

}