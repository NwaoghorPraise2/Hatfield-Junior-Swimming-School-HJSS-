package view;

import controller.LessonController;
import models.Learner;
import models.Lesson;

import java.util.List;

public class LessonView {
    private LessonController lessonController = new LessonController();
    private Lesson lesson = new Lesson();
    public void displayLessons() {
        List<Lesson> allLessons = lessonController.getAllLessons();
        for (Lesson lesson : allLessons) {
            System.out.println(lesson.toString());
        }
    }


}
