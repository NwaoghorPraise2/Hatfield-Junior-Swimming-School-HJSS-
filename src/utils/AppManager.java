package utils;

import controller.LessonController;
import database.LessonDB;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppManager {

    public LocalTime getStartTime(DayOfWeek dayOfWeek) {
        // You can implement your logic to retrieve start time dynamically
        // For demonstration, returning some hardcoded values
        switch (dayOfWeek) {
            case MONDAY:
            case WEDNESDAY:
            case FRIDAY:
                return LocalTime.of(16, 0); // Default start time for Monday, Wednesday, Friday
            case SATURDAY:
                return LocalTime.of(14, 0); // Default start time for Saturday
            default:
                return LocalTime.of(0, 0); // Default start time
        }
    }

    LessonDB lessonDB = LessonDB.getInstance();
//    public void addLesson (){
//        LessonController lessonController = new LessonController();
//        String result = lessonController.createLesson(3, DayOfWeek.MONDAY , LocalTime.of(9,0));
//        System.out.println(result);
//        System.out.println(lessonDB.getLessons().toString());
//    }
}
