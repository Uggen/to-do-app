package io.github.uggen.app;

import io.github.uggen.service.TaskService;

import java.util.Scanner;

public class CreateTaskCommand implements  Command {
    private final TaskService taskService;
    private final Scanner scanner;

    public CreateTaskCommand(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
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
        String description = scanner.nextLine();
        taskService.createTask(taskName, description);

        System.out.println("Задача создана");
    }
}
