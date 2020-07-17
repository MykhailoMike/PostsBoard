package main.java.com.mkaloshyn.my_posts_app.repository;

import main.java.com.mkaloshyn.my_posts_app.model.Post;

public interface PostRepository extends GenericRepository<Post, Long> {

    Post create(String content, long regionId, long userId);
}
