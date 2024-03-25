package utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnerInputValidator {
    public void isValidName(String name) {

        //make sure no number passed
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public void isValidGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            throw new IllegalArgumentException("Invalid gender");
        }

        String unifyString = gender.toLowerCase();
        if (!unifyString.equals("male") && !unifyString.equals("female") && !unifyString.equals("others")) {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public void isValidDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date of birth");
        }

        int age = LocalDate.now().getYear() - dateOfBirth.getYear();
        if (age <= 4 || age >= 11) {
            throw new IllegalArgumentException("Your age is restricted from this service😇.");
        }

    }

    public void isValidUKPhoneNumber(String emergencyContact) {
        if (emergencyContact == null || emergencyContact.isEmpty()) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        String regexPattern = "^((\\+44)|(0))(7\\d{9})$";
        Pattern ukPhoneNumberPattern = Pattern.compile(regexPattern);
        Matcher matcher = ukPhoneNumberPattern.matcher(emergencyContact);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The number you entered is not a valid Uk number");
        }
    }
    public void isValidGradeLevel(int gradeLevel) {
        if (gradeLevel < 1 || gradeLevel > 5) {
            throw new IllegalArgumentException("Invalid grade level: We only have Grade(1 to 5).");
        }
    }
}
