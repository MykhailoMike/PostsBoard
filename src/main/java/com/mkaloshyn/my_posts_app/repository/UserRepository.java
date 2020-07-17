package main.java.com.mkaloshyn.my_posts_app.repository;

import main.java.com.mkaloshyn.my_posts_app.model.User;

public interface UserRepository extends GenericRepository<User, Long> {

    User create(String firstName, String lastName, String role);
}
