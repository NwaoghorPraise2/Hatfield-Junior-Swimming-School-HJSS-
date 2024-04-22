import database.LessonDB;
import appManager.AppManager;
import utils.dataGenerators.BookingsDataGenerator;
import utils.dataGenerators.LearnDataGenerator;
import utils.dataGenerators.LessonDataGenerator;
import utils.dataGenerators.ReviewDataGenerator;
import view.HJSSView;

public class Main {
    public static void main(String[] args) {
        BookingsDataGenerator bookingDataGenerator = new BookingsDataGenerator();
        LearnDataGenerator dataGenerator = new LearnDataGenerator();
        LessonDataGenerator lessonDataGenerator = new LessonDataGenerator();
        ReviewDataGenerator reviewDataGenerator = new ReviewDataGenerator();
        AppManager appManager = AppManager.getInstance();
        LessonDB lessonDB = new LessonDB();
        System.out.println("Initialising System");
        System.out.println("Loading data");
        dataGenerator.generateDummyData(20);
        lessonDataGenerator.generateLessonData();
        lessonDataGenerator.updateLessonData();
        bookingDataGenerator.generateBookings();
        dataGenerator.updateLearnerData();
        reviewDataGenerator.generateReviewsForAttendedBookings();
        System.out.println("Data loaded");
        int a = lessonDB.getLessonCount();
        System.out.println("Total:" + a);
        System.out.println("System initialised");
        new HJSSView();
    }
}
