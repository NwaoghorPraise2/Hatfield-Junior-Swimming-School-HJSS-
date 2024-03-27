package view;

import controller.LearnerController;
import models.Learner;
import utils.LearnerInputValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LearnerView {
    private final Scanner scanner = new Scanner(System.in);
    private final LearnerController learnerController = new LearnerController();
    private final LearnerInputValidator learnerInputValidator = new LearnerInputValidator();

    public void displayLearner() {
        List<Learner> allLearners = learnerController.getAllLearners();
        for (Learner learner : allLearners) {
            System.out.println(learner.toString());
        }
    }


    public void registerLearner () {

            //refactor this code
            String name = null;
            String gender = null;
            LocalDate dateOfBirth = null;
            String emergencyContactNumber = null;
            int gradeLevel = 0;

            while (true) {
                try {
                    System.out.println("Enter your name:");
                    name = scanner.nextLine();
                    learnerInputValidator.isValidName(name);
                    break; // If name is valid, exit the loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Prompt for gender and validate
            while (true) {
                try {
                    System.out.println("Enter your gender (Either Male or Female or others):");
                    gender = scanner.nextLine();
                    learnerInputValidator.isValidGender(gender);
                    break; // If gender is valid, exit the loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Prompt for date of birth and validate
            while (true) {
                try {
                    System.out.println("Please enter your date of birth (YYYY-MM-DD):");
                    dateOfBirth = LocalDate.parse(scanner.nextLine());
                    learnerInputValidator.isValidDateOfBirth(dateOfBirth);
                    break; // If date of birth is valid, exit the loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Prompt for emergency contact number and validate
            while (true) {
                try {
                    System.out.println("Enter your Emergency Contact number (+447917490416):");
                    emergencyContactNumber = scanner.nextLine();
                    learnerInputValidator.isValidUKPhoneNumber(emergencyContactNumber);
                    break; // If emergency contact number is valid, exit the loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Prompt for swimming grade level and validate
            while (true) {
                try {
                    System.out.println("Enter your Swimming Grade Level (1, 2, 3, 4, or 5):");
                    gradeLevel = Integer.parseInt(scanner.nextLine());
                    learnerInputValidator.isValidGradeLevel(gradeLevel);
                    break; // If grade level is valid, exit the loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Register the learner once all inputs are valid
            String result = learnerController.registerLearner(name, dateOfBirth, gender, emergencyContactNumber, gradeLevel);
            System.out.println(result);
            System.out.println();
    }
}


