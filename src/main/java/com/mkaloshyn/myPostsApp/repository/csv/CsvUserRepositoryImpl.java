package main.java.com.mkaloshyn.myPostsApp.repository.csv;

import main.java.com.mkaloshyn.myPostsApp.model.Post;
import main.java.com.mkaloshyn.myPostsApp.model.Role;
import main.java.com.mkaloshyn.myPostsApp.model.User;
import main.java.com.mkaloshyn.myPostsApp.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvUserRepositoryImpl implements UserRepository {

    private static final String USERS_DB = "src/main/resources/users.csv";
    private static final String POSTS_TO_USERS = "src/main/resources/postsToUsers.csv";
    private CsvPostRepositoryImpl csvPostRepository = new CsvPostRepositoryImpl();
    private CsvUtil csvUtil = CsvUtil.getCsvUtil();

    @Override
    public User getById(Long id) {
        String str = csvUtil.defineMatchingString(id, USERS_DB);
        if (str != null) {
            return getUser(str);
        } else {
            return null;
        }
    }

    private User getUser(String str) {
        String[] split = str.split(";");
        Long id = Long.valueOf(split[0]);
        String firstName = split[1];
        String lastName = split[2];
        String role = split[3];
        List<Post> posts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_TO_USERS))) {
            String strng;
            while (true) {
                if ((strng = reader.readLine()) == null) {
                    return User.base()
                            .addId(id)
                            .addFirstName(firstName)
                            .addLastName(lastName)
                            .addPostsList(posts)
                            .addRole(Role.valueOf(role))
                            .build();
                }
                if (!strng.isEmpty() && !strng.startsWith("post id")) {
                    String[] postsToUsersPair = strng.split(";");
                    if ((Long.valueOf(postsToUsersPair[1])).equals(id)) {
                        posts.add(csvPostRepository.getById(Long.valueOf(postsToUsersPair[0])));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return User.base()
                .addId(id)
                .addFirstName(firstName)
                .addLastName(lastName)
                .addPostsList(posts)
                .addRole(Role.valueOf(role))
                .build();
    }

    @Override
    public List<User> getAll() {
        return getUsersAsList();
    }

    private List<User> getUsersAsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_DB))) {
            String str;
            List<User> users = new ArrayList<>();
            str = reader.readLine();
            while (true) {
                if ((str = reader.readLine()) == null) {
                    return users;
                }
                if (!str.isEmpty()) {
                    User user = getUser(str);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_DB, true))) {
                writer.write(csvUtil.getStringFromUser(user));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getById(user != null ? user.getId() : null);
    }

    @Override
    public User update(User user) {
        csvUtil.updateEntry(user, USERS_DB);
        return getById(user.getId());
    }

    @Override
    public User deleteById(Long id) {
        User userToDelete = getById(id);
        csvUtil.deleteEntryById(id, USERS_DB);
        return userToDelete;
    }

    @Override
    public User create(String firstName, String lastName, String role) {
        long id = csvUtil.defineNextId(Objects.requireNonNull(getUsersAsList()));
        return User.base()
                .addId(id)
                .addFirstName(firstName)
                .addLastName(lastName)
                .addRole(Role.valueOf(role))
                .build();
    }
}
