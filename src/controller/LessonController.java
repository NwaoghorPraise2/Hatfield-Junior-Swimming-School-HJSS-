package controller;

import database.LessonDB;
import models.Learner;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
}
