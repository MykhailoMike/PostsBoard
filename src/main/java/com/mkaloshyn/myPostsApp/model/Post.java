package main.java.com.mkaloshyn.myPostsApp.model;

import java.time.LocalDateTime;

public class Post extends BasicEntry {

    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Region region;

    private Post() {
        created = LocalDateTime.now();
    }

    public static PostBuilder base() {
        return new PostBuilder();
    }

    public static class PostBuilder {

        private Post post = new Post();

        private PostBuilder() {
        }

        public PostBuilder addId(Long id) {
            post.setId(id);
            return this;
        }

        public PostBuilder addContent(String content) {
            post.content = content;
            return this;
        }

        public PostBuilder addCreated(LocalDateTime created) {
            post.created = created;
            return this;
        }

        public PostBuilder addUpdated(LocalDateTime updated) {
            post.updated = updated;
            return this;
        }

        public PostBuilder addRegion(Region region) {
            post.region = region;
            return this;
        }

        public Post build() {
            return post;
        }
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {

        return updated != null ? "Post{" +
                "id = " + getId() +
                ", content: " + content +
                ", created=" + created +
                ", updated=" + updated +
                ", region=" + region +
                "} " : "Post{" +
                "id = " + getId() +
                ", content: " + content +
                ", created=" + created +
                ", region=" + region +
                "} ";
    }
}
