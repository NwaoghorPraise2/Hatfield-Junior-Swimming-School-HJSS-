package view;

import controller.LearnerController;
import models.Learner;
import middlewares.LearnerInputValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LearnerView {
    private final static Scanner scanner = new Scanner(System.in);
    private LearnerController learnerController = new LearnerController();
    private final LearnerInputValidator learnerInputValidator = new LearnerInputValidator();

    public void displayLearner() {
        List<Learner> allLearners = learnerController.getAllLearners();
        for (Learner learner : allLearners) {
            System.out.println(learner.toString());
        }
    }


//    public void registerLearner() {
//    }
     public void registerLearner() {
        System.out.println("Register with us today. Follow the instructions!!!");

        String name = getInput("Enter your name:", learnerInputValidator::isValidName);
        String gender = getInput("Enter your gender (Either Male or Female or others):", learnerInputValidator::isValidGender);
        LocalDate dateOfBirth = getInput("Please enter your date of birth (YYYY-MM-DD):", LocalDate::parse, learnerInputValidator::isValidDateOfBirth);
        String emergencyContactNumber = getInput("Enter your Emergency Contact number (+447917490416):", learnerInputValidator::isValidUKPhoneNumber);
        int gradeLevel = getInput("Enter your Swimming Grade Level (1, 2, 3, 4, or 5):", Integer::parseInt, learnerInputValidator::isValidGradeLevel);

        String result = learnerController.registerLearner(name, dateOfBirth, gender, emergencyContactNumber, gradeLevel);
        System.out.println(result);
        System.out.println();
    }

    private static <T> T getInput(String prompt, InputParser<T> parser, InputValidator<T> validator) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine();
                if (parser != null) {
                    T parsedInput = parser.parse(input);
                    if (validator != null) {
                        validator.validate(parsedInput);
                    }
                    return parsedInput;
                } else if (validator != null) {
                    validator.validate((T) input);
                    return (T) input;
                }
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static <T> T getInput(String prompt, InputValidator<T> validator) {
        return getInput(prompt, null, validator);
    }

    interface InputParser<T> {
        T parse(String input) throws Exception;
    }

    interface InputValidator<T> {
        void validate(T input) throws ValidationException;
    }

    static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}



