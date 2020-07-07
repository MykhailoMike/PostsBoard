package main.java.com.mkaloshyn.myPostsApp;

import main.java.com.mkaloshyn.myPostsApp.view.PostView;

import java.util.Scanner;

public class ActionOnPost extends DoingActionOnEntity {

    private PostView postView = new PostView(getScanner());

    public ActionOnPost(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void implementNeededAction(int choice) {

        switch (choice) {
            case 1:
                postView.getById();
                break;
            case 2:
                postView.getAll();
                break;
            case 3:
                postView.save();
                break;
            case 4:
                postView.update();
                break;
            case 5:
                postView.deleteById();
                break;
            case 6:
                break;
            default:
                System.out.println("Please choose in range 1 to 6 strictly");
        }
    }
}
