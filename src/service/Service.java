package service;

import dao.TaskDao;
import entity.TaskEntity;
import entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class Service {

    public void createTask(String taskName, String... description) {
        var instance = TaskDao.getInstance();
        var task = new TaskEntity();

        task.setTitle(taskName);
        if (description.length > 0) {
            task.setDescription(description[0]);
        }
        task.setStatus(TaskStatus.NEW);
        task.setCreated_at(LocalDateTime.now());
        task.setUpdated_at(LocalDateTime.now());
        instance.save(task);
    }

    public List<TaskEntity> searchTask(TaskEntity taskStatus) {
        var instance = TaskDao.getInstance();
        List<TaskEntity> result = instance.filterByStatus(taskStatus);
        return result;
    }

    public List<TaskEntity> searchTasks() {
        var instance = TaskDao.getInstance();
        return instance.findAll();
    }

    public List<TaskEntity> searchTasksByDate() {
        var instance = TaskDao.getInstance();
        var list = instance.findAll();
        List<TaskEntity> result = list.stream()
                .sorted(Comparator.comparing(TaskEntity::getCreated_at))
                .toList();
        return result;
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        var instance = TaskDao.getInstance();
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setStatus(status);
            instance.update(taskEntity);
        });
    }

    public void updateTaskDesc(Long id,String description) {
        var instance = TaskDao.getInstance();
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setDescription(description);
            instance.update(taskEntity);
        });
    }

    public void updateTaskTitle(Long id,String title) {
        var instance = TaskDao.getInstance();
        var findResult = instance.findById(id);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setTitle(title);
            instance.update(taskEntity);
        });
    }

    public void deleteTask(Long id) {
        var instance = TaskDao.getInstance();
        instance.delete(id);
    }
}
