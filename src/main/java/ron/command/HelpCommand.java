package ron.command;

import ron.storage.Storage;
import ron.task.TaskList;
import ron.ui.Ui;

/**
 * Represents a command to display the help information.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String result = ui.showHelp();
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
