package models;

import utils.AppManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Lesson {

    private LocalDate date;
    private DayOfWeek dayOfTheWeek;
    private String timeSlot;
    private String lessonRef;
    private String coach;
    private int gradeLevel;
    private List<Integer> bookings;
    private int maxBookings;
    private String status;

    AppManager appManager = new AppManager();

    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, String timeSlot, String lessonRef, String coach, int gradeLevel, List<Integer> bookings, int maxBookings, String status) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.timeSlot = generateTimeSlots(dayOfTheWeek, appManager.getStartTime(dayOfTheWeek));
        this.lessonRef = lessonRef;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        this.bookings = bookings;
        this.maxBookings = maxBookings;
        this.status = status;
    }


    private String generateTimeSlots(DayOfWeek dayOfTheWeek, LocalTime startTime) {
        int numberOfSlots = (dayOfTheWeek == DayOfWeek.SATURDAY) ? 2 : 3;
        String slotsString = "";
        for (int i = 0; i < numberOfSlots; i++) {
            LocalTime endTime = startTime.plusHours(1);
            slotsString += "Slot " + (i + 1) + ": " + startTime + " to " + endTime + "\n";
            startTime = endTime;
        }
        return slotsString;
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

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getLessonRef() {
        return lessonRef;
    }

    public void setLessonRef(String lessonRef) {
        this.lessonRef = lessonRef;
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

    public List<Integer> getBookings() {
        return bookings;
    }

    public void setBookings(List<Integer> bookings) {
        this.bookings = bookings;
    }

    public int getMaxBookings() {
        return maxBookings;
    }

    public void setMaxBookings(int maxBookings) {
        this.maxBookings = maxBookings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "date=" + date +
                ", dayOfTheWeek=" + dayOfTheWeek +
                ", timeSlot='" + timeSlot + '\'' +
                ", lessonRef='" + lessonRef + '\'' +
                ", coach='" + coach + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", bookings=" + bookings +
                ", maxBookings=" + maxBookings +
                ", status='" + status + '\'' +
                '}';
    }
}
