package main.java.com.mkaloshyn.my_posts_app.controller;

import main.java.com.mkaloshyn.my_posts_app.model.User;
import main.java.com.mkaloshyn.my_posts_app.repository.UserRepository;
import main.java.com.mkaloshyn.my_posts_app.repository.csv.CsvUserRepositoryImpl;

import java.util.List;

public class UserController {

    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new CsvUserRepositoryImpl();
    }

    public User getById(long id) {
        return userRepository.getById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User save(String firstName, String lastName, String role) {
        User user = userRepository.create(firstName, lastName, role);
        return userRepository.save(user);
    }

    public User deleteById(long id) {
        return userRepository.deleteById(id);
    }

    public User update(User user) {
        return userRepository.update(user);
    }
}
