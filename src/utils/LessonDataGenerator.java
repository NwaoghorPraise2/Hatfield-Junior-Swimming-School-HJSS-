package utils;

import controller.LessonController;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

public class LessonDataGenerator {
    private LocalDate currentDate = LocalDate.now();
    private LocalDate startDate = currentDate.minusWeeks(4);
    private LocalDate endDate = currentDate.plusWeeks(2);
    private LearnDataGenerator learnDataGenerator = new LearnDataGenerator();
    private AppManager appManager = new AppManager();
    LessonController lessonController = new LessonController();

    public void generateLessonData() {
        while (startDate.isBefore(endDate)) {
           for (int i = 0; i < 7; i++) {
               DayOfWeek dayOfWeek = startDate.getDayOfWeek();
               if(dayOfWeek.getValue() == 1 || dayOfWeek.getValue() == 2 || dayOfWeek.getValue() == 3 || dayOfWeek.getValue() == 6 ){
                   lessonController.createLesson(startDate, dayOfWeek, appManager.assignGradeLevel());
               }
               startDate = startDate.plusDays(1);
           }
        }
    }
}
