package database;

import models.Lesson;
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

        public int getLessonCount() {
            return lessons.size();
        }
}
