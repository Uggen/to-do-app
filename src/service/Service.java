package service;

import dao.TaskDao;
import entity.TaskEntity;
import entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Service {
    
    public static void createTask() {
        var instance = TaskDao.getInstance();
        var task = new TaskEntity();

        Scanner text = new Scanner(System.in);
        String taskName = text.nextLine();
        if (taskName.equals(" ")) {
            System.out.println("Название задачи должно быть заполнено");
        }
        String taskDescription = text.nextLine();

        task.setTitle(taskName);
        task.setStatus(TaskStatus.NEW);
        task.setCreated_at(LocalDateTime.now());
        task.setUpdated_at(LocalDateTime.now());
        if (!taskDescription.equals(" ")) {
            task.setDescription(taskDescription);
        }
        instance.save(task);
    }

    public static void searchTask(TaskEntity taskStatus) {
        var instance = TaskDao.getInstance();
        instance.filterByStatus(taskStatus);
    }

    public static void searchTasks() {
        var instance = TaskDao.getInstance();
        instance.findAll();
    }

    public static List<TaskEntity> searchTasksByDate() {
        var instance = TaskDao.getInstance();
        var list = instance.findAll();
        List<TaskEntity> sortedByDate = list.stream()
                .sorted(Comparator.comparing(TaskEntity::getCreated_at))
                .toList();
        return sortedByDate;
    }

    public static void updateTask(Long id, TaskStatus status) {
        var instance = TaskDao.getInstance();
        var findResult = instance.findById(id);
        System.out.println(findResult);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setStatus(status);
            instance.update(taskEntity);
        });
    }

    public static boolean updateTask(Long id,String description) {
        var instance = TaskDao.getInstance();
        var findResult = instance.findById(id);
        System.out.println(findResult);

        findResult.ifPresent(taskEntity -> {
            taskEntity.setTitle(description);
            instance.update(taskEntity);
        });
        return true;
    }

    public static void deleteTask(Long id) {
        var instance = TaskDao.getInstance();
        instance.delete(id);
    }
}
