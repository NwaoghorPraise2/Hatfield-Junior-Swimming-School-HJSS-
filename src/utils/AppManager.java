package utils;

import controller.LessonController;
import database.LessonDB;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppManager {

    public LocalTime getStartTime(DayOfWeek dayOfWeek) {
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

    public String assignCoach() {
        String[] coaches = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Christopher", "Jennifer"};
        Random random = new Random();
        String coach = coaches[random.nextInt(coaches.length)];
        return coach;
    }


    public int setLessonCapacity() {
        return 4;
    }

    public void addLesson() {
        LessonController lessonController = new LessonController();
        lessonController.createLesson(LocalDate.of(2022, 3, 12), DayOfWeek.WEDNESDAY, 2);
    }
}
