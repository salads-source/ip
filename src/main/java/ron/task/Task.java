package ron.task;

public abstract class Task {
    private final String name;
    private boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean isMarked() {
        return this.completed;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getType();
    public abstract String getDetails();

    @Override
    public String toString() {
        String taskBox;
        taskBox = completed ? "[X]": "[]";

        return String.format("[%s]%s %s %s", getType(), taskBox, this.name, getDetails());
    }

}
