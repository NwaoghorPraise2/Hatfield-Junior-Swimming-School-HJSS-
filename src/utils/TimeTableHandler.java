package utils;

import controller.LearnerController;
import controller.LessonController;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class TimeTableHandler {
    private LessonController lessonController;


    public TimeTableHandler() {
        this.lessonController = new LessonController();
    }

    public void displayTimetable() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        System.out.println("Timetable for the week starting from: " + currentDate);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Date       | Day         | Coach            | Grade Level | Lesson Ref           | Capacity | Status       | Time Slot           |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

        // Display timetable for the next 7 days
            List<Lesson> lessons = lessonController.getLessonsByGradeLevel(5);

            for (Lesson lesson : lessons) {
                System.out.printf("| %-10s | %-11s | %-16s | %-11d | %-20s | %-8d | %-12s | %-19s |%n",
                        lesson.getDate(), lesson.getDayOfTheWeek(), lesson.getCoach(), lesson.getGradeLevel(),
                        lesson.getLessonRef(), lesson.getCapacity(), lesson.getStatus(), lesson.getTimeSlot());
            }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    }
}