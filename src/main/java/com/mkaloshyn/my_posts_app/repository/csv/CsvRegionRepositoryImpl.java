package main.java.com.mkaloshyn.my_posts_app.repository.csv;

import main.java.com.mkaloshyn.my_posts_app.model.Region;
import main.java.com.mkaloshyn.my_posts_app.repository.RegionRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvRegionRepositoryImpl implements RegionRepository {

    private static final String REGIONS_DB = "src/main/resources/regions.csv";
    private CsvUtil csvUtil = CsvUtil.getCsvUtil();

    @Override
    public Region getById(Long id) {
        String str = csvUtil.defineMatchingString(id, REGIONS_DB);
        if (str != null) {
            return getRegion(str);
        } else {
            return null;
        }
    }

    private Region getRegion(String str) {
        String[] split = str.split(";");
        Long id = Long.valueOf(split[0]);
        String regionName = split[1];
        return new Region(id, regionName);
    }

    @Override
    public List<Region> getAll() {
        return getRegionsAsList();
    }

    private List<Region> getRegionsAsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REGIONS_DB))) {
            String str;
            List<Region> regions = new ArrayList<>();
            while (true) {
                if ((str = reader.readLine()) == null) {
                    return regions;
                }
                if (!str.isEmpty() && !str.startsWith("id")) {
                    Region region = getRegion(str);
                    regions.add(region);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Region save(Region region) {
        if (region != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(REGIONS_DB, true))) {
                writer.write(csvUtil.getStringFromRegion(region));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getById(region != null ? region.getId() : null);
    }

    @Override
    public Region update(Region region) {
        csvUtil.updateEntry(region, REGIONS_DB);
        return getById(region.getId());
    }

    @Override
    public Region deleteById(Long id) {
        Region regionToDelete = getById(id);
        csvUtil.deleteEntryById(id, REGIONS_DB);
        return regionToDelete;
    }

    @Override
    public Region create(String regionName) {
        long id = csvUtil.defineNextId(Objects.requireNonNull(getRegionsAsList()));
        return new Region(id, regionName);
    }
}
