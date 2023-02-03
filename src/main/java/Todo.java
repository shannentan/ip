public class Todo extends Task {
    protected String opening = "...\n" + "Got it. I've added this task:\n" + " [T][ ] ";

    public Todo(String description) {
        super(description);
        System.out.println(opening + description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}