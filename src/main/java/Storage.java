import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static messages.operationalMessages.MESSAGE_NO_EXISTING_FILE;

public class Storage {

    public static Task loadNewTodo(String isDoneString, String description) {
        Todo readTodo = new Todo(description);
        if (isDoneString.equals("1")) {
            readTodo.isDone = true;
        }
        return readTodo;
    }

    public static Task loadNewEvent(String isDoneString, String description, String start, String end) {
        Event readEvent = new Event(description, start, end);
        if (isDoneString.equals("1")) {
            readEvent.isDone = true;
        }
        return readEvent;
    }

    public static Task loadNewDeadline(String isDoneString, String description, String by) {
        Deadline readDeadline = new Deadline(description, by);
        if (isDoneString.equals("1")) {
            readDeadline.isDone = true;
        }
        return readDeadline;
    }

    public void loadFromFile(ArrayList<Task> items) throws FileNotFoundException {
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
        } else {
            System.out.println(MESSAGE_NO_EXISTING_FILE);
            File dataFolder = new File("data");
            dataFolder.mkdir();
        }
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
}