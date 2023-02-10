import exceptions.DukeException;

import java.util.Scanner;

public class Duke {
//    1) check for sufficient arguments
//    2) check for /by etc

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\n" + "What would you like to do today?");
        Scanner response = new Scanner(System.in);
        int counter = 1;
        Task[] items = new Task[101]; // 1-based
        String line = "";

        while (!(line.equals("bye"))) {
            line = response.nextLine();
            String[] lines = line.trim().split(" ", 2);
            String command = lines[0];
            if (line.equals("list")) {
                System.out.println("...\n" + "Here are the tasks in your list:");
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + "." + items[i]);
                }
                continue;
            } else if (line.equals("bye")) {
                break;
            }
            String arguments = lines[1];
            if (command.equals("mark")) {
                try {
                    int index = Integer.parseInt(arguments);
                    items[index].isDone = true;
                    System.out.println("...\n" + "Nice! I've marked this task as done:\n" + items[index]);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid index!");
                }
            } else if (command.equals("unmark")) {
                try {
                    int index = Integer.parseInt(arguments);
                    items[index].isDone = false;
                    System.out.println("...\n" + "OK, I've marked this task as not done yet:\n" + items[index]);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid index!");
                }
            } else if (command.equals("todo")) {
                Todo newTodo = new Todo(line.substring(5));
                items[counter] = newTodo;
                counter++;
            } else if (command.equals("event")) {
                String eventSubstring = line.trim().substring(6);
                String[] eventInfo = eventSubstring.split("/from ");
                String[] eventDuration = eventInfo[1].split("/to ");
                Event newEvent = new Event(eventInfo[0], eventDuration[0], eventDuration[1]);
                items[counter] = newEvent;
                counter++;
            } else if (command.equals("deadline")) {
                try {
                    String[] deadlineInfo = arguments.split("/by ");
                    if (deadlineInfo.length < 2) {
                        throw new DukeException("There's missing information. Did you miss out the description or the deadline?");
                    }
                    Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    items[counter] = newDeadline;
                    counter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("What would you like to do, and when's the deadline?");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Remember to add /by before the deadline!");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye! Have a nice day:D");
    }
}