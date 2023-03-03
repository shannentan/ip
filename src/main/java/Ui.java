import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static messages.ErrorMessages.MESSAGE_INVALID_COMMAND;
import static messages.operationalMessages.MESSAGE_CLOSING;

public class Ui {
    public void readInCommand(ArrayList<Task> items, Scanner response, String line) throws IOException {
        while (!(line.equals("bye"))) {
            line = response.nextLine();
            String[] lines = line.trim().split(" ", 2);
            String command = lines[0];
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                Parser.displayList(items);
                continue;
            }
            String arguments = lines[1];

            if (command.equals("mark")) {
                Parser.markTask(items, arguments);
            } else if (command.equals("unmark")) {
                Parser.unmarkTask(items, arguments);
            } else if (command.equals("todo")) {
                Parser.addNewTodo(items, arguments);
            } else if (command.equals("event")) {
                Parser.addNewEvent(items, arguments);
            } else if (command.equals("deadline")) {
                Parser.addNewDeadline(items, arguments);
            } else if (command.equals("remove")) {
                Parser.removeTask(items, arguments);
            } else {
                System.out.println(MESSAGE_INVALID_COMMAND);
                continue;
            }
            Storage.writeToFile(TaskList.items);
        }
        System.out.println(MESSAGE_CLOSING);
    }
}
