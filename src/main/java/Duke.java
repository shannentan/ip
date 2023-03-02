import exceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static messages.ErrorMessages.invalidCommandMessage;
import static messages.ErrorMessages.invalidIndexErrorMessage;
import static messages.ErrorMessages.missingInfoErrorMessage;
import static messages.newTaskMessages.createNewDeadlineMessage;
import static messages.newTaskMessages.createNewEventMessage;
import static messages.newTaskMessages.createNewTodoMessage;
import static messages.operationalMessages.openingMessage;
import static messages.operationalMessages.closingMessage;
import static messages.operationalMessages.markTaskMessage;
import static messages.operationalMessages.unmarkTaskMessage;
import static messages.operationalMessages.removeTaskMessage;

public class Duke {

    public static void main(String[] args) throws IOException {
        ArrayList<Task> items = new ArrayList<>();

        File f = new File("data/Duke.txt");
        if (f.exists()) {
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String taskLine = scan.nextLine();
                String[] taskArgs = taskLine.split("\\|");
                if (taskArgs[0].equals("T")) {
                    items.add(loadNewTodo(taskArgs[1].trim(), taskArgs[2].trim()));
                } else if (taskArgs[0].equals("D")) {
                    items.add(loadNewEvent(taskArgs[1].trim(), taskArgs[2].trim(), taskArgs[3].trim(), taskArgs[4].trim()));
                } else {
                    taskArgs[0] = "E";
                    items.add(loadNewDeadline(taskArgs[1].trim(), taskArgs[2].trim(), taskArgs[3].trim()));
                }
            }
        }

        System.out.println(openingMessage);
        Scanner response = new Scanner(System.in);
        String line = "";

        while (!(line.equals("bye"))) {
            line = response.nextLine();
            String[] lines = line.trim().split(" ", 2);
            String command = lines[0];
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                displayList(items);
                continue;
            }
            String arguments = lines[1];

            if (command.equals("mark")) {
                markTask(items, arguments);
            } else if (command.equals("unmark")) {
                unmarkTask(items, arguments);
            } else if (command.equals("todo")) {
                addNewTodo(items, arguments);
            } else if (command.equals("event")) {
                addNewEvent(items, arguments);
            } else if (command.equals("deadline")) {
                addNewDeadline(items, arguments);
            } else if (command.equals("remove")) {
                removeTask(items, arguments);
            } else {
                System.out.println(invalidCommandMessage);
                continue;
            }
            writeToFile(items);
        }
        System.out.println(closingMessage);
    }

    public static void writeToFile(ArrayList<Task> items) throws IOException {
        FileWriter fw = new FileWriter("data/Duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task item : items) {
            String newSerialisedTask = item.toSerialisedString();
            sb.append(newSerialisedTask).append("\n");
        }
        String serialString = sb.toString();
        fw.write(serialString);
        fw.close();
    }

    public static void displayList(ArrayList<Task> items) {
        System.out.println("...\n" + "Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i));
        }
    }

    public static void markTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.get(index).isDone = true;
            System.out.println(markTaskMessage + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(invalidIndexErrorMessage);
        }
    }

    public static void unmarkTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.get(index).isDone = false;
            System.out.println(unmarkTaskMessage + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(invalidIndexErrorMessage);
        }
    }

    public static void removeTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.remove(index);
            System.out.println(removeTaskMessage + (index + 1));
        } catch (NumberFormatException e) {
            System.out.println(invalidIndexErrorMessage);
        }
    }

    public static Task createNewTodo(String arguments) {
        System.out.println(createNewTodoMessage + arguments);
        return new Todo(arguments);
    }

    public static void addNewTodo(ArrayList<Task> items, String arguments) {
        Task newTask = createNewTodo(arguments);
        items.add(newTask);
    }

    public static Task loadNewTodo(String isDoneString, String description) {
        Todo readTodo = new Todo(description);
        if (isDoneString.equals("1")) {
            readTodo.isDone = true;
        }
        return readTodo;
    }

    public static Task createNewEvent(String arguments) throws DukeException {
        try {
            String[] eventInfo = arguments.split("/from ");
            if (eventInfo.length < 2) {
                throw new DukeException(missingInfoErrorMessage);
            }
            String[] eventDuration = eventInfo[1].split("/to ");
            if (eventDuration.length < 2) {
                throw new DukeException(missingInfoErrorMessage);
            }
            System.out.println(createNewEventMessage + eventInfo[0] + "(from: " + eventDuration[0] + " to: " + eventDuration[1] + ")");
            return new Event(eventInfo[0], eventDuration[0], eventDuration[1]);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(missingInfoErrorMessage);
        }
    }

    public static void addNewEvent(ArrayList<Task> items, String arguments) {
        try {
            Task newEvent = createNewEvent(arguments);
            items.add(newEvent);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task loadNewEvent(String isDoneString, String description, String start, String end) {
        Event readEvent = new Event(description, start, end);
        if (isDoneString.equals("1")) {
            readEvent.isDone = true;
        }
        return readEvent;
    }

    public static Task createNewDeadline(String arguments) throws DukeException {
        String[] deadlineInfo = arguments.split("/by ");
        if (deadlineInfo.length < 2) {
            throw new DukeException(missingInfoErrorMessage);
        }
        System.out.println(createNewDeadlineMessage + deadlineInfo[0] + "(by: " + deadlineInfo[1] + ")");
        return new Deadline(deadlineInfo[0], deadlineInfo[1]);
    }

    public static void addNewDeadline(ArrayList<Task> items, String arguments) {
        try {
            Task newDeadline = createNewDeadline(arguments);
            items.add(newDeadline);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task loadNewDeadline(String isDoneString, String description, String by) {
        Deadline readDeadline = new Deadline(description, by);
        if (isDoneString.equals("1")) {
            readDeadline.isDone = true;
        }
        return readDeadline;
    }
}