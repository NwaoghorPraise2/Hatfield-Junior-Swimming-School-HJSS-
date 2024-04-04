package middlewares;

public class BookingDataInputValidator {
    public boolean isStringValid(String string) {
        return string != null && !string.trim().isEmpty();
    }
}
