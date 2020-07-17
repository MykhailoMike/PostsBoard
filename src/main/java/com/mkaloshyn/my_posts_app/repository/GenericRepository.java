package main.java.com.mkaloshyn.my_posts_app.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(Long id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    T deleteById(Long id);

}
