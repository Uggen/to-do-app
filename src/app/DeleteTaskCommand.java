package app;

import service.Service;

import java.util.Scanner;

public class DeleteTaskCommand implements Command {
    private final Service service;
    private final Scanner scanner;

    public DeleteTaskCommand(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        service.searchTasks();
        System.out.println("Введите Id задачи");
        var id = scanner.nextLong();
        service.deleteTask(id);
        System.out.println("Задача удалена");
    }
}
