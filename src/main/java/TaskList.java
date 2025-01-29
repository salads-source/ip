import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws RonException {
        if (index < 0 || index >= tasks.size()) {
            throw new RonException("Invalid task number!");
        }
        tasks.remove(index);
    }

    public void markTask(int index) throws RonException {
        if (index < 0 || index >= tasks.size()) {
            throw new RonException("Invalid task number!");
        }
        tasks.get(index).mark();
    }

    public void unmarkTask(int index) throws RonException {
        if (index < 0 || index >= tasks.size()) {
            throw new RonException("Invalid task number!");
        }
        tasks.get(index).unmark();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
