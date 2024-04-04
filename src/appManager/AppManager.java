package appManager;

import controller.LearnerController;
import controller.LessonController;
import models.Learner;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Random;

public class AppManager {
    private static AppManager instance;
    private final String[] COACHES = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Christopher", "Jennifer"};
    private final Random random = new Random();
    private LearnerController learnerController = LearnerController.getInstance();
    private LessonController lessonController = LessonController.getInstance();

    // Private constructor to prevent instantiation from outside
    private AppManager() {
    }

    // Method to get the singleton instance
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }




    public LocalTime setStartTime(DayOfWeek dayOfWeek) {
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
        return COACHES[random.nextInt(COACHES.length)]; // Randomly select a coach from the array
    }

    public int setLessonCapacity() {
        return 4; // Return a constant value for lesson capacity
    }

    public int assignGradeLevel() {
        return random.nextInt(5) + 1; // Randomly assign a grade level between 1 and 5
    }
}
