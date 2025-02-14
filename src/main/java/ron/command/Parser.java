package ron.command;

import ron.RonException;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Parses the user command and returns the appropriate Command object.
     *
     * @param command The user command as a string.
     * @return A Command object representing the parsed user input.
     * @throws RonException If the input is invalid or arguments are missing.
     */
    public static Command parseCommand(String command) throws RonException {
        assert command != null : "parseCommand: Command should not be null";
        assert !command.trim().isEmpty() : "parseCommand: Command should not be empty";

        String[] tokens = command.trim().split(" ", 2);
        String action = tokens[0];

        switch (action) {
            case "bye":
                return new ExitCommand();
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand();
            case "mark":
                validateArguments(tokens, "Insufficient arguments passed!");
                return new MarkCommand(Integer.parseInt(tokens[1]) - 1);
            case "unmark":
                validateArguments(tokens, "Insufficient arguments passed!");
                return new UnmarkCommand(Integer.parseInt(tokens[1]) - 1);
            case "delete":
                validateArguments(tokens, "Please specify a task number to delete.");
                return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
            case "find":
                validateArguments(tokens, "Please provide a keyword to search.");
                return new FindCommand(tokens[1]);
            case "todo":
                validateArguments(tokens, "To-do description cannot be empty!");
                return new TodoCommand(tokens[1]);
            case "deadline":
                validateArguments(tokens, "Please specify a deadline using /by in the format yyyy-MM-dd HH:mm");
                String[] deadlineTokens = tokens[1].split(" /by ");
                if (deadlineTokens.length < 2 || deadlineTokens[1].trim().isEmpty()) {
                    throw new RonException("Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HH:mm");
                }
                return new DeadlineCommand(deadlineTokens[0].trim(), deadlineTokens[1].trim());
            case "event":
                validateArguments(tokens, "Please specify both the /from and /to timing in the format yyyy-MM-dd HH:mm");
                String[] eventTokens = tokens[1].split(" /from | /to ");
                if (eventTokens.length < 3 || eventTokens[1].trim().isEmpty() || eventTokens[2].trim().isEmpty()) {
                    throw new RonException("Invalid event format. Use: event <task> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm");
                }
                return new EventCommand(eventTokens[0].trim(), eventTokens[1].trim(), eventTokens[2].trim());
            default:
                throw new RonException("Invalid command. Please try again.");
        }
    }

    /**
     * Validates if the command tokens contain the necessary arguments.
     *
     * @param tokens The split command tokens.
     * @param errorMessage The error message to throw if validation fails.
     * @throws RonException If tokens are insufficient or empty.
     */
    private static void validateArguments(String[] tokens, String errorMessage) throws RonException {
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new RonException(errorMessage);
        }
    }
}
