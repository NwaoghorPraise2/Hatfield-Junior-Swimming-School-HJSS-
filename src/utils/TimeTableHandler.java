package utils;

import controller.LearnerController;
import controller.LessonController;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TimeTableHandler {
    private LessonController lessonController;
    private LocalDate currentDate;
    private LocalDate endDate;


    public TimeTableHandler() {
        this.lessonController = new LessonController();
        this.currentDate = LocalDate.now();
        this.endDate = currentDate.plusWeeks(2);
    }

    public void displayTimetable() {
            printTimeTableHeader();
            List<Lesson> lessons = lessonController.getAllLessons();
            printTimeTableBody(lessons);
    }

    public void displayTimetableByGradeLevel(int gradeLevel) {
        printTimeTableHeader();
        List<Lesson> lessons = lessonController.getLessonsByGradeLevel(gradeLevel);
        printTimeTableBody(lessons);
    }

    public void displayTimetableByCoachName(String coachName) {
        printTimeTableHeader();
        List<Lesson> lessons = lessonController.getLessonsByCoach(coachName);
        printTimeTableBody(lessons);
    }

    public void displayTimetableByDayOfWeek(DayOfWeek dayOfTheWeek) {
        printTimeTableHeader();
        List<Lesson> lessons = lessonController.getLessonsByDay(dayOfTheWeek);
        printTimeTableBody(lessons);
    }

    public void printTimeTableHeader(){
        System.out.println("Timetable for from: " + currentDate + " " + "to" + " "+endDate);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Date       | Day         | Coach            | Grade Level | Lesson Ref           | Capacity | Status       | Time Slot           |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

    }

    public void printTimeTableBody(List<Lesson> lessons){
        for (Lesson lesson : lessons) {
            if(lesson.getDate().isAfter(currentDate) && lesson.getDate().isBefore(endDate))
                System.out.printf("| %-10s | %-11s | %-16s | %-11d | %-20s | %-8d | %-12s | %-19s |%n",
                    lesson.getDate(), lesson.getDayOfTheWeek(), lesson.getCoach(), lesson.getGradeLevel(),
                    lesson.getLessonRef(), lesson.getCapacity(), lesson.getStatus(), lesson.getTimeSlot());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    }
}