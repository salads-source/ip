public class Task {
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

    @Override
    public String toString() {
        String taskBox;
        taskBox = completed ? "[X]": "[]";

        return String.format("%s %s", taskBox, name);
    }

}
