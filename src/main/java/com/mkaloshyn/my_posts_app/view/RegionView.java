package main.java.com.mkaloshyn.my_posts_app.view;

import main.java.com.mkaloshyn.my_posts_app.controller.RegionController;
import main.java.com.mkaloshyn.my_posts_app.model.Region;

import java.util.List;
import java.util.Scanner;

import static main.java.com.mkaloshyn.my_posts_app.view.ViewUtil.*;

public class RegionView {

    private RegionController regionController;
    private Scanner scanner;

    public RegionView(Scanner scanner) {
        this.scanner = scanner;
        regionController = new RegionController();
    }

    public Region getById() {
        System.out.println(REGION_ID_REQUEST);
        long id = (long) scanner.nextInt();
        Region region = regionController.getById(id);
        if (region == null) {
            System.out.println(NO_REGIONS_WITH_ID_MSG);
        } else {
            System.out.println(region);
        }
        return region;
    }

    public List<Region> getAll() {
        List<Region> regionList = regionController.getAll();
        if (!regionList.isEmpty()) {
            System.out.println(REGION_HEADER);
            regionList.forEach(System.out::println);
        } else {
            System.out.println(NO_REGIONS_MSG);
        }
        return regionList;
    }

    public Region save() {
        scanner.nextLine();
        System.out.println(REGION_NAME_REQUEST);
        String regionName = scanner.nextLine();
        Region region = regionController.save(regionName);
        if (region != null) {
            System.out.println(SUCCESSFULLY_ADDED_REGION_MSG);
            System.out.println(region);
        }
        return region;
    }

    public void deleteById() {
        System.out.println(REGION_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (regionController.getById(id) == null) {
            System.out.println(NO_REGIONS_WITH_ID_MSG);
        } else {
            Region deletedRegion = regionController.deleteById(id);
            System.out.println(SUCCESSFULLY_DELETED_REGION_MSG + deletedRegion);
        }
    }

    public Region update() {
        System.out.println(REGION_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (regionController.getById(id) == null) {
            System.out.println(NO_REGIONS_WITH_ID_MSG);
            return null;
        } else {
            scanner.nextLine();
            System.out.println(REGION_NAME_REQUEST);
            String regionName = scanner.nextLine();
            Region region = new Region(id, regionName);
            region = regionController.update(region);
            System.out.println(UPDATED_REGION + region);
            return region;
        }
    }
}


