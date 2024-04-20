package database;

import models.Lesson;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Database class responsible for managing lessons.
 */
public class LessonDB {
    private final List<Lesson> lessons;
    private static LessonDB instance;

    /**
     * Retrieves the singleton instance of LessonDB.
     * @return The singleton instance of LessonDB.
     */
    public static LessonDB getInstance() {
        if (instance == null) {
            instance = new LessonDB();
        }
        return instance;
    }

    /**
     * Constructs a LessonDB instance.
     */
    public LessonDB() {
        lessons = new ArrayList<>();
    }

    /**
     * Adds a lesson to the database.
     * @param lesson The lesson to be added.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Retrieves all lessons stored in the database.
     * @return A list of all lessons.
     */
    public List<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Retrieves lessons by grade level.
     * @param gradeLevel The grade level.
     * @return A list of lessons associated with the grade level.
     */
    public List<Lesson> getLessonsByGradeLevel(int gradeLevel) {
        List<Lesson> filteredLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevel) {
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }

    /**
     * Retrieves lessons by coach.
     * @param coach The coach name.
     * @return A list of lessons associated with the coach.
     */
    public List<Lesson> getLessonsByCoach(String coach) {
        List<Lesson> filteredLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().equals(coach)) {
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }

    /**
     * Retrieves a lesson by its reference.
     * @param lessonRef The reference of the lesson.
     * @return The lesson with the specified reference, or null if not found.
     */
    public Lesson getLessonByRef(String lessonRef) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonRef().equals(lessonRef)) {
                return lesson;
            }
        }
        return null;
    }

    /**
     * Retrieves lessons by day of the week.
     * @param dayOfTheWeek The day of the week.
     * @return A list of lessons associated with the day of the week.
     */
    public List<Lesson> getLessonsByDay(DayOfWeek dayOfTheWeek) {
        List<Lesson> filteredLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getDayOfTheWeek().equals(dayOfTheWeek)) {
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }

    /**
     * Retrieves the count of lessons in the database.
     * @return The number of lessons.
     */
    public int getLessonCount() {
        return lessons.size();
    }

    /**
     * Retrieves the lesson reference by its index.
     * @param index The index of the lesson.
     * @return The lesson reference.
     */
    public String getLessonRefByIndex(int index) {
        return lessons.get(index).getLessonRef();
    }

    /**
     * Retrieves a lesson reference randomly.
     * @return A randomly selected lesson reference.
     */
    public String getLessonRefRandomly(){
        int index = (int) (Math.random() * lessons.size());
        return lessons.get(index).getLessonRef();
    }

    /**
     * Retrieves a lesson randomly.
     * @return A randomly selected lesson.
     */
    public Lesson getLessonRandomly(){
        int index = (int) (Math.random() * lessons.size());
        return lessons.get(index);
    }
}
