package io.github.uggen.app;

import io.github.uggen.service.TaskService;

import java.util.Scanner;

public class DeleteTaskCommand implements Command {
    private final TaskService taskService;
    private final Scanner scanner;

    public DeleteTaskCommand(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        taskService.searchTasks().forEach(task ->
                System.out.println("ID: " + task.getId() + " Title: " + task.getTitle()));
        System.out.println("Введите Id задачи");
        var id = scanner.nextLong();
        taskService.deleteTask(id);
        System.out.println("Задача удалена");
    }
}
