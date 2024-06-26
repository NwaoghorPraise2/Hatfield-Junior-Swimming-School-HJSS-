package models;

import appManager.AppManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a lesson in the system.
 */
public class Lesson {
    private LocalDate date;
    private DayOfWeek dayOfTheWeek;
    private String coach;
    private int gradeLevel;
    private String lessonRef;
    private int capacity;
    private String[] bookings;
    private String status;
    private LocalTime startTime;
    private String timeSlot;
    private static int lessonCounter = 1;

    /**
     * Constructs a Lesson object with the given attributes.
     * @param date The date of the lesson.
     * @param dayOfTheWeek The day of the week of the lesson.
     * @param coach The coach assigned to the lesson.
     * @param gradeLevel The grade level of the lesson.
     * @param capacity The capacity of the lesson.
     */
    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, int capacity) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        generateLessonRef();
        this.capacity = capacity;
        this.bookings = new String[capacity];
        this.status = "Available";
        this.startTime = AppManager.getInstance().setStartTime(dayOfTheWeek);
        this.timeSlot = createTimeSlot(startTime);
    }

    // Empty constructor for data generation purpose only
    public Lesson(){}

    private void generateLessonRef() {
        this.lessonRef = String.format("%s-%02d-%02d-%d-SLT%d", dayOfTheWeek.toString().toLowerCase().substring(0, 3), date.getDayOfMonth(), date.getMonthValue(), date.getYear(), lessonCounter++);
    }

    private String createTimeSlot(LocalTime startTime) {
        LocalTime endTime = startTime.plusHours(1);
        return startTime + " - " + endTime;
    }

    /**
     * Checks if the lesson is available.
     * @return true if the lesson is available, false otherwise.
     */
    public Boolean isAvailable() {
        return this.status.equals("Available");
    }

    /**
     * Updates the status of the lesson based on the current bookings.
     */
    public void updateStatus() {
        long nonNullBookingsCount = Arrays.stream(bookings)
                .filter(Objects::nonNull)
                .count();

        status = nonNullBookingsCount >= this.capacity ? "Fully Booked" : "Available";
    }

    /**
     * Adds a learner to the list of booked learners for this lesson.
     * @param learnerId The ID of the learner to be added.
     */
    public void addBookedLearner(String learnerId) {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                bookings[i] = learnerId;
                updateStatus();
                return;
            }
        }
    }

    /**
     * Removes a learner from the list of booked learners for this lesson.
     * @param learnerId The ID of the learner to be removed.
     */
    public void removeBookedLearner(String learnerId) {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && bookings[i].equals(learnerId)) {
                bookings[i] = null;
                updateStatus();
                return;
            }
        }
    }


    // Getters and Setters

    public void setBookings(String[] bookings) {
        this.bookings = bookings;
    }

    public String[] getBookings() {
        return bookings;
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
                ", bookings=" + Arrays.toString(bookings) +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}
