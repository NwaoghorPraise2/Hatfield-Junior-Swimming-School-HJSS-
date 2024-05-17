package view;

import controller.LessonController;
import models.Lesson;

import java.util.List;

public class LessonView {
    private LessonController lessonController = new LessonController();
    public void displayLessons() {
        List<Lesson> allLessons = lessonController.getAllLessons();
        for (Lesson lesson : allLessons) {
            System.out.println(lesson.toString());
        }
    }
}
