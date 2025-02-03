package ron.command;

import ron.RonException;
import ron.task.TaskList;
import ron.task.Event;
import ron.storage.Storage;
import ron.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles adding an Event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws RonException {
        try {
            LocalDateTime fromTime = LocalDateTime.parse(this.from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime toTime = LocalDateTime.parse(this.to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            String response = tasks.addTask(new Event(this.description, fromTime, toTime));
            storage.save(tasks.getTasks());
            return response;
        } catch (DateTimeParseException e) {
            throw new RonException("Invalid date format! Use yyyy-MM-dd HH:mm.");
        }
    }
}
