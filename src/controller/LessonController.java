package controller;

import database.LessonDB;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller class responsible for managing lessons.
 */
public class LessonController {
    private static final LessonController INSTANCE = new LessonController();

    // Access point to get the singleton instance
    public static LessonController getInstance() {
        return INSTANCE;
    }

    private LessonDB lessonDB = LessonDB.getInstance();

    /**
     * Creates a new lesson.
     * @param date The date of the lesson.
     * @param dayOfTheWeek The day of the week of the lesson.
     * @param coach The coach assigned to the lesson.
     * @param gradeLevel The grade level associated with the lesson.
     * @param capacity The capacity of the lesson.
     * @return A message indicating the success of the creation.
     */
    public String createLesson(LocalDate date, DayOfWeek dayOfTheWeek, String coach, int gradeLevel, int capacity){
        Lesson lesson = new Lesson(date, dayOfTheWeek, coach, gradeLevel, capacity);
        lessonDB.addLesson(lesson);
        return "LESSON ADDED";
    }

    /**
     * Retrieves all lessons stored in the database.
     * @return A list of all lessons.
     */
    public List<Lesson> getAllLessons() {
        return lessonDB.getLessons();
    }

    /**
     * Retrieves lessons by coach.
     * @param coach The coach name.
     * @return A list of lessons associated with the coach.
     */
    public List<Lesson> getLessonsByCoach(String coach) {
        return lessonDB.getLessonsByCoach(coach);
    }

    /**
     * Retrieves lessons by grade level.
     * @param gradeLevel The grade level.
     * @return A list of lessons associated with the grade level.
     */
    public List<Lesson> getLessonsByGradeLevel(int gradeLevel) {
        return lessonDB.getLessonsByGradeLevel(gradeLevel);
    }

    /**
     * Retrieves lessons by day of the week.
     * @param dayOfTheWeek The day of the week.
     * @return A list of lessons associated with the day of the week.
     */
    public List<Lesson> getLessonsByDay(DayOfWeek dayOfTheWeek) {
        return lessonDB.getLessonsByDay(dayOfTheWeek);
    }

    /**
     * Retrieves a lesson by its reference.
     * @param lessonRef The reference of the lesson.
     * @return The lesson with the specified reference.
     */
    public Lesson getLessonByRef(String lessonRef) {
        return lessonDB.getLessonByRef(lessonRef);
    }

    /**
     * Updates a lesson with the specified reference.
     * @param lessonRef The reference of the lesson to update.
     * @param bookings The array of bookings to update.
     * @return A message indicating the success of the update.
     */
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

    /**
     * Retrieves the lesson reference by its index.
     * @param index The index of the lesson.
     * @return The lesson reference.
     */
    public String getLessonRefByIndex(int index) {
        return lessonDB.getLessonRefByIndex(index);
    }

    /**
     * Retrieves a lesson reference randomly.
     * @return A randomly selected lesson reference.
     */
    public String getLessonRefRandomly(){
        return lessonDB.getLessonRefRandomly();
    }

    /**
     * Retrieves a lesson randomly.
     * @return A randomly selected lesson.
     */
    public Lesson getLessonRandomly(){
        return lessonDB.getLessonRandomly();
    }
}
