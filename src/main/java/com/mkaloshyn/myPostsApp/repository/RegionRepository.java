package main.java.com.mkaloshyn.myPostsApp.repository;

import main.java.com.mkaloshyn.myPostsApp.model.Region;

public interface RegionRepository extends GenericRepository<Region, Long> {

    Region create (String regionName);
}
