import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\n" + "How may I help you today?");
        Scanner response = new Scanner(System.in);
        String line = response.nextLine();
        int counter = 0;
        String[] items = new String[100];

        while (!(line.equals("bye"))) {
            if (line.equals("list")) {
                for (int a = 0; a < counter; a++) {
                    System.out.println((a + 1) + ". " + items[a]);
                }
                line = response.nextLine();
            } else {
                System.out.println("added: " + line);
                items[counter] = line;
                line = response.nextLine();
                counter++;
            }
        }
        System.out.println("Bye! Have a nice day:D");
    }
}


/* public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" +
                " What can I do for you?\n" +  "Bye. Hope to see you again soon!");
    }
}
*/