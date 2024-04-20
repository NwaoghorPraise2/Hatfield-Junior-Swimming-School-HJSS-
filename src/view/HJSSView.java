package view;
import appManager.AppManager;
import utils.TimeTableHandler;

import java.util.*;

public class HJSSView {
    private static final Scanner scanner = new Scanner(System.in);
    private final LearnerView learnerView = new LearnerView();
    private final LessonView lessonView = new LessonView();
    private final BookingView bookingView = new BookingView();

    private final TimeTableHandler timeTableHandler = new TimeTableHandler();

     private final AppManager appManager = AppManager.getInstance();

    public HJSSView() {
        start();
    }

    public void start() {

        while (true) {
            System.out.println("""
                    Welcome to Hatfield Junior School System
                    +-----------------------------------------------------+
                    1. Book a swimming lesson
                    2. Change/Cancel a booking
                    3. Attend a swimming lesson
                    4. Monthly learner report
                    5. Monthly coach report
                    6. Register a new learner
                    7. Exit
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
                case 1 -> bookingView.bookLesson();
                case 2 -> bookingView.cancelAndChangeQuerySelector();
                case 3 -> bookingView.attendLesson();
                case 4 -> lessonView.displayLessons();
                case 5 -> timeTableHandler.displayTimetable();
                case 6 -> learnerView.registerLearner();
                case 8 -> learnerView.displayLearner();
                case 9 ->bookingView.displayBookings();
                case 7 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
