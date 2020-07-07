package main.java.com.mkaloshyn.myPostsApp.view;

import main.java.com.mkaloshyn.myPostsApp.controller.PostController;
import main.java.com.mkaloshyn.myPostsApp.controller.RegionController;
import main.java.com.mkaloshyn.myPostsApp.controller.UserController;
import main.java.com.mkaloshyn.myPostsApp.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static main.java.com.mkaloshyn.myPostsApp.view.ViewUtil.*;

public class PostView {

    private PostController postController;
    private RegionController regionController;
    private UserController userController;
    private Scanner scanner;

    public PostView(Scanner scanner) {
        this.scanner = scanner;
        postController = new PostController();
        regionController = new RegionController();
        userController = new UserController();
    }

    public Post getById() {
        System.out.println(POST_ID_REQUEST);
        long id = (long) scanner.nextInt();
        Post post = postController.getById(id);
        if (post == null) {
            System.out.println(NO_POST_WITH_ID_MSG);
        } else {
            System.out.println(post);
        }
        return post;
    }

    public List<Post> getAll() {
        List<Post> postList = postController.getAll();
        if (!postList.isEmpty()) {
            postList.forEach(System.out::println);
        } else {
            System.out.println(NO_POSTS_MSG);
        }
        System.out.println(postList);
        return postList;
    }

    public void save() {
        scanner.nextLine();
        long regionId;
        do {
            System.out.println(REGION_ID_REQUEST);
            regionId = scanner.nextLong();
        } while (regionController.getById(regionId) == null);
        long userId;
        do {
            System.out.println(USER_ID_REQUEST);
            userId = scanner.nextLong();
        } while (userController.getById(userId) == null);
        scanner.nextLine();
        System.out.println(POST_CONTENT_REQUEST);
        String content = scanner.nextLine();
        Post post = postController.save(content, regionId, userId);
        if (post != null) {
            System.out.println(SUCCESSFULLY_ADDED_POST_MSG);
            System.out.println(post);
        }
    }

    public void deleteById() {
        System.out.println(POST_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (postController.getById(id) == null) {
            System.out.println(NO_POST_WITH_ID_MSG);
        } else {
            Post deletedPost = postController.deleteById(id);
            System.out.println(SUCCESSFULLY_DELETED_POST_MSG + deletedPost);
        }
    }

    public Post update() {
        System.out.println(POST_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (postController.getById(id) == null) {
            System.out.println(NO_POST_WITH_ID_MSG);
            return null;
        } else {
            scanner.nextLine();
            long regionId;
            do {
                System.out.println(REGION_ID_REQUEST);
                regionId = scanner.nextLong();
            } while (regionController.getById(regionId) == null);
            scanner.nextLine();
            System.out.println(POST_UPDATE_BODY_REQUEST);
            String content = scanner.nextLine();
            LocalDateTime created = postController.getById(id).getCreated();
            Post post = Post.base()
                    .addId(id)
                    .addContent(content)
                    .addCreated(created)
                    .addUpdated(LocalDateTime.now())
                    .addRegion(regionController.getById(regionId))
                    .build();
            postController.update(post);
            System.out.println(UPDATED_POST + postController.getById(id));
            return postController.getById(id);
        }
    }
}
