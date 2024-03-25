package view;

import controller.LearnerController;

import java.time.LocalDate;

public class HJSSView {
    private final LearnerController learnerController = new LearnerController();

    public void registerUser(){
        System.out.println("Register guy");
        LocalDate dob = LocalDate.parse("1998-04-12");

//        LocalDate date = LocalDate.now();
        learnerController.registerLearner("Ghhg",  dob,  "Male", "+23454464343", 5 );
        System.out.println("User Added");
    }
}