package utils.dataGenerators;

import appManager.AppManager;
import controller.BookingController;
import controller.LearnerController;
import controller.LessonController;
import models.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class to generate lesson data and update lesson data.
 */
public class LessonDataGenerator {
    private LocalDate currentDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LearnDataGenerator learnDataGenerator;
    private AppManager appManager;
    private LessonController lessonController;
    private LearnerController learnerController;
    private BookingController bookingController;

    /**
     * Constructs a LessonDataGenerator instance.
     */
    public LessonDataGenerator() {
        currentDate = LocalDate.now();
        startDate = currentDate.minusWeeks(4);
        endDate = currentDate.plusWeeks(2);
        learnDataGenerator = new LearnDataGenerator();
        appManager = AppManager.getInstance();
        lessonController = LessonController.getInstance();
        learnerController = new LearnerController();
        bookingController = new BookingController();
    }

    /**
     * Generates lesson data for the specified duration.
     */
    public void generateLessonData() {
        LocalDate tempDate = startDate;

        while (!tempDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = tempDate.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY ||
                    dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
                int lessonsToGenerate = (dayOfWeek == DayOfWeek.SATURDAY) ? 2 : 3;

                for (int i = 0; i < lessonsToGenerate; i++) {
                    lessonController.createLesson(tempDate, dayOfWeek, appManager.assignCoach(),
                            appManager.assignGradeLevel(), appManager.setLessonCapacity());
                }
            }
            tempDate = tempDate.plusDays(1);
        }
    }

    /**
     * Updates lesson data by assigning learners to lessons.
     */
    public void updateLessonData() {
        List<Lesson> lessons = lessonController.getAllLessons();

        for (Lesson lesson : lessons) {
            List<String> learnerIDs = new ArrayList<>();
            int numLearners = getRandomNumberInRange(1, 4);

            while (learnerIDs.size() < numLearners) {
                String randomLearnerID = learnerController.getRandomLearnerID();
                if (!learnerIDs.contains(randomLearnerID)) {
                    learnerIDs.add(randomLearnerID);
                }
            }

            String[] learnerIDArray = learnerIDs.toArray(new String[0]);

            lessonController.updateLesson(lesson.getLessonRef(), learnerIDArray);
        }
    }

    /**
     * Generates a random number within the specified range.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return The randomly generated number.
     */
    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
