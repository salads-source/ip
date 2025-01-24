import java.util.Scanner;

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

        Task[] storedCommands = new Task[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                break;
            } else if (nextCommand.equals("list")) {
                printSeparator();
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < counter; i++) {
                    System.out.printf("%s. %s\n", i + 1, storedCommands[i]);
                }

                printSeparator();
                continue;
            } else if (nextCommand.startsWith("mark")) {
                int taskNumber = Integer.parseInt(nextCommand.split(" ")[1]) - 1;
                storedCommands[taskNumber].mark();

                printSeparator();
                System.out.printf("Nice! I've marked this task as done:\n  %s\n", storedCommands[taskNumber]);
                printSeparator();
                continue;
            } else if (nextCommand.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(nextCommand.split(" ")[1]) - 1;
                storedCommands[taskNumber].unmark();

                printSeparator();
                System.out.printf("OK, I've marked this task as not done yet:\n  %s\n", storedCommands[taskNumber]);
                printSeparator();
                continue;
            }

            storedCommands[counter] = new Task(nextCommand);
            counter++;
            System.out.println(echoCommand(nextCommand));
        }

        System.out.println(farewellUser());
        scanner.close();

    }
}
