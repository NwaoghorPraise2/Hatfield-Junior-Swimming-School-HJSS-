package controller;

import database.LessonDB;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class LessonController {
    private static final LessonController INSTANCE = new LessonController();
    public static LessonController getInstance() {
        return INSTANCE;
    }
    LessonDB lessonDB = LessonDB.getInstance();
    public String createLesson (LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, int capacity){
        Lesson lesson = new Lesson(date, dayOfTheWeek, coach, gradeLevel, capacity);
        lessonDB.addLesson(lesson);
        return "LESSON ADDED";
    }

    public  List<Lesson> getAllLessons() {
        return lessonDB.getLessons();
    }

    public List<Lesson> getLessonsByCoach(String coach) {
        return lessonDB.getLessonsByCoach(coach);
    }

    public List<Lesson> getLessonsByGradeLevel(int gradeLevel) {
        return lessonDB.getLessonsByGradeLevel(gradeLevel);
    }

    public List<Lesson> getLessonsByDay(DayOfWeek dayOfTheWeek) {
        return lessonDB.getLessonsByDay(dayOfTheWeek);
    }

    public Lesson getLessonByRef(String lessonRef) {
        return lessonDB.getLessonByRef(lessonRef);
    }

    public String updateLesson(String lessonRef, String [] bookings) {
        Lesson lessonToUpdate = lessonDB.getLessonByRef(lessonRef);
        if (lessonToUpdate != null) {
            lessonToUpdate.setBookings(bookings);
            lessonToUpdate.updateStatus();
            return "Lesson updated successfully";
        } else {
            return "Lesson with reference " + lessonRef + " not found";
        }
    }

    public String getLessonRefByIndex(int index) {
        return lessonDB.getLessonRefByIndex(index);
    }

    public String getLessonRefRandomly(){
        return lessonDB.getLessonRefRandomly();
    }

    public Lesson getLessonRandomly(){
        return lessonDB.getLessonRandomly();
    }
}
