import org.apache.commons.lang3.StringUtils;


import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<String> taskList = new ArrayList<>();

    public static void main(String[] args) {

        menuInfo();
        String input = "";

        while (true) {

            input = scanner.nextLine();
            switch (input) {
                case "add": {
                    addTask();
                    break;
                }

                case "remove": {
                    System.out.println("remove selected task");
                    break;

                }
                case "list": {
                    showAllTasks();
                    break;

                }

                case "exit": {
                    System.out.println(ConsoleColors.RED + " Bye Bye.");
                    scanner.close();
                    System.exit(0);
                }

                default:
                    System.out.println("Please select a correct option");


            }

        }


    }


    public static void addTask() {
        System.out.println("Please add task description");
        String a = scanner.nextLine();

        System.out.println("Please add task due date");
        String b = scanner.nextLine();


        System.out.println("Is your task is important: true/false");
        String c = scanner.nextLine();

        String joinedStrings = StringUtils.join(a," ", b," ", c);
        taskList.add(joinedStrings);

        System.out.println(joinedStrings);

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder formattedString = stringBuilder.append("\n")
                .append(a).append(" ")
                .append(b).append(" ")
                .append(c);
        {

            System.out.println(taskList); // <- testing


            try {
                Writer output = new BufferedWriter(new FileWriter("task.txt", true));
                output.append(formattedString);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showAllTasks(){

        File file = new File("task.txt");
        StringBuilder reading = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        System.out.println(reading.toString());

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
