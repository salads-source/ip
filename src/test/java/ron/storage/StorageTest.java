package ron.storage;

import org.junit.jupiter.api.*;
import ron.RonException;
import ron.task.Deadline;
import ron.task.Event;
import ron.task.Task;
import ron.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_FILE_PATH = "./data/test_tasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        this.storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        Path filePath = Paths.get(TEST_FILE_PATH);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error trying to delete file.");
        }
    }

    @Test
    @DisplayName("Test creating a new file if it does not exist")
    public void testCreateFile() throws RonException {
        this.storage.createFile();
        assertTrue(Files.exists(Path.of(TEST_FILE_PATH)), "File should be created.");
    }

    @Test
    @DisplayName("Test loading tasks from an empty file")
    public void testLoadEmptyFile() throws RonException {
        this.storage.createFile();
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Task list should be empty for a new file.");
    }

    @Test
    @DisplayName("Test saving and loading tasks")
    public void testSaveAndLoadTasks() throws RonException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("clubbbbbbbbbbb"));
        tasksToSave.add(new Deadline(" 2 leetcodeeee questions", LocalDateTime.parse("2025-01-31 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        tasksToSave.add(new Event("iex meetinggg", LocalDateTime.parse("2025-02-01 09:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2025-02-01 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));


        this.storage.save(tasksToSave);

        ArrayList<Task> loadedTasks = this.storage.load();

        assertEquals(tasksToSave.size(), loadedTasks.size(), "Loaded tasks should match the number of saved tasks.");
        assertEquals(tasksToSave.get(0).toString(), loadedTasks.get(0).toString(), "First task should match.");
        assertEquals(tasksToSave.get(1).toString(), loadedTasks.get(1).toString(), "Second task should match.");
        assertEquals(tasksToSave.get(2).toString(), loadedTasks.get(2).toString(), "Third task should match.");
    }

    @Test
    @DisplayName("Test saving empty task list")
    public void testSaveEmptyTaskList() throws RonException {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        this.storage.save(emptyTasks);
        ArrayList<Task> loadedTasks = this.storage.load();
        assertTrue(loadedTasks.isEmpty(), "Loaded task list should be empty after saving an empty list.");
    }
}