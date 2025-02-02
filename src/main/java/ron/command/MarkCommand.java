package ron.command;

import ron.RonException;
import ron.storage.Storage;
import ron.task.TaskList;
import ron.ui.Ui;

/**
 * Mark stored commands
 */
public class MarkCommand extends Command {
    private final int TASK_NUMBER;

    public MarkCommand(int taskNumber) {
        this.TASK_NUMBER = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        tasks.markTask(this.TASK_NUMBER);
        storage.save(tasks.getTasks());
    }
}
