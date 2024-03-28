package controller;

import database.LessonDB;
import models.Learner;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class LessonController {
    LessonDB lessonDB = LessonDB.getInstance();
    public String createLesson (LocalDate date, DayOfWeek dayOfTheWeek, int gradeLevel){
        Lesson lesson = new Lesson(date, dayOfTheWeek, gradeLevel);
        lessonDB.addLesson(lesson);
        return "LESSION ADDED";
    }

    public  List<Lesson> getAllLessons() {
        return lessonDB.getLessons();
    }
}
