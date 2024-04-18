package view;

import appManager.AppManager;
import controller.BookingController;
import middlewares.BookingDataInputValidator;
import middlewares.LearnerInputValidator;
import utils.TimeTableHandler;

import java.time.DayOfWeek;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The BookingView class provides functionality for users to interact with the booking system.
 * It allows users to view available lessons in the timetable and book a lesson.
 */
public class BookingView {
    private final Scanner scanner = new Scanner(System.in);
    private final AppManager appManager = AppManager.getInstance();
    private final BookingController bookingController = new BookingController();
    private final BookingDataInputValidator bookingDataInputValidator = new BookingDataInputValidator();
    private final TimeTableHandler timeTableHandler = new TimeTableHandler();
    private final LearnerInputValidator learnerInputValidator = new LearnerInputValidator();

    /**
     * Initiates the process of booking a lesson.
     * It displays the timetable, prompts the user to select a lesson,
     * validates user inputs, and creates a booking.
     */
    public void bookLesson() {
        System.out.println("Welcome to the Booking System!!!");
        System.out.println("To book a Lesson, you need to view available lessons in the timetable.");
        System.out.println();

        displayTimeTable();

        System.out.println("Please select a lesson by entering the lesson reference from the timetable.");

        String learnerId = getValidLearnerId();
        String lessonRef = getValidLessonReference();

        String result = bookingController.createBooking(learnerId, lessonRef.trim());
        System.out.println(result);
        System.out.println();
    }

    /**
     * Displays the timetable based on the user's choice of view.
     * The user can choose to view the timetable by coach name, day of the week, or grade level.
     */
    private void displayTimeTable() {
        System.out.println("How do you want to view the timetable?:");
        System.out.println("1. By coach name");
        System.out.println("2. By day of the week");
        System.out.println("3. By grade level");
        System.out.println("Enter your Choice:");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayByCoachName();
                    break;
                case 2:
                    displayByDayOfWeek();
                    break;
                case 3:
                    displayByGradeLevel();
                    break;
                default:
                    System.out.println("Invalid input! Please enter a valid choice (1, 2, or 3).");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    /**
     * Displays the timetable by coach name.
     */
    private void displayByCoachName() {
        try {
            System.out.println("Enter coach name:");
            String coachName = scanner.nextLine();
            bookingDataInputValidator.isStringValid(coachName);
            timeTableHandler.displayTimetableByCoachName(coachName);
        } catch (Exception e) {
            System.out.println("An error occurred");
        }
    }


    /**
     * Displays the timetable by day of the week.
     */
    private void displayByDayOfWeek() {
        System.out.println("Enter day of the week (Monday, Wednesday, Friday, or Saturday):");
        try {
            String dayOfTheWeek = scanner.nextLine().toUpperCase();
            DayOfWeek day = DayOfWeek.valueOf(dayOfTheWeek);
            //Validate the day of the week
            timeTableHandler.displayTimetableByDayOfWeek(day);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid day of the week! Please enter a valid day.");
        }
    }

    /**
     * Displays the timetable by grade level.
     */
    private void displayByGradeLevel() {
        System.out.println("Enter grade level (1 to 5):");
        try {
            int gradeLevel = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            learnerInputValidator.isValidGradeLevel(gradeLevel);
            timeTableHandler.displayTimetableByGradeLevel(gradeLevel);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user to enter a valid learner ID.
     *
     * @return The valid learner ID entered by the user.
     */
    private String getValidLearnerId() {
        while (true) {
            try {
                System.out.println("Enter your Username:");
                String learnerId = scanner.nextLine().trim();
                bookingDataInputValidator.isStringValid(learnerId);
                return learnerId;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prompts the user to enter a valid lesson reference.
     *
     * @return The valid lesson reference entered by the user.
     */
    private String getValidLessonReference() {
        while (true) {
            try {
                System.out.println("Enter your preferred lesson's lessonRef:");
                String lessonRef = scanner.nextLine().trim();
                bookingDataInputValidator.isStringValid(lessonRef);
                return lessonRef;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This method allows the user to select between canceling or changing a booking.
     * Users are prompted to provide their choice and necessary booking details.
     *
     * @throws InputMismatchException If the user enters a non-integer value for their choice.
     */
    public void cancelAndChangeQuerySelector(){
        System.out.println("|Would you like to cancel or change your booking?");
        System.out.println("|Please have your BookingID and New LessonRef ready for changes.");
        System.out.println("|Enter (1) to change booking or (2) to cancel booking.");
        System.out.println();

        try {
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Process user choice
            switch(choice) {
                case 1:
                    // Call method to change booking
                    changeBooking();
                    break;
                case 2:
                    // Call method to cancel booking
                    cancelBooking();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    /**
     * This method allows the user to change their booking by providing the booking ID
     * and the new lesson reference.
     *
     * @throws Exception If an error occurs during the booking update process.
     */
    private void changeBooking() {
        try {
            System.out.println("Enter your BookingID:");
            String bookingId = scanner.nextLine();

            System.out.println("Enter your New LessonRef:");
            String newLessonRef = scanner.nextLine();

            String result = bookingController.updateBooking(bookingId, newLessonRef);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * This method allows the user to cancel their booking by providing the booking ID.
     *
     * @throws Exception If an error occurs during the booking cancellation process.
     */
    private void cancelBooking() {
        try {
            System.out.println("Enter your BookingID:");
            String bookingId = scanner.nextLine();

            String result = bookingController.cancelBooking(bookingId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    //Todo tomorrow
//    Finish attend
//            Finish Data generation
//    finish Reports
//            Spend time to refactor where necessary
//            Finish report
//                    Make video
//                            package software
//    and any other thing



//    public void attendLesson() {
//        System.out.println("Enter your BookingID:");
//        String bookingId = scanner.nextLine();
//
//        String result = bookingController.getLesson(bookingId);
//        System.out.println(result);
//    }
//
//    public void writeReview () {
//        System.out.println("Enter your BookingID:");
//        String bookingId = scanner.nextLine();
//
//        System.out.println("Enter your Review:");
//        String review = scanner.nextLine();
//
//        String result = bookingController.writeReview(bookingId, review);
//        System.out.println(result);
//    }

}