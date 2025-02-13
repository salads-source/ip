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
            case "list":
                return new ListCommand();
            case "mark":
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Insufficient arguments passed!");
                }
                return new MarkCommand(Integer.parseInt(tokens[1]) - 1);
            case "unmark":
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Insufficient arguments passed!");
                }
                return new UnmarkCommand(Integer.parseInt(tokens[1]) - 1);
            case "delete":
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Please specify a task number to delete.");
                }
                return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
            case "find":
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Please provide a keyword to search.");
                }
                return new FindCommand(tokens[1]);
            case "todo":
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("To-do description cannot be empty!");
                }
                return new TodoCommand(tokens[1]);
            case "deadline":
                String[] deadlineTokens = tokens[1].split(" /by ");
                if (deadlineTokens.length < 2 || deadlineTokens[1].trim().isEmpty()) {
                    throw new RonException("Please specify a deadline using /by in the format yyyy-MM-dd HH:mm");
                }
                return new DeadlineCommand(deadlineTokens[0].trim(), deadlineTokens[1].trim());
            case "event":
                String[] eventTokens = tokens[1].split(" /from | /to ");
                if (eventTokens.length < 3 || eventTokens[1].trim().isEmpty() || eventTokens[2].trim().isEmpty()) {
                    throw new RonException("Please specify both the /from and /to timing in the format yyyy-MM-dd HH:mm");
                }
                return new EventCommand(eventTokens[0].trim(), eventTokens[1].trim(), eventTokens[2].trim());
            default:
                throw new RonException("Invalid command. Please try again.");
        }
    }
}
