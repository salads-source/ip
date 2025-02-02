package ron.command;

import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * Handles exiting the application
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Ui.farewellUser();
        ui.closeScanner();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
