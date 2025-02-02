package ron.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import ron.storage.Storage;
import ron.task.Task;
import ron.task.TaskList;
import ron.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private static final String TEST_FILE_PATH = "./data/test_tasks.txt";

    @BeforeEach
    public void setUp() {
        this.taskList = new TaskList();
        this.storage = new Storage(TEST_FILE_PATH);
        this.ui = new Ui();
    }

    @AfterEach
    public void tearDown() {

        this.taskList = null;
        this.storage = null;
        this.ui = null;
    }

    @Test
    @DisplayName("Add a ToDo task")
    public void testAddTodoCommand() throws Exception {
        Command command = Parser.parseCommand("todo Club meeting");
        command.execute(taskList, storage, ui);

        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size(), "Task list should contain one task.");
        assertEquals("[T][] Club meeting", tasks.get(0).toString().trim(), "Todo task should be added correctly.");
    }

    @Test
    @DisplayName("Add a Deadline task")
    public void testAddDeadlineCommand() throws Exception {
        Command command = Parser.parseCommand("deadline Homework /by 2025-01-31 23:59");
        command.execute(taskList, storage, ui);

        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size(), "Task list should contain one task.");
        assertEquals("[D][] Homework (by: Jan 31 2025, 23:59)", tasks.get(0).toString().trim(), "Deadline task should be added correctly.");
    }

    @Test
    @DisplayName("Mark a task as done")
    public void testMarkTaskCommand() throws Exception {
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
        taskList.addTask(task);

        Command command = Parser.parseCommand("mark 1");
        command.execute(taskList, storage, ui);

        assertTrue(taskList.getTasks().get(0).isMarked(), "Task should be marked as done.");
    }

    @Test
    @DisplayName("Unmark a task as undone")
    public void testUnmarkTaskCommand() throws Exception {
        Task task = new Task("Write journal") {
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
        taskList.addTask(task);

        Command command = Parser.parseCommand("unmark 1");
        command.execute(taskList, storage, ui);

        assertFalse(taskList.getTasks().get(0).isMarked(), "Task should be unmarked as undone.");
    }

    @Test
    @DisplayName("Delete a task")
    public void testDeleteTaskCommand() throws Exception {
        Task task = new Task("Clean room") {
            @Override
            public String getType() {
                return "T";
            }

            @Override
            public String getDetails() {
                return "";
            }
        };
        taskList.addTask(task);

        Command command = Parser.parseCommand("delete 1");
        command.execute(taskList, storage, ui);
        assertTrue(taskList.getTasks().isEmpty(), "Task list should be empty after deletion.");
    }
}
