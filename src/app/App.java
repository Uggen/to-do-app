package app;

import service.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        Map<Integer, Command> commands = new HashMap<>();

        commands.put(1, new CreateTaskCommand(service, scanner));
        commands.put(2, new ListTasksCommand(service, scanner));
        commands.put(3, new UpdateTaskCommand(service, scanner));
        commands.put(4, new DeleteTaskCommand(service, scanner));
        while (true) {
            System.out.println("Добро пожаловать в ToDO App!\n " +
                    "---------------------\n " +
                    "Выберите действие:\n " +
                    "1. Создать задачу\n " +
                    "2. Показать задачи\n " +
                    "3. Обновить задачу\n " +
                    "4. Удалить задачу\n" +
                    "0. Выход");

            int choice = scanner.nextInt();

            if (choice == 0) break;

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Неверный выбор!");
            }
        }
    }
}
