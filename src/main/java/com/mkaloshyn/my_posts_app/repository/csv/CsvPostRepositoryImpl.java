package main.java.com.mkaloshyn.my_posts_app.repository.csv;

import main.java.com.mkaloshyn.my_posts_app.model.Post;
import main.java.com.mkaloshyn.my_posts_app.model.Region;
import main.java.com.mkaloshyn.my_posts_app.repository.PostRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvPostRepositoryImpl implements PostRepository {

    private static final String POSTS_DB = "src/main/resources/posts.csv";
    private static final String POSTS_TO_USERS = "src/main/resources/postsToUsers.csv";
    private static final String BUFFER = "src/main/resources/buf.csv";
    private CsvRegionRepositoryImpl csvRegionRepository = new CsvRegionRepositoryImpl();
    private CsvUtil csvUtil = CsvUtil.getCsvUtil();

    @Override
    public Post getById(Long id) {
        String str = csvUtil.defineMatchingString(id, POSTS_DB);
        if (str != null) {
            return getPost(str);
        } else {
            return null;
        }
    }

    private Post getPost(String str) {
        String[] split = str.split(";");
        Long id = Long.valueOf(split[0]);
        String content = split[1];
        Region region = csvRegionRepository.getById(Long.valueOf(split[2]));
        LocalDateTime created = LocalDateTime.parse(split[3]);
        LocalDateTime updated;
        if (split.length == 5) {
            updated = LocalDateTime.parse(split[4]);
            return Post.base()
                    .addId(id)
                    .addContent(content)
                    .addCreated(created)
                    .addUpdated(updated)
                    .addRegion(region)
                    .build();
        } else {
            return Post.base()
                    .addId(id)
                    .addContent(content)
                    .addCreated(created)
                    .addRegion(region)
                    .build();
        }
    }

    @Override
    public List<Post> getAll() {
        return getPostsAsList();
    }

    private List<Post> getPostsAsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_DB))) {
            String str;
            List<Post> posts = new ArrayList<>();
            while (true) {
                if ((str = reader.readLine()) == null) {
                    return posts;
                }
                if (!str.isEmpty() && !str.startsWith("id")) {
                    Post post = getPost(str);
                    posts.add(post);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post save(Post post) {
        if (post != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(POSTS_DB, true))) {
                writer.write(csvUtil.getStringFromPost(post));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getById(post != null ? post.getId() : null);
    }

    @Override
    public Post update(Post post) {
        csvUtil.updateEntry(post, POSTS_DB);
        return getById(post.getId());
    }

    @Override
    public Post deleteById(Long id) {
        Post postToDelete = getById(id);
        csvUtil.deleteEntryById(id, POSTS_DB);

        if (postToDelete != null) {
            File sourceFile = new File(POSTS_TO_USERS);
            File outputFile = new File(BUFFER);

            try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    if (!str.isEmpty()) {
                        String[] parts = str.split(";");
                        if (parts[0].equalsIgnoreCase("post id")) {
                            writer.write(str);
                            writer.newLine();
                        } else {
                            long idFromParts = Long.parseLong(parts[0]);
                            if (idFromParts != id) {
                                writer.write(str);
                                writer.newLine();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sourceFile.delete();
            outputFile.renameTo(sourceFile);
        }
        return postToDelete;
    }

    @Override
    public Post create(String content, long regionId, long userId) {
        long id = csvUtil.defineNextId(Objects.requireNonNull(getPostsAsList()));
        Post post = Post.base()
                .addId(id)
                .addContent(content)
                .addCreated(LocalDateTime.now())
                .addRegion(csvRegionRepository.getById(regionId))
                .build();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(POSTS_TO_USERS, true))) {
            writer.write(id + ";" + userId);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}
