import java.util.Scanner;
import java.util.ArrayList;
public class Ron {
    private static ArrayList<Task> storedCommands = new ArrayList<>();
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
    public static String echoCommand(Task task, int taskCount) {
        return String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.
                ____________________________________________________________
                """, task, taskCount);
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
                        System.out.printf("%d.%s\n", i + 1, storedCommands.get(i));
                    }
                }
                printSeparator();
            } else if (nextCommand.startsWith("mark") || nextCommand.startsWith("unmark")) {
                try {
                    printSeparator();
                    int taskNumber = Integer.parseInt(nextCommand.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= storedCommands.size()) {
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
            } else if (nextCommand.startsWith("todo")) {
                String taskName = nextCommand.substring(5);
                Task task = new Todo(taskName);
                storedCommands.add(task);
                System.out.println(echoCommand(task, storedCommands.size()));
            } else if (nextCommand.startsWith("deadline")) {
                String[] tokens = nextCommand.split(" /by ");
                String taskName = tokens[0].substring(9);
                String by = tokens[1];
                Task task = new Deadline(taskName, by);
                storedCommands.add(task);
                System.out.println(echoCommand(task, storedCommands.size()));
            } else if (nextCommand.startsWith("event")) {
                String[] tokens = nextCommand.split(" /from | /to");
                String taskName = tokens[0].substring(6);
                String from = tokens[1];
                String to = tokens[2];
                Task task = new Event(taskName, from, to);
                storedCommands.add(task);
                System.out.println(echoCommand(task, storedCommands.size()));
            } else {
                System.out.println("Invalid command. Please re-enter your command.");
            }
        }

        System.out.println(farewellUser());
        scanner.close();
    }
}
