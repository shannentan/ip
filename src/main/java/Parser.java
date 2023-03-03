import exceptions.DukeException;

import java.util.ArrayList;

import static messages.ErrorMessages.MESSAGE_INVALID_INDEX_ERROR;
import static messages.operationalMessages.MESSAGE_MARK_TASK;
import static messages.operationalMessages.MESSAGE_UNMARK_TASK;
import static messages.operationalMessages.MESSAGE_REMOVE_TASK;

public class Parser {

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
            System.out.println(MESSAGE_MARK_TASK + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    public static void unmarkTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.get(index).isDone = false;
            System.out.println(MESSAGE_UNMARK_TASK + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    public static void removeTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.remove(index);
            System.out.println(MESSAGE_REMOVE_TASK + (index + 1));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    public static void addNewTodo(ArrayList<Task> items, String arguments) {
        Task newTask = TaskList.createNewTodo(arguments);
        items.add(newTask);
    }

    public static void addNewEvent(ArrayList<Task> items, String arguments) {
        try {
            Task newEvent = TaskList.createNewEvent(arguments);
            items.add(newEvent);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addNewDeadline(ArrayList<Task> items, String arguments) {
        try {
            Task newDeadline = TaskList.createNewDeadline(arguments);
            items.add(newDeadline);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findKeywords(ArrayList<Task> items, String arguments) {
        boolean isMessagePrinted = false;
        boolean isKeywordFound = false;
        String[] keywords = arguments.split(" ");
        for (int i = 0; i < items.size(); i ++) {
            for (String keyword : keywords) {
                String taskDescription = items.get(i).description;
                if (taskDescription.contains(keyword)) {
                    isKeywordFound = true;
                    if (!isMessagePrinted) {
                        System.out.println("Here are the matching tasks in your list:");
                        isMessagePrinted = true;
                    }
                    System.out.println((i + 1) + "." + items.get(i));
                    break;
                }
            }
        }
        if (!isKeywordFound) {
            System.out.println("Sorry, there are no such tasks.");
        }
    }
}