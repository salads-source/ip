package ron.command;

import ron.RonException;
import ron.storage.Storage;
import ron.task.TaskList;
import ron.ui.Ui;

/**
 * Mark stored commands
 */
public class UnmarkCommand extends Command {
    private final int TASK_NUMBER;

    public UnmarkCommand(int taskNumber) {
        this.TASK_NUMBER = taskNumber;
    }
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        String response = tasks.unmarkTask(this.TASK_NUMBER);
        storage.save(tasks.getTasks());
        return response;
    }
}