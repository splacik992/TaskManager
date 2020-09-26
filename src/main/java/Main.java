import org.apache.commons.lang3.StringUtils;


import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<String> taskList = new ArrayList<>();
    private final static String projectFile = "task.csv";


    public static void main(String[] args) {
        mainMenu();
    }


    private static void mainMenu() {
        menuInfo();
        checkTask();

        String input = "";
        while (true) {

            input = scanner.nextLine();
            switch (input) {
                case "add": {
                    addTask();
                    break;
                }

                case "remove": {
                    remove();
                    break;
                }

                case "list": {
                    showAllTasks();
                    break;
                }

                case "exit": {
                    exit();
                }
                default:
                    System.out.println("Please select a correct option");
            }

        }
    }

    private static void exit() {
        scanner.close();
        System.out.println(ConsoleColors.RED + " Bye Bye.");
        Path path = Paths.get(projectFile);
        try {
            Files.write(path, taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }


    public static void addTask() {
        System.out.println("Please add task description");
        String a = scanner.nextLine();
        System.out.println("Please add task due date");
        String b = scanner.nextLine();
        System.out.println("Is your task is important: true/false");
        String c = scanner.nextLine();

        String joinedStrings = StringUtils.join(a, " | ", b, " | ", c);
        taskList.add(joinedStrings);

    }


    public static void checkTask() {
        File file = new File(projectFile);

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                taskList.add(scan.nextLine());
            }
        }catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
    }


    public static void showAllTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String s = taskList.get(i);
            System.out.println(ConsoleColors.YELLOW_BRIGHT + (i + 1)
                    + ConsoleColors.RESET + " : " + s);
        }
        System.out.println(ConsoleColors.BLUE + "Choice : "
                + ConsoleColors.CYAN_BOLD + "'add'/'remove'/'list'/'exit'");
    }


    public static void remove() {
        System.out.println("Please select number of task to delete:");

        while (true) {
            String s = scanner.next();
            if (StringUtils.isNumeric(s)) {
                int tasktoRemove = Integer.parseInt(s);
                if (tasktoRemove >= 1 && tasktoRemove <= taskList.size()) {
                    taskList.remove(tasktoRemove - 1);
                    System.out.println("Value was successfully deleted");
                    break;
                } else if (tasktoRemove == 0) {
                    break;
                }
            }
            System.out.println("Try again... or press 0 to cancel");
        }
        System.out.println(ConsoleColors.BLUE + "Choice : "
                + ConsoleColors.CYAN_BOLD + "'add'/'remove'/'list'/'exit'");
    }

    public static void menuInfo() {
        System.out.println(ConsoleColors.BLUE + " Please select an option ");
        System.out.println(ConsoleColors.RESET +
                "add \n" +
                "remove \n" +
                "list \n" +
                "exit");
    }

}
