import java.util.Scanner;
import java.util.ArrayList;
public class Ron {
    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }
    public static String greetUser() {
        return """
                ____________________________________________________________
                Hello! I'm Ron
                What can I do for you?
                ____________________________________________________________
                """;
    }
    public static String echoCommand(String command) {
        String result =  """
                ____________________________________________________________
                added: %s
                ____________________________________________________________
                """;
        result = String.format(result, command);
        return result;
    }

    public static String farewellUser() {
        return """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetUser());

        ArrayList<Task> storedCommands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();

            if (nextCommand.equals("bye")) {
                break;
            } else if (nextCommand.equals("list")) {
                printSeparator();
                if (storedCommands.isEmpty()) {
                    System.out.println("No tasks in your list!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storedCommands.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, storedCommands.get(i));
                    }
                }
                printSeparator();
            } else if (nextCommand.startsWith("mark") || nextCommand.startsWith("unmark")) {
                try {
                    printSeparator();
                    int taskNumber = Integer.parseInt(nextCommand.split(" ")[1]) - 1;
                    if (taskNumber <= 0 || taskNumber >= storedCommands.size()) {
                        System.out.println("Invalid task number!");
                    } else {
                        Task task = storedCommands.get(taskNumber);
                        if (nextCommand.startsWith("mark")) {
                            if (task.isMarked()) {
                                System.out.println("Task is already marked as done!");
                            } else {
                                task.mark();
                                System.out.printf("Nice! I've marked this task as done:\n %s\n", task);
                            }
                        } else {
                            if (!task.isMarked()) {
                                System.out.println("Task is already not done!");
                            } else {
                                task.unmark();
                                System.out.printf("OK, I've marked this task as not done yet:\n %s\n", task);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format! Use 'mark/unmark <task number>'.");
                }
                printSeparator();
            } else {
                storedCommands.add(new Task(nextCommand));
                System.out.println(echoCommand(nextCommand));
            }
        }

        System.out.println(farewellUser());
        scanner.close();
    }
}
