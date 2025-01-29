package ron.ui;

import ron.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        this.scanner.close();
    }
    public static void greetUser() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println("Hello from\n" + logo);
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Ron
                What can I do for you?
                ____________________________________________________________
                """);
    }

    public static void farewellUser() {
        System.out.println("""
                Bye. Hope to see you again soon.
                ____________________________________________________________
                """);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public boolean hasNextInput() {
        return this.scanner.hasNext();
    }

    public String readNextCommand() {
        return this.scanner.nextLine();
    }

    public static void echoMessage(String message) {
        System.out.println(message);
    }

    public static String echoCommand(Task task, int taskCount, boolean isAdd) {
        return isAdd ? String.format("""
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.
                """, task, taskCount) : String.format("""
                Noted. I've removed this task:
                  %s
                Now you have %d tasks in the list.
                """, task, taskCount);
    }

    public static void listTasks(ArrayList<Task> storedCommands) {
        if (storedCommands.isEmpty()) {
            System.out.println("No tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < storedCommands.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, storedCommands.get(i));
            }
        }
    }



}
