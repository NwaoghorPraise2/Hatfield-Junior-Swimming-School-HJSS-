package view;

import controller.LearnerController;
import utils.LearnerInputValidator;
import java.util.*;

public class HJSSView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LearnerInputValidator learnerInputValidator = new LearnerInputValidator();
    private final LearnerController learnerController = new LearnerController();

    private final LearnerView learnerView = new LearnerView();

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
                    +-----------------------------------------------------+
                    """);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1 -> learnerView.registerLearner();
                case 2 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}