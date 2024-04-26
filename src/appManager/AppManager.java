package appManager;

import controller.LearnerController;
import controller.LessonController;
import database.LessonDB;
import utils.dataGenerators.BookingsDataGenerator;
import utils.dataGenerators.LearnDataGenerator;
import utils.dataGenerators.LessonDataGenerator;
import utils.dataGenerators.ReviewDataGenerator;
import view.HJSSView;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Random;

/**
 * Singleton class responsible for managing the application.
 */
public class AppManager {
    private static AppManager instance;
    private final String[] COACHES = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Christopher", "Jennifer"};
    private final Random random = new Random();
    private LearnerController learnerController = new LearnerController();
    private LessonController lessonController = LessonController.getInstance();

    // Private constructor to prevent instantiation from outside
    private AppManager() {
    }

    /**
     * Retrieves the singleton instance of the AppManager class.
     * @return The singleton instance of AppManager.
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * Sets the start time for a lesson based on the given day of the week.
     * @param dayOfWeek The day of the week for which to set the start time.
     * @return The start time for the lesson.
     */
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

    public void startApp(){
        BookingsDataGenerator bookingDataGenerator = new BookingsDataGenerator();
        LearnDataGenerator dataGenerator = new LearnDataGenerator();
        LessonDataGenerator lessonDataGenerator = new LessonDataGenerator();
        ReviewDataGenerator reviewDataGenerator = new ReviewDataGenerator();
        dataGenerator.generateDummyData(20);
        lessonDataGenerator.generateLessonData();
        lessonDataGenerator.updateLessonData();
        bookingDataGenerator.generateBookings();
        dataGenerator.updateLearnerData();
        reviewDataGenerator.generateReviewsForAttendedBookings();
        System.out.println("Loading.............................");
        System.out.println("System initialised!!!!!!!!!!!!!!!!!!!");
        new HJSSView();
    }

    /**
     * Assigns a coach randomly from the available list of coaches.
     * @return The name of the assigned coach.
     */
    public String assignCoach() {
        return COACHES[random.nextInt(COACHES.length)]; // Randomly select a coach from the array
    }

    /**
     * Sets the capacity for a lesson.
     * @return The capacity for the lesson.
     */
    public int setLessonCapacity() {
        return 4; // Return a constant value for lesson capacity
    }

    /**
     * Assigns a grade level randomly between 1 and 5.
     * @return The assigned grade level.
     */
    public int assignGradeLevel() {
        return random.nextInt(5) + 1; // Randomly assign a grade level between 1 and 5
    }
}
