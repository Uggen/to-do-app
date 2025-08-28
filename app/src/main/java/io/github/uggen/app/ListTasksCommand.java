package io.github.uggen.app;

import io.github.uggen.entity.TaskEntity;
import io.github.uggen.entity.TaskStatus;
import io.github.uggen.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class ListTasksCommand implements Command {

    private final TaskService taskService;
    private final Scanner scanner;

    public ListTasksCommand(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Выберите режим:\n" +
                "1. Все задачи\n" +
                "2. Фильтр по статусу\n" +
                "3. Сортировка по дате");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                taskService.searchTasks().forEach(System.out::println);
                break;
            case 2: System.out.println("Укажите статус:");
                var newStatus = scanner.next();
                if (newStatus.equals("NEW")){
                    List<TaskEntity> resultNew = taskService.searchTask(TaskStatus.NEW);
                    resultNew.forEach(System.out::println);
                    break;
                } else if (newStatus.equals("IN_PROGRESS")){
                    List<TaskEntity> resultInProc = taskService.searchTask(TaskStatus.IN_PROGRESS);
                    resultInProc.forEach(System.out::println);
                    break;
                } else if (newStatus.equals("DONE")){
                    List<TaskEntity> resultDone = taskService.searchTask(TaskStatus.DONE);
                    resultDone.forEach(System.out::println);
                    break;
                } else {
                    System.out.println("Введен несуществующий статус");
                    break;
                }
            case 3:
                List<TaskEntity> resultSearchByDate = taskService.searchTasksByDate();
                resultSearchByDate.forEach(System.out::println);
                break;
        }
    }
}
