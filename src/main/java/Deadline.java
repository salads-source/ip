public class Deadline extends Task {
    private final String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDetails() {
        return String.format("(by: %s)", by);
    }
}
