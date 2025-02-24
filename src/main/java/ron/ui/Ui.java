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
     * Initialises the Ui class with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner object for memory reallocation.
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Greets the user.
     *
     * @return A string for greeting the user.
     */
    public static String greetUser() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n")
                .append("     _______  ____   ____ \n")
                .append("     \\_  __ \\/  _ \\ /    \\\n")
                .append("      |  | \\(  <_> )   |  \\\n")
                .append("      |__|   \\____/|___|  /\n")
                .append("                        \\/\n\n")
                .append("Hello! I'm Ron\n")
                .append("What can I do for you?\n");
        return sb.toString();
    }


    /**
     * Bids farewell to user.
     *
     * @return A string for the farewell message.
     */
    public static String farewellUser() {
        return ("""
                Bye. Hope to see you again soon.
                ____________________________________________________________
                """);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of available commands.
     */
    public String showHelp() {
        String helpText = """
            Here are the available commands:
            - `list` : Displays all tasks
            - `todo <task description>` : Adds a todo task
            - `deadline <task description> /by <yyyy-MM-dd HH:mm>` : Adds a deadline
            - `event <task description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>` : Adds an event
            - `mark <task number>` : Marks a task as done
            - `unmark <task number>` : Unmarks a task
            - `delete <task number>` : Deletes a task
            - `find <keyword>` : Finds tasks containing a keyword
            - `bye` : Exits the application
            - `help` : Shows this help message
            """;
        return helpText;
    }

    /**
     * Detects the presence of another command.
     *
     * @return {@code true} if the scanner detects another command and {@code false} otherwise.
     */
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
     * @return A formatted string of the current task list.
     */
    public static String getTaskList(ArrayList<Task> storedCommands) {
        if (storedCommands.isEmpty()) {
            return "No tasks in your list!";
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < storedCommands.size(); i++) {
                result.append(String.format("%d. %s%n", i + 1, storedCommands.get(i)));
            }
            return result.toString();
        }
    }

    /**
     * Displays the list of matching tasks as instructed by the "find" command
     *
     * @param matchingTasks The list of matching tasks to be displayed
     * @return A formatted string of the matching task list.
     */
    public static String getMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(String.format("%d. %s%n", i + 1, matchingTasks.get(i)));
            }
            return result.toString();
        }
    }
}
