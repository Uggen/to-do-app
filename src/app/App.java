package app;

import entity.TaskEntity;
import entity.TaskStatus;
import service.Service;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Добро пожаловать в ToDO App!\n " +
                "---------------------\n " +
                "Выберите действие:\n " +
                "1: Создать задачу\n " +
                "2: Посмотреть заведенные задачи\n " +
                "3: Обновить задачу\n " +
                "4: Удалить задачу");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        switch (choose) {
            case 1:
                Service.createTask();
                break;
            case 2:
                System.out.println("Выберите опцию:\n" +
                        "1: Вывести все задачи\n" +
                        "2: Отфильтровать по статусу\n" +
                        "3: Отсортировать по дате");
                Scanner input = new Scanner(System.in);
                switch (input.nextInt()) {
                    case 1:
                        Service.searchTasks();
                        break;
                    case 2:
                        var task = new TaskEntity();
                        System.out.println("Введите статус: \n" +
                                "1: NEW\n" +
                                "2: IN_PROGRESS\n" +
                                "3: DONE\n");
                        switch (input.nextInt()) {
                            case 1:
                                task.setStatus(TaskStatus.NEW);
                                Service.searchTask(task);
                                break;
                            case 2:
                                task.setStatus(TaskStatus.IN_PROGRESS);
                                Service.searchTask(task);
                                break;
                            case 3:
                                task.setStatus(TaskStatus.DONE);
                                Service.searchTask(task);
                                break;
                            default:
                                System.out.println("Выберите один из пунктов в меню");
                        }
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Выберите один из пунктов в меню");
                        Service.searchTasks();
                }
            case 3:
                Service.updateTask();
                break;
            case 4:
                Service.deleteTask();
                break;
            default:
                System.out.println("Выберите один из пунктов в меню");
        }
    }
}
