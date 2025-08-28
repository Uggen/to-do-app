package io.github.uggen.dao;

import io.github.uggen.entity.TaskEntity;
import io.github.uggen.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll();

    Optional<T> findById(K id);

    T save(T entity);

    void delete(K id);

    List<TaskEntity> filterByStatus(TaskStatus taskStatus);
}
