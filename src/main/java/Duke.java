import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String openingMessage = "Hello, I'm Duke!\n" + "What would you like to do today?";

    static String closingMessage = "Bye! Have a nice day:D";

    static String invalidIndexErrorMessage = "Please input a valid index!";

    public static void main(String[] args) {
        System.out.println(openingMessage);
        Scanner response = new Scanner(System.in);
        int counter = 0;
        ArrayList<Task> items = new ArrayList<Task>();
        String line = "";

        while (!(line.equals("bye"))) {
            line = response.nextLine();
            String[] lines = line.trim().split(" ", 2);
            String command = lines[0];
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("...\n" + "Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + items.get(i));
                }
                continue;
            }
            String arguments = lines[1];
            String missingInfoErrorMessage = "There's missing information. What would you like to do today?";
            if (command.equals("mark")) {
                try {
                    int index = Integer.parseInt(arguments) - 1;
                    items.get(index).isDone = true;
                    System.out.println("...\n" + "Nice! I've marked this task as done:\n" + items.get(index));
                } catch (NumberFormatException e) {
                    System.out.println(invalidIndexErrorMessage);
                }
            } else if (command.equals("unmark")) {
                try {
                    int index = Integer.parseInt(arguments) - 1;
                    items.get(index).isDone = false;
                    System.out.println("...\n" + "OK, I've marked this task as not done yet:\n" + items.get(index));
                } catch (NumberFormatException e) {
                    System.out.println(invalidIndexErrorMessage);
                }
            } else if (command.equals("todo")) {
                Todo newTodo = new Todo(arguments);
                items.add(newTodo);
                counter++;
            } else if (command.equals("event")) {
                try {
                    String[] eventInfo = arguments.split("/from ");
                    if (eventInfo.length < 2) {
                        throw new DukeException(missingInfoErrorMessage);
                    }
                    String[] eventDuration = eventInfo[1].split("/to ");
                    if (eventDuration.length < 2) {
                        throw new DukeException(missingInfoErrorMessage);
                    }
                    Event newEvent = new Event(eventInfo[0], eventDuration[0], eventDuration[1]);
                    items.add(newEvent);
                    counter++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(missingInfoErrorMessage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] deadlineInfo = arguments.split("/by ");
                    if (deadlineInfo.length < 2) {
                        throw new DukeException(missingInfoErrorMessage);
                    }
                    Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    items.add(newDeadline);
                    counter++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(missingInfoErrorMessage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("remove")) {
                try {
                    int index = Integer.parseInt(arguments) - 1;
                    items.remove(index);
                    counter--;
                    System.out.println("Okay, I've removed task " + (index + 1));
                } catch (NumberFormatException e) {
                    System.out.println(invalidIndexErrorMessage);
                }
            } else {
                System.out.println("Sorry, I don't understand that.");
            }
        }
        System.out.println(closingMessage);
    }
}