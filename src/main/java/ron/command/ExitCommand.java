package ron.command;

import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * Handles exiting the application
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String response = Ui.farewellUser();
        ui.closeScanner();
        return response;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
