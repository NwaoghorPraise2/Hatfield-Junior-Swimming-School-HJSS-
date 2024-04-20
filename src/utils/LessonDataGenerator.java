package utils;

import appManager.AppManager;
import controller.LearnerController;
import controller.LessonController;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class LessonDataGenerator {
    private LocalDate currentDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LearnDataGenerator learnDataGenerator;
    private AppManager appManager;
    private LessonController lessonController;
    private LearnerController learnController;
    private static int index;

    public LessonDataGenerator() {
        currentDate = LocalDate.now();
        startDate = currentDate.minusWeeks(4);
        endDate = currentDate.plusWeeks(2);
        learnDataGenerator = new LearnDataGenerator();
        appManager = AppManager.getInstance();
        lessonController = new LessonController();
        learnController = LearnerController.getInstance();
        index = 0;
    }

    public void generateLessonData() {
        LocalDate tempDate = startDate; // Reuse currentDate for iteration

        while (!tempDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = tempDate.getDayOfWeek();

            // Check if the current day is one of the specified days for lessons
            if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY ||
                    dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
                int lessonsToGenerate = (dayOfWeek == DayOfWeek.SATURDAY) ? 2 : 3;

                // Generate the specified number of lessons for the current day
                for (int i = 0; i < lessonsToGenerate; i++) {
                    lessonController.createLesson(tempDate, dayOfWeek, appManager.assignCoach(),
                            appManager.assignGradeLevel(), appManager.setLessonCapacity());

                    // Create a booking for the lesson
//                    bookingController.createBookingForLesson(lesson);
                }
            }
            // Move to the next day
            tempDate = tempDate.plusDays(1);
        }
    }

    public void updateLessonData() {
        // Get all lessons
        List<Lesson> lessons = lessonController.getAllLessons();

        // Iterate over each lesson
        for (Lesson lesson : lessons) {
            // Create a list to hold learner IDs for each lesson
            List<String> learnerIDs = new ArrayList<>();

            // Generate a random number of unique learner IDs to add to the list
            int numLearners = getRandomNumberInRange(1, 4);

            // Populate the list with unique learner IDs
            while (learnerIDs.size() < numLearners) {
                String randomLearnerID = learnController.getRandomLearnerID();
                if (!learnerIDs.contains(randomLearnerID)) {
                    learnerIDs.add(randomLearnerID);
                }
            }

            // Convert the list to a string array
            String[] learnerIDArray = learnerIDs.toArray(new String[0]);

            // Pass the array and lesson reference to the updateLesson method for each lesson
            lessonController.updateLesson(lesson.getLessonRef(), learnerIDArray);
        }
    }

    // Helper method to generate a random number within a specified range
    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
