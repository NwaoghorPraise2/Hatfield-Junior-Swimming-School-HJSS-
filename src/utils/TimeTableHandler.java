package utils;

import controller.LessonController;
import models.Lesson;

public class TimeTableHandler {

    LessonController lessonController = new LessonController();

    public void displayTimeTable() {
        for (Lesson lesson : lessonController.getAllLessons()) {
            System.out.printf("%-20s%-12s%-20s%-25s\n", "Date", "Day", "Status", "LessonRef");
            System.out.printf("%-20s%-12s%-20s%-25s\n", lesson.getDate(), lesson.getDayOfTheWeek(), lesson.getStatus(), lesson.getLessonRef());
            System.out.println();
            System.out.println("Time Slots:\tCoach:\t\tGrade Level:");

            String[] slots = lesson.getTimeSlots().split("\n");
            for (String slot : slots) {
                System.out.printf("       %-120s\n", slot);
            }
            System.out.println();
        }

//Level
//            String timeSlots = lesson.getTimeSlots();
//            String[] slots = timeSlots.split("\n");
//            for (String slot : slots) {
//                String[] slotDetails = slot.split(" ");
//                String slotNumber = slotDetails[1].substring(0, slotDetails[1].length() - 1);
//                String timeRange = slotDetails[3] + " to " + slotDetails[5];
//                String coach = slotDetails[6].substring(6);
//                String gradeLevel = slotDetails[9];

//               9 System.out.printf("Slot %s: %-11s Coach: %-15s Grade Level: %s\n", slotNumber, timeRange, coach, gradeLevel);
//
////                System.out.printf("%-30s%-12s%-20s\n", timeSlot, coach, gradeLevel);gradeLevel
//            }
//            System.out.println();
    }
}