package controller;

import database.LessonDB;
import models.Learner;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class LessonController {
    LessonDB lessonDB = LessonDB.getInstance();
    public String createLesson (int gradeLevel, DayOfWeek day, LocalTime timeSlot){
        Lesson lesson = new Lesson(gradeLevel, day, timeSlot);
        lessonDB.addLesson(lesson);
        return "LESSION ADDED";
    }

    public  List<Lesson> getAllLessons() {
        return lessonDB.getLessons();
    }
}
