package utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnerInputValidator {

//    private static boolean isStringEmptyOrNull(String value) {
//        return value == null || value.isEmpty();
//    }

        public static boolean isValidName(String name) {
            return name != null && !name.isEmpty();
        }

        public static boolean isValidGender(String gender) {
            if (gender == null || gender.isEmpty() ) {
                return false;
            }

            String unifyString = gender.toLowerCase();
            return gender.equals("male") || gender.equals("female") || gender.equals("others");
        }

        public static boolean isValidDateOfBirth(LocalDate dateOfBirth) {
            return dateOfBirth != null && !dateOfBirth.isAfter(LocalDate.now());
        }

        public static boolean isValidUKPhoneNumber(String emergencyContact) {
            if (emergencyContact == null || emergencyContact.isEmpty() ) {
                return false;
            }

            String regexPattern = "^((\\+44)|(0))(7\\d{9})$";
            Pattern ukPhoneNumberPattern = Pattern.compile(regexPattern);
            Matcher matcher = ukPhoneNumberPattern.matcher(emergencyContact);
            return matcher.matches();
        }

        public static boolean isValidGradeLevel(int gradeLevel) {
            return gradeLevel >= 1 && gradeLevel <= 5;
        }
    }




    public Learner(String name, String gender, LocalDate dateOfBirth, String emergencyContact, int currentGradeLevel) {
        if (!LearnerValidator.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (!LearnerValidator.isValidGender(gender)) {
            throw new IllegalArgumentException("Invalid gender");
        }
        if (!LearnerValidator.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
        if (!LearnerValidator.isValidEmergencyContact(emergencyContact)) {
            throw new IllegalArgumentException("Invalid emergency contact");
        }
        if (!LearnerValidator.isValidGradeLevel(currentGradeLevel)) {
            throw new IllegalArgumentException("Invalid grade level");
        }
}
