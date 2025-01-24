import java.util.Scanner;

public class Ron {
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

        String[] storedCommands = new String[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                break;
            } else if (nextCommand.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%s. %s%n", i + 1, storedCommands[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            storedCommands[counter] = nextCommand;
            counter++;
            System.out.println(echoCommand(nextCommand));
        }

        System.out.println(farewellUser());
        scanner.close();

    }
}
