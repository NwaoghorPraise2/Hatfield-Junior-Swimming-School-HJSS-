package utils;

import controller.LessonController;
import models.Lesson;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Utility class for handling timetable operations.
 */
public class TimeTableHandler {
    private LessonController lessonController;
    private LocalDate currentDate;
    private LocalDate endDate;

    /**
     * Constructor to initialize the TimeTableHandler.
     */
    public TimeTableHandler() {
        this.lessonController = new LessonController();
        this.currentDate = LocalDate.now();
        this.endDate = currentDate.plusWeeks(2);
    }

    /**
     * Display the entire timetable.
     */
    public void displayTimetable() {
        try {
            printTimeTableHeader();
            List<Lesson> lessons = lessonController.getAllLessons();
            printTimeTableBody(lessons);
        } catch (Exception e) {
            System.err.println("Error occurred while displaying timetable: " + e.getMessage());
        }
    }

    /**
     * Display the timetable for a specific grade level.
     * @param gradeLevel The grade level for which to display the timetable.
     */
    public void displayTimetableByGradeLevel(int gradeLevel) {
        try {
            printTimeTableHeader();

            List<Lesson> lessons = lessonController.getLessonsByGradeLevel(gradeLevel);
            if (lessons.isEmpty()) System.out.println("No lessons found for grade level: " + gradeLevel);

            System.out.println("Grade Level: " + gradeLevel);
            printTimeTableBody(lessons);
        } catch (Exception e) {
            System.err.println("Error occurred while displaying timetable by grade level");
        }
    }


    /**
     * Display the timetable for a specific coach.
     * @param coachName The name of the coach for which to display the timetable.
     */
    public void displayTimetableByCoachName(String coachName) {
        try {
            printTimeTableHeader();

            List<Lesson> lessons = lessonController.getLessonsByCoach(coachName);
            if (lessons.isEmpty()) System.out.println("No lessons found for coach name: " + coachName);

            System.out.println("Coach Name: " + coachName);
            printTimeTableBody(lessons);
        } catch (Exception e) {
            System.err.println("Error occurred while displaying timetable by coach name");
        }
    }

    /**
     * Display the timetable for a specific day of the week.
     * @param dayOfTheWeek The day of the week for which to display the timetable.
     */
    public void displayTimetableByDayOfWeek(DayOfWeek dayOfTheWeek) {
        try {
            printTimeTableHeader();

            List<Lesson> lessons = lessonController.getLessonsByDay(dayOfTheWeek);
            if (lessons.isEmpty()) System.out.println("No lessons found for this Day: " + dayOfTheWeek);

            System.out.println("Day of the Week: " + dayOfTheWeek);
            printTimeTableBody(lessons);
        } catch (Exception e) {
            System.err.println("Error occurred while displaying timetable by day of the week");
        }
    }

    /**
     * Print the header of the timetable.
     */
    public void printTimeTableHeader() {
        System.out.println("Timetable for from: " + currentDate + " " + "to" + " " + endDate);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Date       | Day         | Coach            | Grade Level | Lesson Ref           | Capacity | Status       | Time Slot           |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Print the body of the timetable.
     * @param lessons The list of lessons to print in the timetable.
     */
    public void printTimeTableBody(List<Lesson> lessons) {
        try {
            for (Lesson lesson : lessons) {
                if (lesson.getDate().isAfter(currentDate) && lesson.getDate().isBefore(endDate))
                    System.out.printf("| %-10s | %-11s | %-16s | %-11d | %-20s | %-8d | %-12s | %-19s |%n",
                            lesson.getDate(), lesson.getDayOfTheWeek(), lesson.getCoach(), lesson.getGradeLevel(),
                            lesson.getLessonRef(), lesson.getCapacity(), lesson.getStatus(), lesson.getTimeSlot());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println("Error occurred while printing timetable body.");
        }
    }
}
