package view;

import utils.AppManager;

import java.util.Scanner;

public class BoookingView {
    private final Scanner scanner = new Scanner(System.in);
    private final AppManager appManager = AppManager.getInstance();


    public void bookLesson(){
        String learnerId;
        String lessonId;
        while (true) {
            try {
                System.out.println("Enter your Username:");
                learnerId = scanner.nextLine();
//                learnerInputValidator.isValidName(name); add input validator
                break; // If name is valid, exit the loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        while (true) {
            try {
                System.out.println("Enter your preffered lessonRef:");
                lessonId = scanner.nextLine();
//                learnerInputValidator.isValidName(name); add input validator
                break; // If name is valid, exit the loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        String result = appManager.bookLesson(learnerId, lessonId);
        System.out.println(result);
        System.out.println();
    }
}
