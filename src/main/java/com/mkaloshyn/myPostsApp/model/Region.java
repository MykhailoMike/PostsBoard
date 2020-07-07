package main.java.com.mkaloshyn.myPostsApp.model;

public class Region extends BasicEntry {

    private String name;

    public Region(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
