package utils.reportHandlers;

import controller.BookingController;
import controller.LearnerController;
import models.Booking;
import models.Learner;
import models.Lesson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the generation of learner reports.
 */
public class LearnerReportHandler {
    private final BookingController bookingController;
    private final LearnerController learnerController;

    /**
     * Constructs a new LearnerReportHandler object.
     */
    public LearnerReportHandler() {
        this.bookingController = new BookingController();
        this.learnerController = LearnerController.getInstance();
    }

    /**
     * Generates a learner report for the specified month.
     * @param month The month for which the report is generated (1-12).
     */
    public void generateLearnerReport(int month) {
        int currentYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(currentYear, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        LocalDate fourWeeksBack = startDate.minusWeeks(4);

        List<Booking> bookings = bookingController.getAllBookings();
        List<Learner> learners = learnerController.getAllLearners();
        Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus = groupBookingsByLearnerAndStatus(bookings, fourWeeksBack, endDate);

        printReportHeader();
        printReportBody(learners, learnerBookingsByStatus);
    }

    private Map<String, Map<String, List<Lesson>>> groupBookingsByLearnerAndStatus(List<Booking> bookings, LocalDate fourWeeksBack, LocalDate endDate) {
        Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus = new HashMap<>();

        for (Booking booking : bookings) {
            LocalDate bookingDate = booking.getLesson().getDate();
            if (bookingDate.isAfter(fourWeeksBack) && bookingDate.isBefore(endDate)) {
                String learnerId = booking.getLearnerId();
                Lesson lesson = booking.getLesson();
                String status = booking.getStatus();

                learnerBookingsByStatus.putIfAbsent(learnerId, new HashMap<>());
                Map<String, List<Lesson>> statusMap = learnerBookingsByStatus.get(learnerId);

                statusMap.putIfAbsent(status, new ArrayList<>());
                List<Lesson> lessons = statusMap.get(status);

                lessons.add(lesson);
            }
        }
        return learnerBookingsByStatus;
    }

    private void printReportHeader() {
        System.out.println("|HJSS Learners Report.");
        System.out.println("-----------------------------------------------------------------");
    }

    private void printReportBody(List<Learner> learners, Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus) {
        for (Learner learner : learners) {
            String learnerId = learner.getId();
            Map<String, List<Lesson>> statusMap = learnerBookingsByStatus.getOrDefault(learnerId, new HashMap<>());

            System.out.println("LearnerId: " + learnerId);

            printLessonDetails(statusMap);
        }
    }

    private void printLessonDetails(Map<String, List<Lesson>> statusMap) {
        int totalBooked = 0;
        int totalAttended = 0;
        int totalCancelled = 0;

        for (Map.Entry<String, List<Lesson>> statusEntry : statusMap.entrySet()) {
            String status = statusEntry.getKey();
            List<Lesson> lessons = statusEntry.getValue();

            System.out.println("\t\t\t" + "Status: " + status);

            for (Lesson lesson : lessons) {
                System.out.println("\t\t\tLessonRef: " + lesson.getLessonRef());
                System.out.println("\t\t\tDate: " + lesson.getDate());
                System.out.println("\t\t\tCoach Name: " + lesson.getCoach());
                System.out.println("\t\t\tGradeLevel: " + lesson.getGradeLevel());
                System.out.println("\t\t\t___________________________________________________");
            }

            // Update total bookings by status
            int[] updatedCounts = updateTotalBookingsByStatus(status, lessons, totalBooked, totalAttended, totalCancelled);
            totalBooked = updatedCounts[0];
            totalAttended = updatedCounts[1];
            totalCancelled = updatedCounts[2];
        }

        // Print summary and grand total
        printSummaryAndGrandTotal(totalBooked, totalAttended, totalCancelled);
    }

    private int[] updateTotalBookingsByStatus(String status, List<Lesson> lessons, int totalBooked, int totalAttended, int totalCancelled) {
        int[] updatedCounts = {totalBooked, totalAttended, totalCancelled};
        if (status.equals("Booked")) {
            updatedCounts[0] += lessons.size();
        } else if (status.equals("Attended")) {
            updatedCounts[1] += lessons.size();
        } else if (status.equals("Cancelled")) {
            updatedCounts[2] += lessons.size();
        }
        return updatedCounts;
    }

    private void printSummaryAndGrandTotal(int totalBooked, int totalAttended, int totalCancelled) {
        System.out.println("\t\t\tSummary:");
        System.out.println("\t\t\t- Booked Bookings: " + totalBooked);
        System.out.println("\t\t\t- Attended Bookings: " + totalAttended);
        System.out.println("\t\t\t- Cancelled Bookings: " + totalCancelled);

        int grandTotal = totalBooked + totalAttended + totalCancelled;
        System.out.println("\t\t\tGrand Total: " + grandTotal);
        System.out.println();
    }
}
