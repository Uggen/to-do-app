package app;

import entity.TaskEntity;
import entity.TaskStatus;
import service.Service;

import java.util.List;
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
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                System.out.println("Чтобы создать задачу введите текст задачи:");
                Service.createTask();
                System.out.println("Задача создана");
                break;
            case 2:
                System.out.println("Выберите опцию:\n" +
                        "1: Вывести все задачи\n" +
                        "2: Отфильтровать по статусу\n" +
                        "3: Отсортировать по дате");
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
                        var sortedByDate = Service.searchTasksByDate();
                        System.out.println(sortedByDate);
                        break;
                    default:
                        System.out.println("Выберите один из пунктов в меню");
                        Service.searchTasks();
                }
            case 3:
                Service.searchTasks();
                var nextLong = input.nextLong();
                var nextString = input.next();
                var result = Service.updateTask(nextLong, nextString);
                if (result) {
                    System.out.println("Задача обновлена");
                } else {
                    System.out.println("Задача не обновлена");
                }
                break;
            case 4:
                Service.searchTasks();
                var nextDel = input.nextLong();
                Service.deleteTask(nextDel);
                break;
            default:
                System.out.println("Выберите один из пунктов в меню");
        }
    }
}
