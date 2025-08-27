package app;

import service.Service;

import java.util.Scanner;

public class CreateTaskCommand implements  Command {
    private final Service service;
    private final Scanner scanner;

    public CreateTaskCommand(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        System.out.println("Чтобы создать задачу введите текст задачи:");
        String taskName = scanner.nextLine();
        if (taskName == null || taskName.isEmpty()) {
            System.out.println("ERROR - Название задачи не было введено");
            return;
        }
        System.out.println("Введите описание задачи (*):");
        String taskDescription = scanner.nextLine();
        if (taskDescription == null || taskDescription.equals(" ")){
            service.createTask(taskName);
        } else {
            service.createTask(taskName, taskDescription);
        }
        System.out.println("Задача создана");
    }
}
