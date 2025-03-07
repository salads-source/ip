package ron.command;

import ron.RonException;
import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * Handles deleting a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        String response = tasks.deleteTask(this.taskNumber);
        storage.save(tasks.getTasks());
        return response;
    }
}
