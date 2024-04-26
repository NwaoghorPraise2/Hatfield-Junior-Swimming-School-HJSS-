package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a learner enrolled in the system.
 */
public class Learner {
    private String id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String emergencyContact;
    private int currentGradeLevel;
    private List<String> bookedLessons;
    private List<String> attendedLessons;
    private List<String> cancelledLessons;

    private static int counter = 0;

    /**
     * Constructs a Learner object with the given attributes.
     * @param name The name of the learner.
     * @param gender The gender of the learner.
     * @param dateOfBirth The date of birth of the learner.
     * @param emergencyContact The emergency contact of the learner.
     * @param currentGradeLevel The current grade level of the learner.
     */
    public Learner(String name, String gender , LocalDate dateOfBirth, String emergencyContact, int currentGradeLevel) {
        id = generateID(dateOfBirth);
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
        this.bookedLessons = new ArrayList<>();
        this.attendedLessons = new ArrayList<>();
        this.cancelledLessons = new ArrayList<>();
    }

    // Empty constructor for data generation purpose only
    public Learner() {
    }

    /**
     * Generates a unique ID for the learner based on the date of birth and a counter.
     * @param dateOfBirth The date of birth of the learner.
     * @return The generated learner ID.
     */
    public String generateID(LocalDate dateOfBirth) {
        String formattedDateOfBirth = dateOfBirth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uniqueID = "HJSS-" + formattedDateOfBirth + "-" + String.format("%03d", counter++);
        return uniqueID;
    }

    public void moveLearnerToNextGradeLevel() {
        if (currentGradeLevel < 5) {
            currentGradeLevel++;
        }
    }

    /**
     * Books a lesson for the learner.
     * @param lessonId The ID of the lesson to be booked.
     */
    public void addBookedLesson(String lessonId) {
        bookedLessons.add(lessonId);
    }

    /**
     * Marks a lesson as attended by the learner.
     * @param lessonId The ID of the lesson attended.
     */
    public void addAttendLesson(String lessonId) {
        if (bookedLessons.contains(lessonId)) {
            attendedLessons.add(lessonId);
            bookedLessons.remove(lessonId); // Remove from booked lessons since it's attended
        } else {
            System.out.println("Error: This lesson was not booked.");
        }
    }

    /**
     * Cancels a previously booked lesson.
     * @param lessonId The ID of the lesson to be cancelled.
     */
    public void cancelLesson(String lessonId) {
        if (bookedLessons.contains(lessonId)) {
            cancelledLessons.add(lessonId);
            bookedLessons.remove(lessonId); // Remove from booked lessons since it's cancelled
        } else {
            System.out.println("Error: This lesson was not booked.");
        }
    }


    /**
     * Gets the ID of the learner.
     * @return The ID of the learner.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the learner.
     * @param id The ID of the learner.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the learner.
     * @return The name of the learner.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the learner.
     * @param name The name of the learner.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date of birth of the learner.
     * @return The date of birth of the learner.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the learner.
     * @param dateOfBirth The date of birth of the learner.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the gender of the learner.
     * @return The gender of the learner.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the learner.
     * @param gender The gender of the learner.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the emergency contact of the learner.
     * @return The emergency contact of the learner.
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Sets the emergency contact of the learner.
     * @param emergencyContact The emergency contact of the learner.
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * Gets the current grade level of the learner.
     * @return The current grade level of the learner.
     */
    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    /**
     * Sets the current grade level of the learner.
     * @param currentGradeLevel The current grade level of the learner.
     */
    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    /**
     * Gets the list of lesson IDs booked by the learner.
     * @return The list of lesson IDs booked by the learner.
     */
    public List<String> getBookedLessons() {
        return bookedLessons;
    }

    /**
     * Sets the list of lesson IDs booked by the learner.
     * @param bookedLessons The list of lesson IDs booked by the learner.
     */
    public void setBookedLessons(List<String> bookedLessons) {
        this.bookedLessons = bookedLessons;
    }

    /**
     * Gets the list of lesson IDs attended by the learner.
     * @return The list of lesson IDs attended by the learner.
     */
    public List<String> getAttendedLessons() {
        return attendedLessons;
    }

    /**
     * Sets the list of lesson IDs attended by the learner.
     * @param attendedLessons The list of lesson IDs attended by the learner.
     */
    public void setAttendedLessons(List<String> attendedLessons) {
        this.attendedLessons = attendedLessons;
    }

    /**
     * Gets the list of lesson IDs cancelled by the learner.
     * @return The list of lesson IDs cancelled by the learner.
     */
    public List<String> getCancelledLessons() {
        return cancelledLessons;
    }

    /**
     * Sets the list of lesson IDs cancelled by the learner.
     * @param cancelledLessons The list of lesson IDs cancelled by the learner.
     */
    public void setCancelledLessons(List<String> cancelledLessons) {
        this.cancelledLessons = cancelledLessons;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", currentGradeLevel=" + currentGradeLevel +
                ", bookedLessons=" + bookedLessons +
                ", attendedLessons=" + attendedLessons +
                ", cancelledLessons=" + cancelledLessons +
                '}';
    }
}
