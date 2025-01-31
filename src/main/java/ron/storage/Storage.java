package ron.storage;

import ron.RonException;

import ron.task.Deadline;
import ron.task.Event;
import ron.task.Task;
import ron.task.Todo;

import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of task data to and from a file.
 * <p>
 * This class is responsible for persistent storage of tasks, ensuring that
 * task lists are saved to disk and reloaded when the application starts.
 * It supports serialization and deserialization of various task types.
 * </p>
 */
public class Storage {
    private final Path filePath;
    private static final String DELIMITER = " | ";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Storage object to handle file-based task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file into an ArrayList.
     *
     * @return An ArrayList containing all tasks from the file.
     * @throws RonException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws RonException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.notExists(filePath)) {
            createFile();
            return tasks;
        }

        try (Scanner scanner = new Scanner(filePath)) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new RonException("Error reading from the file");
        }

        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws RonException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws RonException {
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            for (Task task: tasks) {
                writer.write(taskToFileFormat(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RonException("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Creates the storage file and necessary directories if they do not exist.
     *
     * @throws RonException If the file or directories cannot be created.
     */
    public void createFile() throws RonException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RonException("Failed to create data file: " + e.getMessage());
        }
    }

    /**
     * Converts a task into a file-compatible string format.
     *
     * @param task The task to be formatted.
     * @return A string representation of the task suitable for file storage.
     */
    private String taskToFileFormat(Task task) {
        StringBuilder sb = new StringBuilder()
                .append(task.getType())
                .append(DELIMITER)
                .append(task.isMarked() ? "1" : "0")
                .append(DELIMITER)
                .append(task.getName());

        if (task instanceof Deadline deadline) {
            sb.append(DELIMITER).append(deadline.getBy().format(DATE_FORMATTER));
        } else if (task instanceof Event event) {
            sb.append(DELIMITER).append(event.getFrom().format(DATE_FORMATTER))
                    .append(DELIMITER).append(event.getTo().format(DATE_FORMATTER));
        }

        return sb.toString();
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     * <p>
     * Supports different task types (ToDo, Deadline, Event) and ensures that
     * the task is properly restored with its completion status.
     * </p>
     *
     * @param line A line from the storage file containing task data.
     * @return A Task object corresponding to the parsed data, or null if the line is corrupted.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isMarked = parts[1].equals("1");
            String name = parts[2];

            Task task = switch (type) {
                case "T" -> new Todo(name);
                case "D" -> {
                    LocalDateTime by = LocalDateTime.parse(parts[3],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    yield new Deadline(name, by);
                }
                case "E" -> {
                    LocalDateTime from = LocalDateTime.parse(parts[3],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime to = LocalDateTime.parse(parts[4],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    yield new Event(name, from, to);
                }
                default -> throw new IllegalArgumentException("Invalid task type");
            };


            if (isMarked) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task: " + line);
            return null;
        }
    }



}
