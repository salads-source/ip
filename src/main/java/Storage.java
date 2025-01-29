import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.Files.createFile;

public class Storage {
    private final Path filePath;
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }
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

    public void save(ArrayList<Task> tasks) throws RonException {
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            for (Task task: tasks) {
                writer.write(taskToFileFormat(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RonException("Error writing to the file: " + e.getMessage());
        }
    }

    public void createFile() throws RonException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RonException("Failed to create data file: " + e.getMessage());
        }
    }

    private String taskToFileFormat(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getType());
        sb.append(" | ").append(task.isMarked() ? "1" : "0");
        sb.append(" | ").append(task.getName());

        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            sb.append(" | ").append(((Event) task).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
        return sb.toString();
    }


    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isMarked = parts[1].equals("1");
            String name = parts[2];

            Task task = switch (type) {
                case "T" -> new Todo(name);
                case "D" -> {
                    LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    yield new Deadline(name, by);
                }
                case "E" -> {
                    LocalDateTime from = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime to = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
