package utils;

import controller.LessonController;
import models.Lesson;

public class TimeTableHandler {

    LessonController lessonController = new LessonController();

    public void displayTimeTable() {
        System.out.println("========== TIMETABLE ==========");
        System.out.printf("%-20s%-12s%-10s%-20s%-40s\n", "Date", "Day", "Grade", "Status", "Time Slots");
        System.out.println("-------------------------------------------------------------------------------------------");

        for(Lesson lesson : lessonController.getAllLessons()) {
            String [] slots = lesson.getTimeSlots().split(",");

            int maxSlotLength = lesson.getCapacity();

            for(String slot : slots) {
                System.out.printf("%-20s%-12s%-10s%-20s%-40s",
                        lesson.getDate(),
                        lesson.getDayOfTheWeek(),
                        lesson.getGradeLevel(),
                        lesson.getStatus(),
                        slot);
                if (slot.length() > maxSlotLength){
                    for(int i = 0; i < maxSlotLength - slot.length(); i++ ){
                        System.out.print(" ");
                    }
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------");
        }
    }
}