public class Event extends Task {
    private final String from;
    private final String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDetails() {
        return String.format("(from: %s to: %s)", this.from, this.to);
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
}
