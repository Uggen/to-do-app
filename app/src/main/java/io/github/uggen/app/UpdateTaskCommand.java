package io.github.uggen.app;

import io.github.uggen.entity.TaskStatus;
import io.github.uggen.service.TaskService;

import java.util.Scanner;

public class UpdateTaskCommand implements Command {
    private final TaskService taskService;
    private final Scanner scanner;

    public UpdateTaskCommand(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Введите ID задачи:");
        long id = scanner.nextLong();

        System.out.println("Что изменить?\n" +
                "1. Заголовок\n" +
                "2. Описание\n" +
                "3. Статус");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введите новое название:");
                var newTitle = scanner.next();
                taskService.updateTaskTitle(id, newTitle);
                System.out.println("Название изменено");
                break;
            case 2: System.out.println("Введите новое описание:");
                var newDesc = scanner.next();
                taskService.updateTaskDesc(id, newDesc); break;
            case 3: System.out.println("Установите новый статус:");
                var newStatus = scanner.next();
                if (newStatus.equals("NEW")){
                    taskService.updateTaskStatus(id, TaskStatus.NEW); break;
                } else if (newStatus.equals("IN_PROGRESS")){
                    taskService.updateTaskStatus(id, TaskStatus.IN_PROGRESS); break;
                } else if (newStatus.equals("DONE")){
                    taskService.updateTaskStatus(id, TaskStatus.DONE); break;
                } else {
                    System.out.println("Введен несуществующий статус");
                    break;
                }
        }
    }
}
