package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Learner {
    private String id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String emergencyContact;
    private int currentGradeLevel;

    private static int counter = 0;
    
    public Learner() {
    }

    public Learner(String name, String gender , LocalDate dateOfBirth, String emergencyContact, int currentGradeLevel) {
        id = generateID(dateOfBirth);
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
    }

    public String generateID(LocalDate dateOfBirth) {
        String formattedDateOfBirth = dateOfBirth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uniqueID = "HJSS-" + formattedDateOfBirth + "-" + String.format("%03d", counter++);
        return uniqueID;
    }

    private int calculateLearnerAge() {
        return 2;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    @Override
    public String toString() {
        return "Learners{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", currentGradeLevel=" + currentGradeLevel +
                '}';
    }
}
