package ron.task;

import ron.RonException;
import ron.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.echoMessage(Ui.echoCommand(task, this.tasks.size(), true));
    }

    public void deleteTask(int taskNumber) throws RonException {
        if (taskNumber < 0 || taskNumber >= this.tasks.size()) {
            throw new RonException("Invalid task number! Please choose a task number to delete.");
        }

        Task task = this.tasks.get(taskNumber);
        this.tasks.remove(taskNumber);
        Ui.echoMessage(Ui.echoCommand(task, this.tasks.size(), false));
    }

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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
