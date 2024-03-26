package utils;
import controller.LearnerController;
import models.Learner;
import java.util.Random;
import java.time.LocalDate;
public class LearnDataGenerator {
    private static final String[] FIRST_NAMES = {"Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    Learner learner = new Learner();
    LearnerController learnerController = new LearnerController();
    public void generateDummyData(int count) {
        for (int i = 1; i <= count; i++) {
            String id = learner.generateID(generateDateOfBirth());
            String name = generateName();
            String gender = generateGender();
            LocalDate dateOfBirth = generateDateOfBirth();
            String emergencyContact = generateEmergencyContact();
            int currentGradeLevel = generateGradeLevel();
            learnerController.registerLearner(name, dateOfBirth, gender, emergencyContact, currentGradeLevel);
        }
    }

    public static String generateName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    public static String generateGender() {
        String[] genders = {"Male", "Female"};
        return genders[new Random().nextInt(genders.length)];
    }

    public static LocalDate generateDateOfBirth() {
        int startYear = LocalDate.now().getYear() - 11;
        int endYear = LocalDate.now().getYear() - 4;
        int year = startYear + new Random().nextInt(endYear - startYear + 1);
        int month = new Random().nextInt(12) + 1;
        int day = new Random().nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    public static String generateEmergencyContact() {
        Random random = new Random();
        long number = 7000000000L + (long) (random.nextDouble() * 1000000000L);
        return "+44" + number;
    }

    public static int generateGradeLevel() {
        return new Random().nextInt(5) + 1; // 1 to 5
    }

}
