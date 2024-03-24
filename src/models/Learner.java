package models;

import java.time.LocalDate;

public class Learner {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String emergencyContact;
    private int currentGradeLevel;

    public Learner(String id, String name, LocalDate dateOfBirth, String emergencyContact, int currentGradeLevel) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
    }

    public String getId() {
        return id;
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
}
