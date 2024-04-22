package view;
import appManager.AppManager;
import utils.reportHandlers.CoachReportHandler;
import utils.reportHandlers.LearnerReportHandler;
import utils.timeTableHandler.TimeTableHandler;

import java.util.*;

public class HJSSView {
    private static final Scanner scanner = new Scanner(System.in);
    private final LearnerView learnerView = new LearnerView();
    private final LessonView lessonView = new LessonView();
    private final BookingView bookingView = new BookingView();
    private final ReviewView reviewView = new ReviewView();
//    private final ReportHandler reportHandler = new ReportHandler();
    private final LearnerReportHandler reportHandler = new LearnerReportHandler();
    private final CoachReportHandler coachReportHandler = new CoachReportHandler();

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
                case 9 -> bookingView.displayBookings();
                case 10 -> reviewView.displayReviews();
                case 11 -> reportHandler.generateLearnerReport(3);
                case 12 -> coachReportHandler.generateCoachReport(3);
                case 7 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
