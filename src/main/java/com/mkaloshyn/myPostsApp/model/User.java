package main.java.com.mkaloshyn.myPostsApp.model;

import java.util.List;

public class User extends BasicEntry {

    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Role role;

    private User() {
    }

    public static UserBuilder base() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private User user = new User();

        private UserBuilder() {

        }

        public UserBuilder addId(Long id) {
            user.setId(id);
            return this;
        }

        public UserBuilder addFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public UserBuilder addLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public UserBuilder addPostsList(List<Post> posts) {
            user.posts = posts;
            return this;
        }

        public UserBuilder addRole(Role role) {
            user.role = role;
            return this;
        }

        public User build() {
            return user;
        }
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("id = ").append(getId()).append(",")
                .append(" firstName='").append(firstName).append('\'').append(",")
                .append(" lastName='").append(lastName).append('\'').append(",")
                .append(" role=").append(role);
        if (!posts.isEmpty()) {
            sb.append(",\nposts:");
            posts.forEach(post -> sb.append("\n").append(post));
        }
        sb.append("}").append("\n");
        return sb.toString();
    }
}
