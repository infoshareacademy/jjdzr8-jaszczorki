package com.isa.control;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.isa.entity.appConstants.AppConstants.*;

public abstract class SubMenuNavigator {

    void subMenuActions(){

        boolean inProgress = true;
        do{
            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            try{
                choice = scanner.nextInt();
                if (choice == 1) {
                    acceptAction();
                } else if (choice == 2) {
                    goBackToMenu();
                    inProgress = false;
                } else {
                    System.out.println(ENTERED_WRONG_NUMBER);
                }
            } catch (InputMismatchException e) {
                System.out.println(ENTERED_WRONG_SIGNS);
            }
        } while(inProgress);
    }
    void acceptAction(){
        System.out.println(ACCEPTED);

    }
    void goBackToMenu(){
        System.out.println(BACK_TO_MENU);
        MenuNavigator.printMenu();
        MenuNavigator.chooseMenuOptions();
    }
}
