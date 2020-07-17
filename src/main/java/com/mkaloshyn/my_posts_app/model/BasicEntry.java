package main.java.com.mkaloshyn.my_posts_app.model;

public abstract class BasicEntry {

    private Long id;

    public BasicEntry() {}

    public BasicEntry(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
