package view;

import appManager.AppManager;
import controller.BookingController;
import middlewares.BookingDataInputValidator;
import utils.TimeTableHandler;

import java.time.DayOfWeek;
import java.util.Scanner;

public class BookingView {
    private final Scanner scanner = new Scanner(System.in);
    private final AppManager appManager = AppManager.getInstance();
    private final BookingController bookingController = new BookingController();
    private final BookingDataInputValidator bookingDataInputValidator = new BookingDataInputValidator();
    private final TimeTableHandler timeTableHandler = new TimeTableHandler();



    public void displayTimeTable () {
            System.out.println("How do you want to view the timetable?:");
            System.out.println("1. By coach name");
            System.out.println("2. By day of the week");
            System.out.println("3. By grade level");
            System.out.println("Enter your Choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                System.out.println("Enter coach name:");
                String coachName = scanner.nextLine();

                timeTableHandler.displayTimetableByCoachName(coachName);
            } else if (choice == 2) {
                System.out.println("Enter day of the week(Either: Monday, Wednesday, Friday or Saturday:");
                String dayOfTheWeek = scanner.nextLine().toUpperCase();
                DayOfWeek day = DayOfWeek.valueOf(dayOfTheWeek);
                timeTableHandler.displayTimetableByDayOfWeek(day);
            } else if (choice == 3) {
                System.out.println("Enter grade level:");
                int gradeLevel = scanner.nextInt();
                // Display timetable for the entered grade level
            } else {
                System.out.println("Invalid input! Please enter a valid choice (1, 2, or 3).");
            }

            // Ask user to select a lesson by copying the lessonRef from the timetable
            System.out.println("Please select a lesson by copying the lessonRef from the timetable.");
        }




    }
        //Ask the use how they want to view the timetable
        //either by coach name, day of the week or gradelevel
        //1. will be for coach name
        //2. will be for day of the week
        //3. will be for gradelevel
        //if the user enters a coach name, display the timetable for that coach
        //if the user enters a day of the week, display the timetable for that day
        //if the user enters a gradelevel, display the timetable for that gradelevel
        //if the user enters an invalid input, display an error message
        //Ask user to select a lesson by copyiny the lessonRef from the timetable
    }




    public void bookLesson(){



        String learnerId;
        String lessonRef;

        while (true) {
            try {
                System.out.println("Enter your Username:");
                learnerId = scanner.nextLine();
                bookingDataInputValidator.isStringValid(learnerId);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        while (true) {
            try {
                System.out.println("Enter your preferred lesson's lessonRef:");
                lessonRef = scanner.nextLine();
                bookingDataInputValidator.isStringValid(lessonRef);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        String result = bookingController.createBooking(learnerId.trim(), lessonRef.trim());
        System.out.println(result);
        System.out.println();
    }

//    public void changeBooking(){
//        String learnerId;
//        String lessonRef;
//        while (true) {
//            try {
//                System.out.println("Enter your Username:");
//                learnerId = scanner.nextLine();
//                bookingDataInputValidator.isStringValid(learnerId);
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Invalid input. Please try again.");
//                scanner.nextLine();
//            }
//        }
//
//        while (true) {
//            try {
//                System.out.println("Enter your preferred lesson's lessonRef:");
//                lessonRef = scanner.nextLine();
//                bookingDataInputValidator.isStringValid(lessonRef);
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Invalid input. Please try again.");
//                scanner.nextLine();
//            }
//        }
//
//        String result = bookingController.createBooking(learnerId.trim(), lessonRef.trim());
//        System.out.println(result);
//        System.out.println();
//    }
//}