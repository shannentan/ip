public class Todo extends Task {

    /**
     * constructs a Todo item
     * @param description String containing description of task added
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

   @Override
    public String toSerialisedString() {
        String isDoneStr = isDone ? "1" : "0";
        return ("T|" + isDoneStr +"|" + description);
    }
}