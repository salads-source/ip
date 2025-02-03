package ron.command;

import ron.storage.Storage;
import ron.task.TaskList;
import ron.ui.Ui;

/**
 * Handles current stored commands
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String response = Ui.getTaskList(tasks.getTasks());
        return response;
    }
}
