public class Deadline extends Task {

    protected String by;
    protected String opening = "...\n" + "Got it. I've added this deadline:\n" + " [D][ ] ";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println(opening + description + "(by: " + by + ")");
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + by + ")");
    }
}
