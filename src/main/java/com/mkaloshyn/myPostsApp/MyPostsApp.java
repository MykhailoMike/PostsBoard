package main.java.com.mkaloshyn.myPostsApp;

import java.util.Scanner;

public class MyPostsApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ActionOnPost actionOnPost = new ActionOnPost(scanner);
        ActionOnUser actionOnUser = new ActionOnUser(scanner);
        ActionOnRegion actionOnRegion = new ActionOnRegion(scanner);

        int chosenEntity;
        do {
            System.out.println("__________________________________\n" +
                    "Please choose the entity to do operations on:\n" +
                    "1 - User;\n" +
                    "2 - Post;\n" +
                    "3 - Region;\n" +
                    "4 - terminate.");
            chosenEntity = scanner.nextInt();
            switch (chosenEntity) {
                case 1:
                    actionOnUser.askForChoiceAndDo();
                    break;
                case 2:
                    actionOnPost.askForChoiceAndDo();
                    break;
                case 3:
                    actionOnRegion.askForChoiceAndDo();
                    break;
                case 4:
                    System.out.println("Program has been terminated.");
                    return;
                default:
                    System.out.println("Please choose in range 1 to 4 strictly");
            }
        } while (chosenEntity != 4);
    }
}
