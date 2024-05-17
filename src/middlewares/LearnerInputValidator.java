package middlewares;

import java.time.LocalDate;

public class LearnerInputValidator {

    /**
     * Validates the learner's name.
     * @param name The name to validate.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public void isValidName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    /**
     * Validates the learner's gender.
     * @param gender The gender to validate.
     * @throws IllegalArgumentException If the gender is null, empty, or not one of "male", "female", or "others".
     */
    public void isValidGender(String gender) {
        gender.toLowerCase();
        if (gender == null || gender.isEmpty() || !gender.matches("^(male|female|others)$")) {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    /**
     * Validates the learner's date of birth.
     * @param dateOfBirth The date of birth to validate.
     * @throws IllegalArgumentException If the date of birth is null, in the future, or if age is restricted.
     */
    public void isValidDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now().minusYears(4)) || dateOfBirth.isBefore(LocalDate.now().minusYears(10))) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
    }

    /**
     * Validates the UK phone number.
     * @param emergencyContact The UK phone number to validate.
     * @throws IllegalArgumentException If the phone number is null, empty, or not a valid UK number.
     */
    public void isValidUKPhoneNumber(String emergencyContact) {
        if (emergencyContact == null || emergencyContact.isEmpty() || !emergencyContact.matches("^((\\+44)|(0))(7\\d{9})$")) {
            throw new IllegalArgumentException("Invalid UK phone number");
        }
    }

    /**
     * Validates the learner's grade level.
     * @param gradeLevel The grade level to validate.
     * @throws IllegalArgumentException If the grade level is not between 1 and 5 (inclusive).
     */
    public void isValidGradeLevel(int gradeLevel) {
        if (gradeLevel < 1 || gradeLevel > 5) {
            throw new IllegalArgumentException("Invalid grade level: We only have Grade(1 to 5).");
        }
    }
}
