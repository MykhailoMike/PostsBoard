package main.java.com.mkaloshyn.my_posts_app.choosing_action_on_entity;

import java.util.Scanner;

public abstract class DoingActionOnEntity {

    private Scanner scanner;

    public DoingActionOnEntity(Scanner scanner) {
        this.scanner = scanner;
    }

    public void askForChoiceAndDo() {
        int choice;
        do {
            System.out.println("__________________________________\n" +
                    "Please choose the action needed:\n" +
                    "1 - read by ID;\n" +
                    "2 - read all;\n" +
                    "3 - add;\n" +
                    "4 - update;\n" +
                    "5 - delete by ID;\n" +
                    "6 - back to the main menu.");
            choice = scanner.nextInt();
            implementNeededAction(choice);
        } while (choice != 6);
    }

    public abstract void implementNeededAction(int choice);

    public Scanner getScanner() {
        return scanner;
    }
}




