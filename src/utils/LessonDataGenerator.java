package utils;

import appManager.AppManager;
import controller.LessonController;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class LessonDataGenerator {
    private LocalDate currentDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LearnDataGenerator learnDataGenerator;
    private AppManager appManager;
    private LessonController lessonController;

    public LessonDataGenerator() {
        currentDate = LocalDate.now();
        startDate = currentDate.minusWeeks(4);
        endDate = currentDate.plusWeeks(2);
        learnDataGenerator = new LearnDataGenerator();
        appManager = AppManager.getInstance();
        lessonController = new LessonController();
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
                }
            }
            // Move to the next day
            tempDate = tempDate.plusDays(1);
        }
    }
}
