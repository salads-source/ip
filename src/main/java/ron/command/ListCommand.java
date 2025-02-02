package ron.command;

import ron.storage.Storage;
import ron.task.TaskList;
import ron.ui.Ui;

/**
 * Handles current stored commands
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Ui.listTasks(tasks.getTasks());
    }
}
