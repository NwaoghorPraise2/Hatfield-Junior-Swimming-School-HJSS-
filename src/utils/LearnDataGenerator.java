package utils;

import appManager.AppManager;
import controller.LearnerController;

import java.util.Random;
import java.time.LocalDate;

public class LearnDataGenerator {
    private static final String[] FIRST_NAMES = {"Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    private static final String[] GENDERS = {"Male", "Female"};
    private static final long MIN_PHONE_NUMBER = 7000000000L;
    private static final long MAX_PHONE_NUMBER = 7999999999L;
    // Using Singleton pattern
    private LearnerController learnerController = LearnerController.getInstance();
    private AppManager appManager;

    // Constructor
    public LearnDataGenerator() {
        this.appManager = AppManager.getInstance();
    }

    // Method to generate dummy learner data
    public void generateDummyData(int count) {
        for (int i = 0; i < count; i++) {
            String name = generateName();
            String gender = generateGender();
            LocalDate dateOfBirth = generateDateOfBirth();
            String emergencyContact = generateEmergencyContact();
            int currentGradeLevel = appManager.assignGradeLevel();
            learnerController.registerLearner(name, dateOfBirth, gender, emergencyContact, currentGradeLevel);
        }
    }

    // Method to generate a random name
    private String generateName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    // Method to generate a random gender
    private String generateGender() {
        return GENDERS[new Random().nextInt(GENDERS.length)];
    }

    // Method to generate a random date of birth between 4 and 11 years ago
    private LocalDate generateDateOfBirth() {
        int startYear = LocalDate.now().getYear() - 11;
        int endYear = LocalDate.now().getYear() - 4;
        int year = startYear + new Random().nextInt(endYear - startYear + 1);
        int month = new Random().nextInt(12) + 1;
        int day = new Random().nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    // Method to generate a random emergency contact number
    private String generateEmergencyContact() {
        Random random = new Random();
        long number = MIN_PHONE_NUMBER + (long) (random.nextDouble() * (MAX_PHONE_NUMBER - MIN_PHONE_NUMBER + 1));
        return "+44" + number;
    }
}
