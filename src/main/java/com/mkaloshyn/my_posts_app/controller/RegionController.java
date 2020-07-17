package main.java.com.mkaloshyn.my_posts_app.controller;

import main.java.com.mkaloshyn.my_posts_app.model.Region;
import main.java.com.mkaloshyn.my_posts_app.repository.RegionRepository;
import main.java.com.mkaloshyn.my_posts_app.repository.csv.CsvRegionRepositoryImpl;

import java.util.List;

public class RegionController {

    private RegionRepository regionRepository;

    public RegionController() {
        regionRepository = new CsvRegionRepositoryImpl();
    }

    public Region getById(long id) {
        return regionRepository.getById(id);
    }


    public List<Region> getAll() {
        return regionRepository.getAll();
    }


    public Region save(String regionName) {
        Region region = regionRepository.create(regionName);
        return regionRepository.save(region);
    }

    public Region deleteById(long id) {
        return regionRepository.deleteById(id);
    }

    public Region update(Region region) {
        return regionRepository.update(region);
    }
}
