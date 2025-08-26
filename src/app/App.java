package app;

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
            case 1 -> Service.createTask();
            case 2 -> Service.searchTasks();
            case 3 -> Service.updateTask();
            case 4 -> Service.deleteTask();
            default -> System.out.println("Выберите один из пунктов в меню");
        }
    }
}
