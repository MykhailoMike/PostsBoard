package main.java.com.mkaloshyn.my_posts_app.choosing_action_on_entity;

import main.java.com.mkaloshyn.my_posts_app.view.RegionView;

import java.util.Scanner;

public class ActionOnRegion extends DoingActionOnEntity {

    private RegionView regionView = new RegionView(getScanner());

    public ActionOnRegion(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void implementNeededAction(int choice) {

        switch (choice) {
            case 1:
                regionView.getById();
                break;
            case 2:
                regionView.getAll();
                break;
            case 3:
                regionView.save();
                break;
            case 4:
                regionView.update();
                break;
            case 5:
                regionView.deleteById();
                break;
            case 6:
                break;
            default:
                System.out.println("Please choose in range 1 to 6 strictly");
        }

    }
}
