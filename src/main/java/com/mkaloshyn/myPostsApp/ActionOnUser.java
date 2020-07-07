package main.java.com.mkaloshyn.myPostsApp;

import main.java.com.mkaloshyn.myPostsApp.view.UserView;

import java.util.Scanner;

public class ActionOnUser extends DoingActionOnEntity {

    private UserView userView = new UserView(getScanner());

    public ActionOnUser(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void implementNeededAction(int choice) {
        switch (choice) {
            case 1:
                userView.getById();
                break;
            case 2:
                userView.getAll();
                break;
            case 3:
                userView.save();
                break;
            case 4:
                userView.update();
                break;
            case 5:
                userView.deleteById();
                break;
            case 6:
                break;
            default:
                System.out.println("Please choose in range 1 to 6 strictly");
        }
    }
}
