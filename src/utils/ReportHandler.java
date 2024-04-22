//package utils;
//
//import controller.BookingController;
//import controller.LearnerController;
//import controller.ReviewController;
//import models.Booking;
//import models.Lesson;
//import models.Review;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ReportHandler {
//    private final BookingController bookingController;
//    private final LearnerController learnerController;
//    private final ReviewController reviewController;
//    private final String[] COACHES = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Christopher", "Jennifer"};
//    private int currentIndex;
//
//    public ReportHandler() {
//        this.bookingController = new BookingController();
//        this.learnerController = LearnerController.getInstance();
//        this.reviewController = new ReviewController();
//        this.currentIndex = 0;
//    }
//
//    public void generateLearnerReport(int month) {
//        int currentYear = LocalDate.now().getYear();
//        LocalDate startDate = LocalDate.of(currentYear, month, 1);
//        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
//        LocalDate fourWeeksBack = startDate.minusWeeks(4);
//
//        List<Booking> bookings = bookingController.getAllBookings();
//        Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus = groupBookingsByLearnerAndStatus(bookings, fourWeeksBack, endDate);
//
//        printReportHeader();
//        printReportBody(learnerBookingsByStatus);
//    }
//
//    private Map<String, Map<String, List<Lesson>>> groupBookingsByLearnerAndStatus(List<Booking> bookings, LocalDate fourWeeksBack, LocalDate endDate) {
//        Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus = new HashMap<>();
//
//        for (Booking booking : bookings) {
//            LocalDate bookingDate = booking.getLesson().getDate();
//            if (bookingDate.isAfter(fourWeeksBack) && bookingDate.isBefore(endDate)) {
//                String learnerId = booking.getLearnerId();
//                Lesson lesson = booking.getLesson();
//                String status = booking.getStatus();
//
//                learnerBookingsByStatus.putIfAbsent(learnerId, new HashMap<>());
//                Map<String, List<Lesson>> statusMap = learnerBookingsByStatus.get(learnerId);
//
//                statusMap.putIfAbsent(status, new ArrayList<>());
//                List<Lesson> lessons = statusMap.get(status);
//
//                lessons.add(lesson);
//            }
//        }
//        return learnerBookingsByStatus;
//    }
//
//    private void printReportHeader() {
//        System.out.println("|HJSS Learners Report.");
//        System.out.println("-----------------------------------------------------------------");
//    }
//
//    private void printReportBody(Map<String, Map<String, List<Lesson>>> learnerBookingsByStatus) {
//        for (Map.Entry<String, Map<String, List<Lesson>>> entry : learnerBookingsByStatus.entrySet()) {
//            String learnerId = entry.getKey();
//            Map<String, List<Lesson>> statusMap = entry.getValue();
//
//            System.out.println("LearnerId: " + learnerId);
//
//            printLessonDetails(statusMap);
//        }
//    }
//
//    private void printLessonDetails(Map<String, List<Lesson>> statusMap) {
//        int totalBooked = 0;
//        int totalAttended = 0;
//        int totalCancelled = 0;
//
//        for (Map.Entry<String, List<Lesson>> statusEntry : statusMap.entrySet()) {
//            String status = statusEntry.getKey();
//            List<Lesson> lessons = statusEntry.getValue();
//
//            System.out.println("\t\t\t" + "Status: " + status);
//
//            for (Lesson lesson : lessons) {
//                System.out.println("\t\t\tLessonRef: " + lesson.getLessonRef());
//                System.out.println("\t\t\tDate: " + lesson.getDate());
//                System.out.println("\t\t\tCoach Name: " + lesson.getCoach());
//                System.out.println("\t\t\tGradeLevel: " + lesson.getGradeLevel());
//                System.out.println("\t\t\t___________________________________________________");
//            }
//
//            updateTotalBookingsByStatus(status, lessons, totalBooked, totalAttended, totalCancelled);
//        }
//
//        printSummaryAndGrandTotal(totalBooked, totalAttended, totalCancelled);
//    }
//
//    private void updateTotalBookingsByStatus(String status, List<Lesson> lessons, int totalBooked, int totalAttended, int totalCancelled) {
//        if (status.equals("Booked")) {
//            totalBooked += lessons.size();
//        } else if (status.equals("Attended")) {
//            totalAttended += lessons.size();
//        } else if (status.equals("Cancelled")) {
//            totalCancelled += lessons.size();
//        }
//    }
//
//    private void printSummaryAndGrandTotal(int totalBooked, int totalAttended, int totalCancelled) {
//        System.out.println("\t\t\tSummary:");
//        System.out.println("\t\t\t- Booked Bookings: " + totalBooked);
//        System.out.println("\t\t\t- Attended Bookings: " + totalAttended);
//        System.out.println("\t\t\t- Cancelled Bookings: " + totalCancelled);
//
//        int grandTotal = totalBooked + totalAttended + totalCancelled;
//        System.out.println("\t\t\tGrand Total: " + grandTotal);
//        System.out.println();
//    }
//
//
////    public void generateCoachReport(int month) {
////        // Check the same criteria as in the student report
////        LocalDate startDate = calculateStartDate(month);
////        LocalDate endDate = calculateEndDate(startDate);
////        List<Review> reviews = getReviewsForMonth(startDate, endDate);
////        Map<String, Double> coachRatings = calculateCoachRatings(reviews);
////        displayCoachReport(coachRatings);
////    }
////
////    private LocalDate calculateStartDate(int month) {
////        int currentYear = LocalDate.now().getYear();
////        return LocalDate.of(currentYear, month, 1);
////    }
////
////    private LocalDate calculateEndDate(LocalDate startDate) {
////        return startDate.plusMonths(1).minusDays(1);
////    }
////
////    private List<Review> getReviewsForMonth(LocalDate startDate, LocalDate endDate) {
////        String coachName = generateCoachName();
////        return reviewController.getReviewsByCoachNameAndDate(coachName, startDate, endDate);
////    }
////
////    private Map<String, Double> calculateCoachRatings(List<Review> reviews) {
////        Map<String, Double> coachRatings = new HashMap<>();
////
////        for (Review review : reviews) {
////            String coachName = review.getLesson().getCoach();
////            double rating = review.getRating();
////
////            if (coachRatings.containsKey(coachName)) {
////                double currentRating = coachRatings.get(coachName);
////                coachRatings.put(coachName, currentRating + rating);
////            } else {
////                coachRatings.put(coachName, rating);
////            }
////        }
////
////        coachRatings.replaceAll((k, v) -> v / reviews.size()); // Calculate average rating
////
////        return coachRatings;
////    }
////
////    private void displayCoachReport(Map<String, Double> coachRatings) {
////        System.out.println("Coach Ratings Report:");
////        System.out.println("----------------------");
////
////        for (Map.Entry<String, Double> entry : coachRatings.entrySet()) {
////            String coachName = entry.getKey();
////            double averageRating = entry.getValue();
////
////            System.out.println("Coach Name: " + coachName);
////            System.out.println("Average Rating: " + averageRating);
////            System.out.println();
////        }
////    }
////
////    public String generateCoachName() {
////        if (currentIndex >= COACHES.length) {
////            currentIndex = 0; // Reset index if it reaches the end of the array
////        }
////        return COACHES[currentIndex++];
////    }
//
//
//
//}