package database;

import models.Lesson;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class LessonDB {
        private final List<Lesson> lessons;
        private static LessonDB instance;

        public static LessonDB getInstance() {
            if (instance == null) {
                instance = new LessonDB();
            }
            return instance;
        }

        public LessonDB() {
            lessons = new ArrayList<>();
        }

        public void addLesson(Lesson lesson) {
            lessons.add(lesson);
        }

        public List<Lesson> getLessons() {
            return lessons;
        }

        public List<Lesson> getLessonsByGradeLevel(int gradeLevel) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getGradeLevel() == gradeLevel) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }

        public List<Lesson> getLessonsByCoach(String coach) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().equals(coach)) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }

        public Lesson getLessonByRef(String lessonRef) {
            for (Lesson lesson : lessons) {
                if (lesson.getLessonRef().equals(lessonRef)) {
                    return lesson;
                }
            }
            return null;
        }

       public List<Lesson> getLessonsByDay(DayOfWeek dayOfTheWeek) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getDayOfTheWeek().equals(dayOfTheWeek)) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }
        public int getLessonCount() {
            return lessons.size();
        }
}
