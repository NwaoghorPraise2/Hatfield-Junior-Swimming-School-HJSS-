package utils;

import controller.LessonController;
import models.Lesson;

public class TimeTableHandler {

    LessonController lessonController = new LessonController();

    public void displayTimeTable() {
        System.out.println("========== TIMETABLE ==========");
        System.out.printf("%-20s%-12s%-10s%-20s%-30s%-1000s\n", "Date", "Day", "Grade", "Status", "LessonRef", "Time Slots");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for(Lesson lesson : lessonController.getAllLessons()) {
            String [] slots = lesson.getTimeSlots().split(",");

            int maxSlotLength = lesson.getCapacity();

            for(String slot : slots) {
                System.out.printf("%-20s%-12s%-10s%-20s%-30s%-1000s",
                        lesson.getDate(),
                        lesson.getDayOfTheWeek(),
                        lesson.getGradeLevel(),
                        lesson.getStatus(),
                        lesson.getLessonRef(),
                        slot);
                if (slot.length() > maxSlotLength){
                    for(int i = 0; i < maxSlotLength - slot.length(); i++ ){
                        System.out.print("                 -40s%\n" + slot);
                    }
                    System.out.println();
                }
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }
}