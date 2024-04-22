package utils.dataGenerators;

import appManager.AppManager;
import controller.BookingController;
import controller.LearnerController;
import models.Booking;
import models.Learner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class to generate dummy learner data.
 */
public class LearnDataGenerator {
    private static final String[] FIRST_NAMES = {"Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    private static final String[] GENDERS = {"Male", "Female"};
    private static final long MIN_PHONE_NUMBER = 7000000000L;
    private static final long MAX_PHONE_NUMBER = 7999999999L;
    // Using Singleton pattern
    private LearnerController learnerController = LearnerController.getInstance();
    private BookingController bookingController = new BookingController();
    private AppManager appManager;

    /**
     * Constructs a LearnDataGenerator instance.
     */
    public LearnDataGenerator() {
        this.appManager = AppManager.getInstance();
    }

    /**
     * Generates dummy learner data.
     * @param count The number of learners to generate.
     */
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

    /**
     * Generates a random name.
     * @return The randomly generated name.
     */
    private String generateName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    /**
     * Generates a random gender.
     * @return The randomly generated gender.
     */
    private String generateGender() {
        return GENDERS[new Random().nextInt(GENDERS.length)];
    }

    /**
     * Generates a random date of birth between 4 and 11 years ago.
     * @return The randomly generated date of birth.
     */
    private LocalDate generateDateOfBirth() {
        int startYear = LocalDate.now().getYear() - 11;
        int endYear = LocalDate.now().getYear() - 4;
        int year = startYear + new Random().nextInt(endYear - startYear + 1);
        int month = new Random().nextInt(12) + 1;
        int day = new Random().nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    /**
     * Generates a random emergency contact number.
     * @return The randomly generated emergency contact number.
     */
    private String generateEmergencyContact() {
        Random random = new Random();
        long number = MIN_PHONE_NUMBER + (long) (random.nextDouble() * (MAX_PHONE_NUMBER - MIN_PHONE_NUMBER + 1));
        return "+44" + number;
    }

    /**
     * Updates learner data based on bookings.
     */
    public void updateLearnerData() {
        // Step 1: Retrieve all learners
        List<Learner> learners = learnerController.getAllLearners();

        // Step 2: Iterate through each learner
        for (Learner learner : learners) {
            String learnerId = learner.getId();

            // Step 3: Retrieve bookings for the current learner
            List<Booking> bookings = bookingController.getBookingsByLearnerId(learnerId);

            // Step 4: Sort bookings by status
            List<String> bookedLessons = new ArrayList<>();
            List<String> attendedLessons = new ArrayList<>();
            List<String> cancelledLessons = new ArrayList<>();

            for (Booking booking : bookings) {
                switch (booking.getStatus()) {
                    case "Booked":
                        bookedLessons.add(booking.getBookingId());
                        break;
                    case "Cancelled":
                        cancelledLessons.add(booking.getBookingId());
                        break;
                    case "Attended":
                        attendedLessons.add(booking.getBookingId());
                        break;
                    // Handle other status types if necessary
                }
            }

            // Step 5: Update learner data with sorted bookings
            learnerController.updateLearnerData(learnerId, bookedLessons, attendedLessons, cancelledLessons);
        }
    }
}
