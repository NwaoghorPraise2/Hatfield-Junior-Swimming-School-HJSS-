package view;
import controller.LessonController;
import utils.TimeTableHandler;

import java.util.*;

public class HJSSView {
    private static final Scanner scanner = new Scanner(System.in);
    private final LearnerView learnerView = new LearnerView();
    private final LessonView lessonView = new LessonView();

    private final TimeTableHandler timeTableHandler = new TimeTableHandler();

    public HJSSView() {
        start();
    }

    public void start() {

        while (true) {
            System.out.println("""
                    Welcome to Hatfield Junior School System
                    +-----------------------------------------------------+
                    1. Register a learner
                    2. Exit
                    3. View all learners
                    4. Display Lessons
                    +-----------------------------------------------------+
                    Enter your choice:
                    """);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> learnerView.registerLearner();
                case 2 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                case 3 -> learnerView.displayLearner();
                case 4 -> lessonView.displayLessons();
                case 5 -> timeTableHandler.displayTimeTable();
                default -> System.out.println("Invalid choice");
            }
        }
    }
}