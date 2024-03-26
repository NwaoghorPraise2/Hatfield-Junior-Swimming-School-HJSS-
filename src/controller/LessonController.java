package controller;

import database.LessonDB;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class LessonController {
    LessonDB lessonDB = LessonDB.getInstance();

    public void createLesson(int gradeLevel, DayOfWeek day, LocalTime timeSlot){
        Lesson lesson = new Lesson(gradeLevel, day, timeSlot);
    }
}
