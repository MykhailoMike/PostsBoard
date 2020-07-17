package main.java.com.mkaloshyn.my_posts_app.view;

import main.java.com.mkaloshyn.my_posts_app.controller.UserController;
import main.java.com.mkaloshyn.my_posts_app.model.Role;
import main.java.com.mkaloshyn.my_posts_app.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static main.java.com.mkaloshyn.my_posts_app.view.ViewUtil.*;

public class UserView {

    private UserController userController;
    private Scanner scanner;

    public UserView(Scanner scanner) {
        this.scanner = scanner;
        userController = new UserController();
    }

    public User getById() {
        System.out.println(USER_ID_REQUEST);
        long id = (long) scanner.nextInt();
        User user = userController.getById(id);
        if (user == null) {
            System.out.println(NO_USER_WITH_ID_MSG);
        } else {
            System.out.println(user);
        }
        return user;
    }

    public List<User> getAll() {
        List<User> userList = userController.getAll();
        if (!userList.isEmpty()) {
            userList.forEach(System.out::println);
        } else {
            System.out.println(NO_USERS_MSG);
        }
        return userList;
    }

    public User save() {
        scanner.nextLine();
        System.out.println(FIRST_NAME_REQUEST);
        String firstName = scanner.nextLine();
        System.out.println(LAST_NAME_REQUEST);
        String lastName = scanner.nextLine();
        System.out.println(USER_ROLE_REQUEST + Arrays.toString(Role.values()));
        String role = scanner.nextLine();
        User user = userController.save(firstName, lastName, role);
        if (user != null) {
            System.out.println(SUCCESSFULLY_ADDED_USER_MSG);
            System.out.println(user);
        }
        return user;
    }

    public void deleteById() {
        System.out.println(USER_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (userController.getById(id) == null) {
            System.out.println(NO_USER_WITH_ID_MSG);
        } else {
            User deletedUser = userController.deleteById(id);
            System.out.println(SUCCESSFULLY_DELETED_USER_MSG);
            System.out.println(deletedUser);
        }
    }

    public User update() {
        System.out.println(USER_ID_REQUEST);
        long id = (long) scanner.nextInt();
        if (userController.getById(id) == null) {
            System.out.println(NO_USER_WITH_ID_MSG);
            return null;
        } else {
            scanner.nextLine();
            System.out.println(FIRST_NAME_REQUEST);
            String firstName = scanner.nextLine();
            System.out.println(LAST_NAME_REQUEST);
            String lastName = scanner.nextLine();
            System.out.println(USER_ROLE_REQUEST + Arrays.toString(Role.values()));
            String role = scanner.nextLine();
            User user = User.base()
                    .addId(id)
                    .addFirstName(firstName)
                    .addLastName(lastName)
                    .addPostsList(userController.getById(id).getPosts())
                    .addRole(Role.valueOf(role.toUpperCase()))
                    .build();
            userController.update(user);
            System.out.println(UPDATED_USER + userController.getById(id));
            return userController.getById(id);
        }
    }
}
