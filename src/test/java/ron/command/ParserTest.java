package ron.command;

import org.junit.jupiter.api.*;
import ron.RonException;
import ron.storage.Storage;
import ron.task.Task;
import ron.task.TaskList;
import ron.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private ByteArrayOutputStream outputStream;
    private static final String TEST_FILE_PATH = "./data/test_tasks.txt";


    @BeforeEach
    public void setUp() {
        this.taskList = new TaskList();
        this.storage = new Storage(TEST_FILE_PATH);
        this.ui = new Ui();

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @DisplayName("Test adding a Todo task")
    public void testAddTodoCommand() {
        Parser.parseCommand("todo clubbb", this.taskList, this.storage, this.ui);
        ArrayList<Task> tasks = this.taskList.getTasks();

        assertEquals(1, tasks.size(), "Task list should contain one task.");
        assertEquals("[T][] clubbb", tasks.get(0).toString().trim(), "Todo task should be added correctly.");
    }

    @Test
    @DisplayName("Test adding a Deadline task")
    public void testAddDeadlineCommand() {
        Parser.parseCommand("deadline 2 leetcode /by 2025-01-31 23:59", this.taskList, this.storage, this.ui);
        List<Task> tasks = this.taskList.getTasks();

        assertEquals(1, tasks.size(), "Task list should contain one task.");
        assertEquals("[D][] 2 leetcode (by: Jan 31 2025, 23:59)", tasks.get(0).toString().trim(),
                "Deadline task should be added correctly.");
    }

    @Test
    @DisplayName("Test marking a task as done")
    public void testMarkTaskCommand() {
        this.taskList.addTask(new Task("Read book") {
            @Override
            public String getType() {
                return "T";
            }

            @Override
            public String getDetails() {
                return "";
            }
        });

        Parser.parseCommand("mark 1", this.taskList, this.storage, this.ui);
        assertTrue(this.taskList.getTasks().get(0).isMarked(), "Task should be marked as done.");
    }

    @Test
    @DisplayName("Test unmarking a task")
    public void testUnmarkTaskCommand() {
        Task task = new Task("Read book") {
            @Override
            public String getType() {
                return "T";
            }

            @Override
            public String getDetails() {
                return "";
            }
        };
        task.mark();
        this.taskList.addTask(task);

        Parser.parseCommand("unmark 1", this.taskList, this.storage, this.ui);
        assertFalse(this.taskList.getTasks().get(0).isMarked(), "Task should be marked as undone.");
    }



}
