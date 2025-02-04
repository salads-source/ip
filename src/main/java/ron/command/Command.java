package ron.command;

import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;
import ron.RonException;

/**
 * Base abstract class for all user commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list to modify.
     * @param storage The storage handler.
     * @param ui The ui for user interaction.
     * @return The string response to be displayed on gui.
     * @throws RonException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws RonException;

    /**
     * Determines if the command should exit the program.
     *
     * @return {@code true} if this is an exit command, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
