package utils;

import controller.LessonController;
import database.LessonDB;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class AppManager {

    LessonDB lessonDB = LessonDB.getInstance();
    public void addLesson (){
        LessonController lessonController = new LessonController();
        String result = lessonController.createLesson(3, DayOfWeek.MONDAY , LocalTime.of(9,0));
        System.out.println(result);
        System.out.println(lessonDB.getLessons().toString());
    }
}
