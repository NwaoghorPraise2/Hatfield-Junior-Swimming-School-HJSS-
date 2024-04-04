package view;

import appManager.AppManager;
import controller.BookingController;
import middlewares.BookingDataInputValidator;

import java.util.Scanner;

public class BookingView {
    private final Scanner scanner = new Scanner(System.in);
    private final AppManager appManager = AppManager.getInstance();
    private final BookingController bookingController = new BookingController();
    private final BookingDataInputValidator bookingDataInputValidator = new BookingDataInputValidator();



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
}