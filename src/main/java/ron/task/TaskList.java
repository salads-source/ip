package ron.task;

import ron.RonException;
import ron.ui.Ui;

import java.util.ArrayList;

/**
 * Manages the list of tasks, including adding, deleting, marking, and unmarking tasks.
 * <p>
 * This class provides operations for handling task-related actions and interacts with
 * the UI to display appropriate messages to the user.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list and displays a confirmation message.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.echoMessage(Ui.echoCommand(task, this.tasks.size(), true));
    }

    /**
     * Deletes a task from the list and displays a confirmation message.
     *
     * @param taskNumber The index of the task to be deleted (0-based index).
     * @throws RonException If the task number is out of range.
     */
    public void deleteTask(int taskNumber) throws RonException {
        if (taskNumber < 0 || taskNumber >= this.tasks.size()) {
            throw new RonException("Invalid task number! Please choose a task number to delete.");
        }

        Task task = this.tasks.get(taskNumber);
        this.tasks.remove(taskNumber);
        Ui.echoMessage(Ui.echoCommand(task, this.tasks.size(), false));
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber The index of the task to be marked (0-based index).
     * @throws RonException If the task number is out of range or the task is already marked.
     */
    public void markTask(int taskNumber) throws RonException {
        if (taskNumber < 0 || taskNumber >= this.tasks.size()) {
            throw new RonException("Invalid task number!");
        }

        Task task = this.tasks.get(taskNumber);
        if (task.isMarked()) {
            throw new RonException("ron.task.Task is already marked as done!");
        } else {
            task.mark();
            System.out.printf("Nice! I've marked this task as done:\n %s\n", task);
        }
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param taskNumber The index of the task to be unmarked (0-based index).
     * @throws RonException If the task number is out of range or the task is already unmarked.
     */
    public void unmarkTask(int taskNumber) throws RonException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new RonException("Invalid task number!");
        }

        Task task = this.tasks.get(taskNumber);
        if (!task.isMarked()) {
            throw new RonException("ron.task.Task is already unmarked!");
        } else {
            task.unmark();
            System.out.printf("Nice! I've marked this task as done:\n %s\n", task);
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList containing all stored tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Displays matching tasks which contains the keyword.
     *
     * @param keyword Search for tasks which contains the keyword.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        Ui.displayMatchingTasks(matchingTasks);
    }
}
