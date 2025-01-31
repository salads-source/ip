package ron.ui;

import ron.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles all user interactions, including input and output operations.
 * <p>
 * This class provides methods to read user commands, display messages,
 * and format task-related outputs.
 * </p>
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initializes the Ui class with a Scanner for user input.
     */
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

    /**
     * Returns a formatted message for task addition or removal.
     *
     * @param task The task being added or removed.
     * @param taskCount The updated number of tasks in the list.
     * @param isAdd {@code true} if the task is being added, {@code false} if it is being removed.
     * @return A formatted string indicating the addition or removal of a task.
     */
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

    /**
     * Displays the list of stored tasks.
     *
     * @param storedCommands The list of tasks to be displayed.
     */
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
