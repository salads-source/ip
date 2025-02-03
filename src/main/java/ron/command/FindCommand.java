package ron.command;

import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * Handles finding tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String response = tasks.findTasks(this.keyword);
        return response;
    }
}
