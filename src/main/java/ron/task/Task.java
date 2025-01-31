package ron.task;

/**
 * Represents an abstract task that can be marked as completed or uncompleted.
 * <p>
 * This class serves as a base for specific types of tasks such as ToDo, Deadline, and Event.
 * It contains common attributes and methods shared by all task types.
 * </p>
 */
public abstract class Task {
    private final String name;
    private boolean completed = false;

    /**
     * Constructs a Task with a specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Unmarks the task, setting it as incomplete.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isMarked() {
        return this.completed;
    }

    /**
     * Retrieves the name or description of the task.
     *
     * @return The task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the type of the task.
     * <p>
     * This method is abstract and should be implemented by subclasses to return a single-character
     * identifier (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     * </p>
     *
     * @return The type of the task as a string.
     */
    public abstract String getType();

    /**
     * Retrieves additional details about the task.
     * <p>
     * For example, deadlines return their due date, and events return their start and end times.
     * </p>
     *
     * @return A string containing task-specific details.
     */
    public abstract String getDetails();

    /**
     * Returns a string representation of the task, including its type, completion status, name, and details.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        String taskBox;
        taskBox = completed ? "[X]": "[]";

        return String.format("[%s]%s %s %s", getType(), taskBox, this.name, getDetails());
    }

}
