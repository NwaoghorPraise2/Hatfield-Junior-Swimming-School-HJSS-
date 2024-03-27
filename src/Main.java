import utils.AppManager;
import utils.LearnDataGenerator;
import view.HJSSView;

public class Main {
    public static void main(String[] args) {
        LearnDataGenerator dataGenerator = new LearnDataGenerator();
        AppManager appManager = new AppManager();
        appManager.addLesson();
        System.out.println("Initialising System");
        System.out.println("Loading data");
        dataGenerator.generateDummyData(20);
        System.out.println("Data loaded");
        System.out.println("System initialised");
        new HJSSView();
    }
}
