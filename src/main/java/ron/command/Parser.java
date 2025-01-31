package ron.command;

import ron.RonException;

import ron.ui.Ui;

import ron.task.Task;
import ron.task.TaskList;
import ron.task.Todo;
import ron.task.Deadline;
import ron.task.Event;

import ron.storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static void parseCommand(String command, TaskList tasks, Storage storage, Ui ui) {
        try {
            if (command.equals("bye")) {
                Ui.farewellUser();
                ui.closeScanner();
                System.exit(0);
            } else if (command.equals("list")) {
                Ui.listTasks(tasks.getTasks());
            } else if (command.startsWith("mark")) {
                String[] tokens = command.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Insufficient arguments passed!");
                }

                int taskNumber = Integer.parseInt(tokens[1]) - 1;
                tasks.markTask(taskNumber);
                storage.save(tasks.getTasks());
            } else if (command.startsWith("unmark")) {
                String[] tokens = command.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Insufficient arguments passed!");
                }

                int taskNumber = Integer.parseInt(tokens[1]) - 1;
                tasks.unmarkTask(taskNumber);
                storage.save(tasks.getTasks());
            } else if (command.startsWith("delete")) {
                String[] tokens = command.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Please specify a task number to delete.");
                }

                int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.deleteTask(taskNumber);
                storage.save(tasks.getTasks());
            } else if (command.startsWith("todo")) {
                String[] tokens = command.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("ron.task.Todo list cannot be empty!");
                }

                Task task = new Todo(tokens[1]);
                tasks.addTask(task);
                storage.save(tasks.getTasks());
            } else if (command.startsWith("deadline")) {
                String[] tokens = command.split(" /by ");
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    throw new RonException("Please specify a deadline using /by in the format yyyy-MM-dd HH:mm");
                }

                String taskName = tokens[0].substring(9).trim();
                LocalDateTime by = LocalDateTime.parse(tokens[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                Task task = new Deadline(taskName, by);
                tasks.addTask(task);
                storage.save(tasks.getTasks());
            } else if (command.startsWith("event")) {
                String[] tokens = command.split(" /from | /to ");
                if (tokens.length < 3 || tokens[1].trim().isEmpty() || tokens[2].trim().isEmpty()) {
                    throw new RonException("Please specify both the /from and /to timing in the format yyyy-MM-dd HH:mm");
                }

                String taskName = tokens[0].substring(6).trim();
                LocalDateTime from = LocalDateTime.parse(tokens[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime to = LocalDateTime.parse(tokens[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                Task task = new Event(taskName, from, to);
                tasks.addTask(task);
                storage.save(tasks.getTasks());
            } else {
                Ui.echoMessage("Invalid command. Please try again.");
            }
        } catch (RonException | NumberFormatException e) {
            Ui.echoMessage("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.echoMessage("Invalid date format! Please use yyyy-MM-dd HH:mm");
        }
    }
}
