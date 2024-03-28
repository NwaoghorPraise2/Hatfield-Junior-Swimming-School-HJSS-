import database.LessonDB;
import utils.AppManager;
import utils.LearnDataGenerator;
import utils.LessonDataGenerator;
import view.HJSSView;

public class Main {
    public static void main(String[] args) {
        LearnDataGenerator dataGenerator = new LearnDataGenerator();
        LessonDataGenerator lessonDataGenerator = new LessonDataGenerator();
        LessonDB lessonDB = new LessonDB();
        System.out.println("Initialising System");
        System.out.println("Loading data");
        dataGenerator.generateDummyData(20);
        lessonDataGenerator.generateLessonData();
        System.out.println("Data loaded");
        int a = lessonDB.getLessonCount();
        System.out.println("Total:" + a);
        System.out.println("System initialised");
        new HJSSView();
    }
}
