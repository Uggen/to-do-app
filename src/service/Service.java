package service;

import dao.TaskDao;
import entity.TaskEntity;
import entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Service {
    
    public static void createTask() {
        var instance = TaskDao.getInstance();
        var task = new TaskEntity();

        System.out.println("Чтобы создать задачу введите текст задачи:");

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
        System.out.println("Задача создана");
    }

    public static void searchTask(TaskEntity taskStatus) {
        var instance = TaskDao.getInstance();
        instance.filterByStatus(taskStatus);
    }

    public static void searchTasks() {
        var instance = TaskDao.getInstance();
        instance.findAll();
    }

    public static void updateTask() {
    }

    public static void deleteTask() {
    }
}
