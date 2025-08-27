package app;

import entity.TaskEntity;
import entity.TaskStatus;
import service.Service;

import java.util.List;
import java.util.Scanner;

public class ListTasksCommand implements Command {

    private final Service service;
    private final Scanner scanner;

    public ListTasksCommand(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        TaskEntity task = new TaskEntity();
        System.out.println("Выберите режим:\n" +
                "1. Все задачи\n" +
                "2. Фильтр по статусу\n" +
                "3. Сортировка по дате");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<TaskEntity> result = service.searchTasks();
                result.forEach(System.out::println);
                break;
            case 2: System.out.println("Укажите статус:");
                var newStatus = scanner.next();
                if (newStatus.equals("NEW")){
                    task.setStatus(TaskStatus.NEW);
                    List<TaskEntity> resultNew = service.searchTask(task);
                    resultNew.forEach(System.out::println);
                    break;
                } else if (newStatus.equals("IN_PROGRESS")){
                    task.setStatus(TaskStatus.IN_PROGRESS);
                    List<TaskEntity> resultInProc = service.searchTask(task);
                    resultInProc.forEach(System.out::println);
                    break;
                } else if (newStatus.equals("DONE")){
                    task.setStatus(TaskStatus.DONE);
                    List<TaskEntity> resultDone = service.searchTask(task);
                    resultDone.forEach(System.out::println);
                    break;
                } else {
                    System.out.println("Введен несуществующий статус");
                    break;
                }
            case 3:
                List<TaskEntity> resultSearchByDate = service.searchTasksByDate();
                resultSearchByDate.forEach(System.out::println);
                break;
        }
    }
}
