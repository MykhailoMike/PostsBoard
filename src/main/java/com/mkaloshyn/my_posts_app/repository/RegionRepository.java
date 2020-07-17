package main.java.com.mkaloshyn.my_posts_app.repository;

import main.java.com.mkaloshyn.my_posts_app.model.Region;

public interface RegionRepository extends GenericRepository<Region, Long> {

    Region create (String regionName);
}
