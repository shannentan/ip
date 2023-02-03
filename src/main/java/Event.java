public class Event extends Task {
    protected String start;
    protected String end;
    protected String opening = "...\n" + "Got it. I've added this event:\n" + " [E][ ] ";

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        System.out.println(opening + description + "(from: " + start + " to: " + end + ")");
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + start + " to: " + end + ")");
    }
}
