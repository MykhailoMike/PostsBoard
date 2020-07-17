package main.java.com.mkaloshyn.my_posts_app.controller;

import main.java.com.mkaloshyn.my_posts_app.model.Post;
import main.java.com.mkaloshyn.my_posts_app.repository.PostRepository;
import main.java.com.mkaloshyn.my_posts_app.repository.csv.CsvPostRepositoryImpl;

import java.util.List;

public class PostController {

    private PostRepository postRepository;

    public PostController() {
        postRepository = new CsvPostRepositoryImpl();
    }

    public Post getById(long id) {
        return postRepository.getById(id);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post save(String content, long regionId, long userId) {
        Post post = postRepository.create(content, regionId, userId);
        return postRepository.save(post);
    }

    public Post deleteById(long id) {
        return postRepository.deleteById(id);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }
}
