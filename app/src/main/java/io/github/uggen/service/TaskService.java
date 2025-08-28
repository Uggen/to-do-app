package io.github.uggen.service;

import io.github.uggen.dao.Dao;
import io.github.uggen.dao.DaoProvider;
import io.github.uggen.entity.TaskEntity;
import io.github.uggen.entity.TaskStatus;
import io.github.uggen.exceptions.DaoException;

import java.util.Comparator;
import java.util.List;

public class TaskService {

    private final Dao<Long, TaskEntity> instance;

    public TaskService() {
        this.instance = DaoProvider.getTaskEntityDao();
    }

    public void createTask(String title, String description) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        if (description == null || description.isBlank()) {
            description = "";
        }

        try {
            TaskEntity savedTask = instance.save(TaskEntity.create(title, description));
            System.out.println("You created task with id #: " + savedTask.getId());
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<TaskEntity> searchTask(TaskStatus taskStatus) {
        return instance.filterByStatus(taskStatus);
    }

    public List<TaskEntity> searchTasks() {
        return instance.findAll();
    }

    public List<TaskEntity> searchTasksByDate() {
        var list = instance.findAll();
        return list.stream()
                .sorted(Comparator.comparing(TaskEntity::getCreated_at))
                .toList();
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setStatus(status);
            instance.save(taskEntity);
        });
    }

    public void updateTaskDesc(Long id,String description) {
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setDescription(description);
            instance.save(taskEntity);
        });
    }

    public void updateTaskTitle(Long id,String title) {
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setTitle(title);
            instance.save(taskEntity);
        });
    }

    public void deleteTask(Long id) {
        instance.delete(id);
    }
}
