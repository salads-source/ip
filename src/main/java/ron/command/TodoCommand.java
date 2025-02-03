package ron.command;

import ron.RonException;
import ron.task.TaskList;
import ron.task.Todo;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * Handles adding a ToDo task.
 */
public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        if (this.description.trim().isEmpty()) {
            throw new RonException("ToDo task cannot be empty!");
        }
        String response = tasks.addTask(new Todo(this.description));
        storage.save(tasks.getTasks());
        return response;
    }
}
