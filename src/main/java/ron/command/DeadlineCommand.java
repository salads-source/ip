package ron.command;

import ron.RonException;
import ron.task.TaskList;
import ron.task.Deadline;
import ron.storage.Storage;
import ron.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles adding a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String response = tasks.addTask(new Deadline(this.description, deadline));
            storage.save(tasks.getTasks());
            return response;
        } catch (DateTimeParseException e) {
            throw new RonException("Invalid date format! Use yyyy-MM-dd HH:mm.");
        }
    }
}
