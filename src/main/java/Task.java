public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " "); // mark x if isDone is true, space if false
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + description);
    }
}
