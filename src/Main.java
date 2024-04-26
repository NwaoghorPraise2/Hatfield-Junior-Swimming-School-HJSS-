import database.LessonDB;
import appManager.AppManager;
import utils.dataGenerators.BookingsDataGenerator;
import utils.dataGenerators.LearnDataGenerator;
import utils.dataGenerators.LessonDataGenerator;
import utils.dataGenerators.ReviewDataGenerator;
import view.HJSSView;

public class Main {
    public static void main(String[] args) {
        AppManager appManager = AppManager.getInstance();
        appManager.startApp();
    }
}
