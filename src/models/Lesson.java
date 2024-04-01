package models;

import utils.AppManager;
import utils.LearnDataGenerator;

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
    private String startTime;
    private String timeSlot;

    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, String lessonRef, int capacity, List<Integer> bookings, String status, String startTime, String timeSlot) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        this.lessonRef = lessonRef;
        this.capacity = capacity;
        this.bookings = bookings;
        this.status = status;
        this.startTime = startTime;
        this.timeSlot = timeSlot;
    }



    //
//    AppManager appManager = new AppManager();
//
//    public Lesson(LocalDate date, DayOfWeek dayOfTheWeek, int gradeLevel) {
//        this.date = date;
//        this.dayOfTheWeek = dayOfTheWeek;
//        this.generateTimeSlots(dayOfTheWeek, appManager.setStartTime(dayOfTheWeek));
//        this.generateLessonRef(date, dayOfTheWeek);
////        this.gradeLevel = gradeLevel;
//        this.capacity = appManager.setLessonCapacity();
//        this.bookings = new ArrayList<>(this.capacity);
//        this.status = "Available";
//    }
//
//    public Lesson (){
//
//    }
//
//
//    private void generateTimeSlots(DayOfWeek dayOfTheWeek, LocalTime startTime) {
//        int numberOfSlots = (dayOfTheWeek == DayOfWeek.SATURDAY) ? 2 : 3;
//        String slotsString = "";
//        for (int i = 0; i < numberOfSlots; i++) {
//            LocalTime endTime = startTime.plusHours(1);
//            String coach = appManager.assignCoach();
//            int gradeLevel = appManager.assignGradeLevel();
//           this.timeSlots = slotsString += "Slot " + (i + 1) + ": " + startTime + " to " + endTime + " " + "Coach:" + coach +" "+ "GradeLevel:" + gradeLevel +"\n";
//            startTime = endTime;
//        }
//    }
//
//    public String getTimeSlots() {
//        return timeSlots;
//    }
//
//    private void generateLessonRef(LocalDate date, DayOfWeek dayOfWeek) {
//        int day = date.getDayOfMonth();
//        int month = date.getMonthValue();
//        int year = date.getYear();
//
//        String dayOfWeekName = dayOfWeek.toString().toLowerCase();
//        this.lessonRef = String.format("%s-%02d-%02d-%d", dayOfWeekName, day, month, year);
//    }
//
//    private void updateStatus() {
//        if (bookings.size() == this.capacity) {
//            this.status = "Fully Booked";
//        }
//    }
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public DayOfWeek getDayOfTheWeek() {
//        return dayOfTheWeek;
//    }
//
//    public void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
//        this.dayOfTheWeek = dayOfTheWeek;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public String getLessonRef() {
//        return lessonRef;
//    }
//
//    public void setLessonRef(String lessonRef) {
//        this.lessonRef = lessonRef;
//    }
//
//
////    public int getGradeLevel() {
////        return gradeLevel;
////    }
////
////    public void setGradeLevel(int gradeLevel) {
////        this.gradeLevel = gradeLevel;
////    }
//
//    public List<Integer> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(List<Integer> bookings) {
//        this.bookings = bookings;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//  @Override
//    public String toString() {
//        return "Lesson{" +
//                "date=" + date +
//                ", dayOfTheWeek=" + dayOfTheWeek +
//                ", timeSlots='" + timeSlots + '\'' +
//                ", lessonRef='" + lessonRef + '\'' +
//                ", bookings=" + bookings +
//                ", capacity=" + capacity +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
