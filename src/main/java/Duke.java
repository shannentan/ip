import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\n" + "What would you like to do today?");
        Scanner response = new Scanner(System.in);
        String line = response.nextLine();
        int counter = 1;
        Task[] items = new Task[101]; // 1-based

        while (!(line.equals("bye"))) {
            if (line.equals("list")) {
                System.out.println("...\n" + "Here are the tasks in your list:");
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + "." + items[i]);
                }
            } else if (line.startsWith("mark")) {
                String strIndex = line.substring(5);
                int index = Integer.parseInt(strIndex);
                items[index].isDone = true;
                System.out.println("...\n" + "Nice! I've marked this task as done:\n" + items[index]);
            } else if (line.startsWith("unmark")) {
                String strIndex = line.substring(7);
                int index = Integer.parseInt(strIndex);
                items[index].isDone = false;
                System.out.println("...\n" + "OK, I've marked this task as not done yet:\n" + items[index]);
            } else if (line.startsWith("todo")) {
                Todo newTodo = new Todo(line.substring(5));
                items[counter] = newTodo;
                counter++;
            } else if (line.startsWith("event")) {
                String eventSubstring = line.trim().substring(6);
                String[] eventInfo = eventSubstring.split("/from ");
                String[] eventDuration = eventInfo[1].split("/to ");
                Event newEvent = new Event(eventInfo[0], eventDuration[0], eventDuration[1]);
                items[counter] = newEvent;
                counter++;
            } else if (line.startsWith("deadline")) {
                String deadlineWithoutCommand = line.trim().substring(9);
                String[] deadlineInfo = deadlineWithoutCommand.split("/by ");
                Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                items[counter] = newDeadline;
                counter++;
            }
            line = response.nextLine();
        }
        System.out.println("Bye! Have a nice day:D");
    }
}