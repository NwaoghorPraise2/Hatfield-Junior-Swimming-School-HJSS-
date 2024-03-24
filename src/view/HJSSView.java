package view;

import controller.LearnerController;

import java.time.LocalDate;

public class HJSSView {
    private final LearnerController learnerController = new LearnerController();

    public void registerUser(){
        System.out.println("Register guy");
        LocalDate date = LocalDate.now();
        learnerController.registerLearner("Ghhg", "Praise", date, "+23454464343", 4 );
        System.out.println("User Added");
    }
}