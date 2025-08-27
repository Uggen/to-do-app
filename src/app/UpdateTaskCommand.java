package app;

import entity.TaskStatus;
import service.Service;

import java.util.Scanner;

public class UpdateTaskCommand implements Command {
    private final Service service;
    private final Scanner scanner;

    public UpdateTaskCommand(Service service, Scanner scanner) {
        this.service = service;
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
                service.updateTaskTitle(id, newTitle);
                System.out.println("Название изменено");
                break;
            case 2: System.out.println("Введите новое описание:");
                var newDesc = scanner.next();
                service.updateTaskDesc(id, newDesc); break;
            case 3: System.out.println("Установите новый статус:");
                var newStatus = scanner.next();
                if (newStatus.equals("NEW")){
                    service.updateTaskStatus(id, TaskStatus.NEW); break;
                } else if (newStatus.equals("IN_PROGRESS")){
                    service.updateTaskStatus(id, TaskStatus.IN_PROGRESS); break;
                } else if (newStatus.equals("DONE")){
                    service.updateTaskStatus(id, TaskStatus.DONE); break;
                } else {
                    System.out.println("Введен несуществующий статус");
                    break;
                }
        }
    }
}
