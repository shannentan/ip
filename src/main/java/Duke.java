import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\n" + "What would you like to do today?");
        Scanner response = new Scanner(System.in);
        String line = response.nextLine();
        int counter = 0;
        String[] items = new String[101]; // 1-based
        String[] check = new String[101]; // 1-based

        while (!(line.equals("bye"))) {
            if (line.equals("list")) {
                System.out.println("...\n" + "Here are the tasks in your list:");
                for (int a = 1; a <= counter; a++) {
                    String checkOrNot = "x";
                    if ((check[a] == null) || (check[a] == " ")) {
                        checkOrNot = (" ");
                    }
                    System.out.println((a) + ". " + "[" + checkOrNot + "] " + items[a]);
                }
            } else if (line.startsWith("mark")) {
                String strIndex = line.substring(5);
                int index = Integer.parseInt(strIndex);
                check[index] = "x";
                System.out.println("...\n" + "Nice! I've marked this task as done:");
                System.out.println(index);
                System.out.println("  [" + check[index] + "] " + items[index]);
            } else if (line.startsWith("unmark")) {
                String strIndex = line.substring(7);
                int index = Integer.parseInt(strIndex);
                check[index] = " ";
                System.out.println("...\n" + "OK, I've marked this task as not done yet:");
                System.out.println("  [" + check[index] + "] " + items[index]);
            } else {
                System.out.println("...\n" + "added: " + line);
                counter++;
                items[counter] = line;
            }
            line = response.nextLine();
        }
        System.out.println("Bye! Have a nice day:D");
    }
}