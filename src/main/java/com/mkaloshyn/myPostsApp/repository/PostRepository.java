package main.java.com.mkaloshyn.myPostsApp.repository;

import main.java.com.mkaloshyn.myPostsApp.model.Post;

public interface PostRepository extends GenericRepository<Post, Long> {

    Post create(String content, long regionId, long userId);
}
