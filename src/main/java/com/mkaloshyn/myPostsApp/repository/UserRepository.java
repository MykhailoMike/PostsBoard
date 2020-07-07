package main.java.com.mkaloshyn.myPostsApp.repository;

import main.java.com.mkaloshyn.myPostsApp.model.User;

public interface UserRepository extends GenericRepository<User, Long> {

    User create(String firstName, String lastName, String role);
}
