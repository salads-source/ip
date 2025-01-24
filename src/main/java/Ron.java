import java.util.Scanner;
import java.util.ArrayList;
public class Ron {
    private static final ArrayList<Task> storedCommands = new ArrayList<>();
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

    public static String farewellUser() {
        return """
                ____________________________________________________________
                Bye. Hope to see you again soon.
                ____________________________________________________________
                """;
    }

    public static void listTasks() {
        if (storedCommands.isEmpty()) {
            System.out.println("No tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < storedCommands.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, storedCommands.get(i));
            }
        }
    }

    public static void setTaskStatus(String nextCommand) throws RonException {
        String[] tokens = nextCommand.split(" ", 2);
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new RonException("Insufficient arguments passed!");
        }
        int taskNumber = Integer.parseInt(tokens[1]) - 1;
        if (taskNumber < 0 || taskNumber >= storedCommands.size()) {
            throw new RonException("Invalid task number!");
        } else {
            Task task = storedCommands.get(taskNumber);
            if (nextCommand.startsWith("mark")) {
                if (task.isMarked()) {
                    throw new RonException("Task is already marked as done!");
                } else {
                    task.mark();
                    System.out.printf("Nice! I've marked this task as done:\n %s\n", task);
                }
            } else {
                if (!task.isMarked()) {
                    System.out.println("Task is already marked as undone!");
                } else {
                    task.unmark();
                    System.out.printf("OK, I've marked this task as not done yet:\n %s\n", task);
                }
            }
        }
    }

    public static void deleteTask(String nextCommand) throws RonException {
        String[] tokens = nextCommand.split(" ", 2);
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new RonException("Please specify a task number to delete.");
        }
        int taskNumber = Integer.parseInt(tokens[1].trim()) - 1;
        if (taskNumber < 0 || taskNumber >= storedCommands.size()) {
            throw new RonException("Invalid task number. Please choose a valid task to delete.");
        }
        Task task = storedCommands.get(taskNumber);
        storedCommands.remove(taskNumber);
        System.out.println(echoCommand(task, storedCommands.size(), false));
    }

    public static void addTodoTask(String nextCommand) throws RonException {
        String[] tokens = nextCommand.split(" ", 2);
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new RonException("Todo activity cannot be empty!");
        }
        Task task = new Todo(tokens[1]);
        storedCommands.add(task);
        System.out.println(echoCommand(task, storedCommands.size(), true));
    }

    public static void addDeadlineTask(String nextCommand) throws RonException {
        String[] tokens = nextCommand.split(" /by ");
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new RonException("Please specify a deadline using /by");
        }
        String taskName = tokens[0].substring(9);
        String by = tokens[1];
        Task task = new Deadline(taskName, by);
        storedCommands.add(task);
        System.out.println(echoCommand(task, storedCommands.size(), true));
    }

    public static void addEventTask(String nextCommand) throws RonException {
        String[] tokens = nextCommand.split(" /from | /to");
        if (tokens.length < 3 || tokens[1].trim().isEmpty() || tokens[2].trim().isEmpty()) {
            throw new RonException("Please specify both the /from and /to timing");
        }
        String taskName = tokens[0].substring(6);
        String from = tokens[1];
        String to = tokens[2];
        Task task = new Event(taskName, from, to);
        storedCommands.add(task);
        System.out.println(echoCommand(task, storedCommands.size(), true));
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
            printSeparator();
            if (nextCommand.equals("bye")) {
                break;
            } else if (nextCommand.equals("list")) {
                listTasks();
            } else if (nextCommand.startsWith("mark") || nextCommand.startsWith("unmark")) {
                try {
                    setTaskStatus(nextCommand);
                } catch (RonException e) {
                    System.out.println(e.getMessage());
                }

            } else if (nextCommand.startsWith("delete")) {
                try {
                    deleteTask(nextCommand);
                } catch (RonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (nextCommand.startsWith("todo")) {
                try {
                   addTodoTask(nextCommand);
                } catch (RonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (nextCommand.startsWith("deadline")) {
                try {
                    addDeadlineTask(nextCommand);
                } catch (RonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (nextCommand.startsWith("event")) {
                try {
                    addEventTask(nextCommand);
                } catch (RonException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid command. Please re-enter your command.");
            }
            printSeparator();
        }

        System.out.println(farewellUser());
        scanner.close();
    }
}
